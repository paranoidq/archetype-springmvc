package me.webapp.common.util.executor;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class ExecuteResult<T> {

    private T result;
    private Throwable t;
    private ListenableFuture<T> listenableFuture;


    private ExecuteResult(ListenableFuture<T> listenableFuture) {
        this.listenableFuture = listenableFuture;
    }

    public static <T> ExecuteResult<T> prepare(ListenableFuture<T> listenableFuture) {
        return new ExecuteResult<T>(listenableFuture);
    }

    public void setFailure(Throwable t) {
        this.t = t;
    }

    /**
     * Get results and wait forever
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public T syncGet() throws ExecutionException, InterruptedException {
        T t = listenableFuture.get();
        this.result = t;
        return this.result;
    }

    /**
     * Get results and wait for given duration
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public T syncGetUntil(long time, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        T t = listenableFuture.get(time, unit);
        this.result = t;
        return this.result;
    }

    /**
     * Try to get result asynchronously
     * May return null, if task is not completed
     * @return
     */
    public Optional<T> tryGet() {
        return Optional.fromNullable(this.result);
    }



}
