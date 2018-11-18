package com.concurrence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 extends TimerTask {
    private static Timer timer = new Timer();
    private static int runCount = 0;

    @Override
    public void run() {
        try {
            System.out.println("1 begin run! Time is:" + new Date());
            Thread.sleep(1000);
            System.out.println("1 end run! Time is:" + new Date());
            runCount++;
           /* if (runCount == 5) {
                timer.cancel();
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            Test1 task1 = new Test1();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString1 = "2018-08-18 11:51:00";
            Date dateRef1 = sdf1.parse(dateString1);
            System.out.println("String 1 Time is :" + dateRef1.toLocaleString() + " now time is :" + new Date().toLocaleString());
//            timer.scheduleAtFixedRate(task1,dateRef1,11000);
            timer.schedule(task1,dateRef1,11000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
