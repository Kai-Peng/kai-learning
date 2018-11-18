package kai.peng.trip1;

import com.concurrence.AtomicTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestShortcutPress {

    public AtomicTest test = new AtomicTest();

    public AtomicTest getTest() {
        return test;
    }

    public void setTest(AtomicTest test) {
        this.test = test;
    }

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();
        int ijb = 0;

        TestShortcutPress test = new TestShortcutPress();

        List<Thread> threadList = new ArrayList<Thread>(1000);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            Thread testThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        test.count(ijb);

                    }

                }
            });

            threadList.add(testThread);
        }
    }

    public int count(int i) {
        i++;
        return i;
    }

    public int safeCount(AtomicInteger i) {
        for (; ; ) {
            int j = i.get();

            boolean suc = i.compareAndSet(j, ++j);
            if (suc) {
                break;
            }

        }

        return i.get();
    }
}