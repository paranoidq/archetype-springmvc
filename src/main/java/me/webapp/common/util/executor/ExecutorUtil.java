package me.webapp.common.util.executor;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.*;
import me.webapp.common.util.executor.policy.AbortPolicyWithReport;

import java.util.concurrent.*;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class ExecutorUtil {
    private ListeningExecutorService listeningExecutorService;

    private ExecutorUtil(ListeningExecutorService listeningExecutorService) {
        this.listeningExecutorService = listeningExecutorService;
    }

    /**
     * Create customized ExecutorUtil instance
     * @param poolName
     * @param coreThreads
     * @param maxThreads
     * @param queueSize
     *          queue == 0 gives {@link SynchronousQueue},
     *          queues < 0 gives unbounded {@link LinkedBlockingQueue},
     *          queues > 0 gives bounded {@link LinkedBlockingQueue}
     * @param keepAliveSeconds
     * @return
     */
    public static ExecutorUtil create(String poolName, int coreThreads, int maxThreads, int queueSize, int keepAliveSeconds) {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(
            coreThreads, maxThreads, keepAliveSeconds, TimeUnit.SECONDS,
            queueSize == 0 ? new SynchronousQueue<Runnable>()
                : (queueSize < 0 ? new LinkedBlockingQueue<Runnable>() : new LinkedBlockingQueue<Runnable>(queueSize)),
            new NamedExecutorFactory(poolName),
            new AbortPolicyWithReport(poolName)
        ));
        return new ExecutorUtil(listeningExecutorService);
    }



    public <T> ExecuteResult<T> submit(Callable<T> task) {
        final ListenableFuture<T> listenableFuture = this.listeningExecutorService.submit(task);
        final ExecuteResult<T> executeResult = ExecuteResult.prepare(listenableFuture);
        Futures.addCallback(listenableFuture, new FutureCallback<T>() {
            @Override
            public void onSuccess(T result) {

            }

            @Override
            public void onFailure(Throwable t) {
                executeResult.setFailure(t);
            }
        }, listeningExecutorService);
        return executeResult;
    }

    public void execute(Runnable task) {
        final ListenableFuture<?> listenableFuture = this.listeningExecutorService.submit(task);
        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailure(Throwable t) {
                // Print exception
                System.err.println(Throwables.getStackTraceAsString(t));
            }
        }, listeningExecutorService);
    }




    public boolean isTerminated() {
        return this.listeningExecutorService.isTerminated();
    }

    public void shutdown() {
        this.listeningExecutorService.shutdown();
    }
}
