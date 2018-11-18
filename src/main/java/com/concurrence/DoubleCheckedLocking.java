package com.concurrence;

import java.util.ArrayList;
import java.util.List;

public class DoubleCheckedLocking {
    public int i;

    private volatile static DoubleCheckedLocking instance;

    public static DoubleCheckedLocking getInstance(){
        if (instance == null) {
            synchronized(DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLocking();
                }
            }
        }
        return instance;
    }

    private DoubleCheckedLocking(){
        i = 3;
    }

    public static void main(String[] args) {
        List<Thread> threadPools = new ArrayList<Thread>(10000);

        for(int i=0; i<1000; i++){
            Thread tempThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    DoubleCheckedLocking testObj = DoubleCheckedLocking.getInstance();
                    System.out.println(testObj.getI());
                }
            });
            ((ArrayList) threadPools).add(tempThread);
        }

        for (Thread tempThread : threadPools){
            tempThread.start();
        }
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
