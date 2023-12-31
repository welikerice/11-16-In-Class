package org.example.concurrency;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyObjects {

    public static final int NUM = 1;
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<NUM; i++) {
                    counter.increment();
                    counter.decrement();
                    counter.increment();
                    System.out.println("Thread: " + Thread.currentThread().getName() + " Value: "+ counter.value());
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i=0; i<NUM; i++) {
                counter.decrement();
                counter.increment();
                counter.decrement();
                System.out.println("Thread: " + Thread.currentThread().getName() + " Value: "+ counter.value());
            }
        });

        Thread t3 = new Thread(() -> {
            for(int i=0; i<NUM; i++) {
                counter.increment();
                counter.decrement();
                counter.increment();
                System.out.println("Thread: " + Thread.currentThread().getName() + " Value: "+ counter.value());
            }
        });

        Thread t4 = new Thread(() -> {
            for(int i=0; i<NUM; i++) {
                counter.decrement();
                counter.increment();
                counter.decrement();
                System.out.println("Thread: " + Thread.currentThread().getName() + " Value: "+ counter.value());
            }
        });

        t1.start();
        t2.start();
//        t3.start();
//        t4.start();
        t1.join();
        t2.join();
        System.out.println(counter.value());
    }
}

class AtomicCounter {
    private AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        c.incrementAndGet();
    }

    public void decrement() {
        c.decrementAndGet();
    }

    public int value() {
        return c.get();
    }

}
