package com.company;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        System.out.println("Erisebilir Thread Sayisi : "
                + semaphore.availablePermits());

        MyClientThread t1 = new MyClientThread(1);
        t1.start();

        MyClientThread t2 = new MyClientThread(2);
        t2.start();

        MyClientThread t3 = new MyClientThread(3);
        t3.start();

        MyClientThread t4 = new MyClientThread(4);
        t4.start();

        MyClientThread t5 = new MyClientThread(5);
        t5.start();
    }
}
