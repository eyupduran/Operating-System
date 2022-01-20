package com.company;

import java.util.concurrent.Semaphore;

public class consumer extends Thread{
    static Semaphore semaphore = new Semaphore(1);
    masa m;
    public consumer(masa m){
        this.m = m;
    }
    public void run(){
        while (true){

            try {

                if (m.yemekSayisi>0) {
                    semaphore.acquire();
                    m.yemekSayisi--;
                    System.out.println("Yam Yam yemegi yedi : "+ m.yemekSayisi);

                } else {
                    System.out.println("Yam Yam Beklemede");
                }
                semaphore.release();
                Thread.sleep(1000);


            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        }

    }
}