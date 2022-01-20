package com.company;

import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String args[]){
        masa m = new masa();
        m.yemekSayisi = 0;

        producer asci = new producer(m);
        consumer yamyam = new consumer(m);

        asci.start();
        yamyam.start();
    }
}

class producer extends Thread{
    masa m;
    public producer(masa m){
        this.m = m;
    }

    public void run(){
        while (true){
            if (m.yemekSayisi<15) {
                m.yemekSayisi++;
                System.out.println("ASCI yemegi yapti : "+ m.yemekSayisi);
            } else {
                System.out.println("ASCI Beklemede");
            }

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {

            }
        }
    }
}

class consumer extends Thread{
    masa m;
    public consumer(masa m){
        this.m = m;
    }
    public void run(){
        int i=0;
        while (true){
            if (m.yemekSayisi>0) {
                m.yemekSayisi--;
                System.out.println("Yam Yam yemegi yedi : "+ m.yemekSayisi);
            } else {
                System.out.println("Yam Yam Beklemede");
            }
            int sleep = 1000;

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e){

            }
        }
    }
}

class masa{
    int yemekSayisi;
}