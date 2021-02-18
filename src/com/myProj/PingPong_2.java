package com.myProj;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong_2 extends Thread {
    private final static ReentrantLock flag = new ReentrantLock();
    private final static Condition condition = flag.newCondition();

    private final String printString;

    PingPong_2 (String str) {
        this.printString = str;
    }

    @Override
    public void run () {
        while (true) {
            flag.lock();
            try {
                condition.signalAll();
                condition.await();
                System.out.println(this.printString);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                flag.unlock();
            }
        }

    }

    public static void main(String[] args) {
        PingPong_2 ping = new PingPong_2("ping");
        PingPong_2 pong = new PingPong_2("pong");
        ping.start();
        pong.start();

    }



}
