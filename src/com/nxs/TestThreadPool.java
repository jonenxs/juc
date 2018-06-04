package com.nxs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int j = 0; j <= 100; j++) {
                        sum += j;
                    }
                    return sum;
                }
            });
            list.add(future);
        }
        pool.shutdown();
        for (Future future :
                list) {
            System.out.println(future.get());
        }

    }
}
