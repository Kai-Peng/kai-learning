package com.concurrence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyRun {
    public static void main(String[] args) {
        List<Callable> callableList = new ArrayList<Callable>();
        callableList.add(new MyCallableA());
        //调用方法
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        ScheduledFuture future1 = executor.scheduleWithFixedDelay(new MyRunnableTestA(), 1, 5, TimeUnit.SECONDS);

        executor.remove((Runnable) future1);
        System.out.println("X==" + System.currentTimeMillis());
        System.out.println("Y==" + System.currentTimeMillis());

    }
}
