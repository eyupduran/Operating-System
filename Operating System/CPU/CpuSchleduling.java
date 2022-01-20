package com.company;


import java.util.Arrays;

public class Main {

    static int totalArray(int burstTimes[]){
        int totalBurstTime=0;
        for(int i=0;i<burstTimes.length;i++){
            totalBurstTime+=burstTimes[i];
        }
        return totalBurstTime;
    }
    static int[] findWaitingTime(int bt[]) {

        int n = bt.length;
        int wt[] = new int[n];

        wt[0] = 0;

        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }

        return wt;
    }

    static int[] findTurnAroundTime(int bt[], int wt[]) {
        int n = bt.length;
        int tat[] = new int[n];

        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }

        return tat;
    }
    static int[] sortArray(int burstTimes[]) {
        int temp;
        int n = burstTimes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (burstTimes[j] < burstTimes[i]) {
                    temp = burstTimes[i];
                    burstTimes[i] = burstTimes[j];
                    burstTimes[j] = temp;
                }
            }
        }
        return burstTimes;
    }
// RRAvgWaitingTime fonksiyonunun girdi olarak verilen processes, burstTime, arrivalTime
// ve quantum parametrelerine göre average waiting time'ı Round Robin yöntemi ile hesaplaması beklenmektedir.
    public static float RRAvgWaitingTime(int[] processes, int[] burstTimes, int[] arrivalTimes, int quantum) {
        int totalElapsedTime = 0;
        int totalBurstTime=totalArray(burstTimes);
        int temp=0;
        float totalWaitingTime=(float)0;
        int totalExitTime=0;
        int totalArriveAndBurstTime=0;
        float avgWaitingTime = (float) 0;
        int n=burstTimes.length;

        for (int i = 0; i < processes.length; i++) {
            totalArriveAndBurstTime=totalArriveAndBurstTime+arrivalTimes[i]+burstTimes[i];
        }
        while (totalElapsedTime<totalBurstTime) {
            for (int i = 0; i < processes.length; i++) {
                if (arrivalTimes[i] <= totalElapsedTime && burstTimes[i] != 0) {
                    if(burstTimes[i]<=quantum){
                        totalElapsedTime += burstTimes[i];
                         System.out.println("Process "+ (i+1) +" ---> "+  burstTimes[i]+"  saniye çalıştı");
                        burstTimes[i] =0;
                        totalExitTime=totalExitTime+totalElapsedTime;
                    }
                    else {
                        burstTimes[i] = burstTimes[i] - quantum;
                        System.out.println("Process "+ (i+1) +" ---> "+  quantum+"  saniye çalıştı");
                        totalElapsedTime += quantum;
                    }
                }
                else {
                    temp=i;
                }

            }
              if(arrivalTimes[temp]<=totalElapsedTime ){
                        if(burstTimes[temp]<=quantum){
                            totalElapsedTime += burstTimes[temp];
                            System.out.println( "Process "+ (temp+1) +" ---> "+  burstTimes[temp]+"  saniye çalıştı");
                            burstTimes[temp] =0;
                            totalExitTime=totalExitTime+totalElapsedTime;
                        }
                        else {
                            burstTimes[temp] = burstTimes[temp] - quantum;
                            System.out.println( "Process "+ (temp+1) +" ---> "+ quantum +"  saniye çalıştı");
                            totalElapsedTime += quantum;
                        }
                    }
        }
        totalWaitingTime=totalExitTime-totalArriveAndBurstTime;
        avgWaitingTime=totalWaitingTime/(n);
        return avgWaitingTime;
    }

    //SJFAvgTurnAroundTime fonksiyonunun girdi olarak verilen processes ve burstTime parametrelerine
    // göre average turn around time'ı Shortest Job First yöntemi ile hesaplaması beklenmektedir.
    public static float SJFAvgTurnAroundTime(int[] processes, int[] burstTimes) {

        int n=burstTimes.length;
        int wt[] = new int[n];
        int tat[] = new int[n];

        float avgTurnaroundTime = (float)0;
        float total_tat = 0;

        sortArray(burstTimes);
        wt = findWaitingTime(burstTimes);
        tat= findTurnAroundTime(burstTimes,wt);


        for (int i = 0;i< tat.length; i++) {
            total_tat = total_tat + tat[i];
        }

        avgTurnaroundTime=(total_tat/(tat.length));
        return avgTurnaroundTime;
    }


    public static void main (String[]args){

        int processes[] = {1,2,3};

        int burst_time[] = {5,7,6};

        int arrivalTimes[] = {0,5,3};

            int quantum = 3;

            //  findavgTime(processes, burst_time);
            //  SJFAvgTurnAroundTime(processes,burst_time);
            float a = RRAvgWaitingTime(processes, burst_time, arrivalTimes, quantum);
            System.out.println("Ortalama bekleme zamanı= " + a);
        }
    }

