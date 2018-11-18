package com.concurrence;

import java.util.concurrent.Phaser;

public class PhaserRun {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        PhaserTest.phaser = phaser;

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                PhaserTest.methodA();
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                PhaserTest.methodA();
            }
        });

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                PhaserTest.methodA();
            }
        });

        a.setName("A线程");
        b.setName("B线程");
        c.setName("C线程");

        a.start();
        b.start();
        c.start();
    }
}
