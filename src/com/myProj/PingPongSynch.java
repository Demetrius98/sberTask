package com.myProj;

public class PingPongSynch extends Thread {
    private static final Object flag = new Object();
    private String printString;

    PingPongSynch (String str) {
        this.printString = str;
    }

    @Override
    public void run () {
        while (true) {
            synchronized (flag) {
                System.out.println(this.printString);
                flag.notifyAll();

                try {
                    flag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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