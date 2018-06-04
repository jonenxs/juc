package com.nxs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建执行线程的方式三：实现Callable接口
 * 相较于实现Runnable 方式，方法可以有返回值，并且可以抛出异常
 *
 * 执行Callable方式，需要FutureTask实现类的支持，用于接收结果
 * FutureTask是Future接口的实现类,也可用于闭锁操作
 */
public class TestCallable {

    public static void main(String[] args) {
        TestThreadDemo threadDemo = new TestThreadDemo();
        FutureTask<Integer> result = new FutureTask<>(threadDemo);
        new Thread(result).start();
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}


class TestThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Integer sum = 0;

        for (int i = 0; i <= 100; i++) {
            System.out.println(i);
            sum += i;
        }
        return sum;
    }
}
//class TestThreadDemo implements Runnable{
//
//    @Override
//    public void run() {
//
//    }
//}