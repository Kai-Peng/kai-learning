package com.concurrence;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayTest implements Delayed {
    private long delayNanoTime;
    private String userName;

    public MyDelayTest(long delayNanoTime, String userName) {
        super();
        this.userName = userName;

        TimeUnit tUnit = TimeUnit.SECONDS;
        this.delayNanoTime = System.nanoTime()+tUnit.toNanos(delayNanoTime);
    }

    public long getDelayNanoTime() {
        return delayNanoTime;
    }

    public void setDelayNanoTime(long delayNanoTime) {
        this.delayNanoTime = delayNanoTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(Delayed o) {
        if ((this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS)) < 0) {
            return -1;
        }
        if ((this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS)) > 0) {
            return 1;
        }

        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayNanoTime-System.nanoTime(),TimeUnit.NANOSECONDS);
    }
}
