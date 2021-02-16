package com.myProj;

import java.util.concurrent.locks.ReentrantLock;

public class PingPong_2 extends Thread {
    private final static ReentrantLock flag = new ReentrantLock();
    private String printString;

    PingPong_2 (String str) {
        this.printString = str;
    }

    @Override
    public void run () {
        while (true) {
            flag.lock();
            try {
                System.out.println(this.printString);
                flag.notifyAll();
                flag.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                flag.unlock();
            }
        }

    }

    public static void main(String[] args) {
        PingPongSynch ping = new PingPongSynch("ping");
        PingPongSynch pong = new PingPongSynch("pong");
        ping.start();
        pong.start();

    }



}
