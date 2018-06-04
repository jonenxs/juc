package com.nxs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用于解决多线程安全问题的方式
 * synchronized:隐式锁
 * 1.同步代码块
 * 2.同步方法
 * lock：显示锁
 * 3.同步锁
 * 需要通过lock()方法上锁，通过unlock()方法释放锁
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();
    }
}

class Ticket implements Runnable{

    private int tick = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (tick > 0) {
                try {
                    Thread.sleep(20);
                    System.out.println("余票为：" + --tick);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }else {
                break;
            }
        }
    }
}
