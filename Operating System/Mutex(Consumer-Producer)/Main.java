package com.company;

import java.util.concurrent.Semaphore;

public class Main {
    static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {
        masa m = new masa();
        m.yemekSayisi = 0;

        producer asci = new producer(m);
        //producer asci2 = new producer(m);

        consumer yamyam = new consumer(m);

        asci.start();
       // asci2.start();
        yamyam.start();

    }
}
