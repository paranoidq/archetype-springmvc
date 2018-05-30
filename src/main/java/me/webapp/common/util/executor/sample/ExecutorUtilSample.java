package me.webapp.common.util.executor.sample;


import me.webapp.common.util.executor.ExecuteResult;
import me.webapp.common.util.executor.ExecutorUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class ExecutorUtilSample {

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return 1 + 1;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorUtil util = ExecutorUtil.create("sample", 10, 10, 10, 60);
        for (int i=0; i< 1000; i++) {
            ExecuteResult<Integer> result = util.submit(new Task());
            int j = result.syncGet();
            System.out.println(j);
        }
    }
}
