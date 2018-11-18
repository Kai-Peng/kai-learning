package com.concurrence;

import java.util.concurrent.Callable;

public class MyCallableT implements Callable<String> {
    private String userName;
    private long sleepValue;

    public MyCallableT(String userName, long sleepValue) {
        this.userName = userName;
        this.sleepValue = sleepValue;
    }

    @Override
    public String call() throws Exception {
        System.out.println(userName);
        Thread.sleep(sleepValue);
        return "return "+userName;
    }
}
