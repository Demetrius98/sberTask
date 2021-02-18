package com.myProj;

public class DeadLock extends Thread {
    private static final Object first = new Object();
    private static final  Object second = new Object();

    private void doSomething() {
        System.out.println("Hello");
    }
    private void doSomethingElse() {
        System.out.println("Bye!");
    }

    public void firstSecond() {
        synchronized (first) { // 1
            synchronized (second) {   //3
                doSomething();
            }
        }
    }

    public void secondFirst() {
        synchronized (second) {    //2
            synchronized (first) { //4
                doSomethingElse();
            }
        }
    }

    @Override
    public void run () {
        while (true) {
            firstSecond();
            secondFirst();
        }
    }

    public static void main(String[] args) {
            DeadLock th_1 = new DeadLock();
            DeadLock th_2 = new DeadLock();
            th_1.start();
            th_2.start();
    }

}

