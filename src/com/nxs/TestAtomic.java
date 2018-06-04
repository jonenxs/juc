package com.nxs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *一、原子性 1.5之后java.util.concurrent.atomic包下提供了常用的原子变量
 * 1.volatile保证内存可见性
 * 2.CAS（Compare And Swap） 算法保证数据的原子性
 * CAS算法是硬件对并发操作共享数据的支持
 * CAS包含了三个操作数：
 *      内存值V 预估值 A 更新值 B
 *      当且仅当V==A时，V=B,否则什么都不做
 */
public class TestAtomic {

    public static void main(String[] args) {
        Atomic atomic = new Atomic();

        for (int i = 0; i < 10; i++) {
            new Thread(atomic).start();
        }
    }
}

class Atomic implements Runnable{

//    private volatile int serialNumber = 0;

    private AtomicInteger serialNumber = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }

}