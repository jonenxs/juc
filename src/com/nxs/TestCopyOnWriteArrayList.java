package com.nxs;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * CopyOnWriteArrayList/CopyOnWriteArraySet 写入并复制
 * 添加操作多时，效率低 因为每次添加都会复制开销大。并发迭代多时效率高
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {

        TestThread testThread = new TestThread();
        for (int i = 0; i < 10; i++) {
            new Thread(testThread).start();
        }
    }


}


class TestThread implements Runnable{

//    public static List<String> list = Collections.synchronizedList(new ArrayList<>());

    public static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> iterators = list.iterator();
        while (iterators.hasNext()) {
            System.out.println(iterators.next());

            list.add("AA");
        }
    }
}
