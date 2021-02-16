package com.myProj;

public class PingPong_3 extends Thread {
    private static volatile boolean flag;

    private String printString;
    PingPong_3 (String str) {
        this.printString = str;
    }

    @Override
    public void run () {
        flag = true;

        while (flag) {
            flag = false;
            System.out.println(this.printString);
            this.notifyAll();

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
        }

    }

    public static void main(String[] args) {
        PingPongSynch ping = new PingPongSynch("ping");
        PingPongSynch pong = new PingPongSynch("pong");
        ping.start();
        pong.start();
    }

}
