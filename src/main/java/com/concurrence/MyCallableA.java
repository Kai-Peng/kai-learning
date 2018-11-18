package com.concurrence;

import java.util.concurrent.Callable;

public class MyCallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        try {
            System.out.println("callA begin "+Thread.currentThread().getName()+" "+
                    System.currentTimeMillis());
            Thread.sleep(10000);
            System.out.println("callA end "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "returnA";
    }
}
