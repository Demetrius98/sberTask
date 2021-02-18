package com.myProj;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class GuaranteedDeadLock extends Thread {
    private static final Object first = new Object();
    private static final  Object second = new Object();
    private final int number;
    private static final CyclicBarrier barrier = new CyclicBarrier(2 );

    private void doSomething() {
        System.out.println("Hello");
    }
    private void doSomethingElse() {
        System.out.println("Bye!");
    }

    public GuaranteedDeadLock(int number) {
        this.number = number;
    }


    public void firstSecond() {
        synchronized (first) { // 1
            try {
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException ignored) {
            }

            System.out.println("Barrier of the 'firstSecond' passed!");
            synchronized (second) {   //3
                doSomething();
            }
        }
    }

    public void secondFirst() {
        synchronized (second) {    //2
            try {
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException ignored) {
            }
            System.out.println("Barrier of the 'secondFirst' passed!");
            synchronized (first) { //4
                doSomethingElse();
            }
        }
    }

    @Override
    public void run () {
        while (true) {
            if (number == 1) {
                firstSecond();
            } else {
                secondFirst();
            }

        }
    }

    public static void main(String[] args) {
        GuaranteedDeadLock th_1 = new GuaranteedDeadLock (1);
        GuaranteedDeadLock th_2 = new GuaranteedDeadLock (2);
        th_1.start();
        th_2.start();
    }

}

