package com.myProj;

import java.util.concurrent.locks.ReentrantLock;

public class GuaranteedDeadLock extends Thread {
    private final static ReentrantLock firstLock = new ReentrantLock();
    private final static ReentrantLock secondLock = new ReentrantLock();
    private static volatile boolean flag;
    private int idThread;

    public GuaranteedDeadLock(int idThread) {
        this.idThread = idThread;
    }

    private void firstSecondMeth () {
        firstLock.lock();
        try {
            flag = true;

            GuaranteedDeadLock th_2 = new GuaranteedDeadLock(2);
            th_2.start();

            while (flag == true) {}

            secondLock.lock();
            try {
                System.out.println("Hello!");
            }
            finally {
                secondLock.unlock();
            }

        }
        finally {
            firstLock.unlock();
        }

    }

    private void secondFirstMeth () {
        secondLock.lock();
        try {
            flag = false;

            firstLock.lock();
            try {
                System.out.println("Bye!");
            }
            finally {
                firstLock.unlock();
            }

        }
        finally {
            secondLock.unlock();
        }

    }

    @Override
    public void run () {
        if (idThread == 1) {
            while (true) {
                firstSecondMeth();
            }
        }
        else {
            while (true) {
                secondFirstMeth();
            }
        }
    }

    public static void main(String[] args) {
            GuaranteedDeadLock th_1 = new GuaranteedDeadLock(1);
            //GuaranteedDeadLock th_2 = new GuaranteedDeadLock(2);
            th_1.start();
            //th_2.start();
    }

}

