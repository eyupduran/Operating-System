package com.company;

import java.util.concurrent.Semaphore;

public class producer extends Thread{
    static Semaphore semaphore = new Semaphore(1);
    masa m;
    public producer(masa m){
        this.m = m;
    }

    public void run(){
        while (true){
            try {
                if (m.yemekSayisi<15) {
                    semaphore.acquire();
                    m.yemekSayisi++;
                    System.out.println("Asci yemegi yapti : "+ m.yemekSayisi);

                } else {
                    System.out.println("Asci Beklemede");
                }
                semaphore.release();
                Thread.sleep(1200);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        }
    }
}