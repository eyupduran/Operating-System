package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Semaphore;


  public class MyClientThread extends Thread {
        static Semaphore semaphore = new Semaphore(3);
        int threadNumber;

        MyClientThread(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        public void run() {

            try {

                System.out.println("Thread istekte bulundu : " + this.threadNumber);
                semaphore.acquire();
                System.out.println("Thread iceride : " + this.threadNumber);

                try {

                    Socket sock = new Socket("127.0.0.1", 6013);
                    InputStream in = sock.getInputStream();
                    BufferedReader bin = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = bin.readLine()) != null)
                        System.out.println("Thread " + this.threadNumber + " Tarihi Aldi : " + line);

                    sock.close();
                } catch (IOException ioe) {
                    System.err.println("Thread Baglanti kuramadi : " + this.threadNumber);
                }

                semaphore.release();
                System.out.println("Thread critical section'dan cikiyor : " + this.threadNumber);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
    }
}

