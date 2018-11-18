package com.concurrence;

public class Test2 {
    public static class MyThreadGroup extends ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            super.uncaughtException(t, e);
            System.out.println("线程组的异常处理");
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread {
        private String num = "a";

        public MyThread() {
            super();
        }

        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            int numInt = Integer.parseInt(num);
            System.out.println("在线程中打印："+(num+1));
        }
    }

    public static class ObjectUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("对象的异常处理");
            e.printStackTrace();
        }
    }

    public static class StateUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("金泰的异常处理");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThreadGroup group = new MyThreadGroup("kai的线程组");
        MyThread myThread = new MyThread(group,"kai的线程啊啊");
//        MyThread myThread = new MyThread();
        myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
        MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
        myThread.start();
    }
}
