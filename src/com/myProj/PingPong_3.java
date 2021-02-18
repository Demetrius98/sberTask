package com.myProj;

public class PingPong_3 extends Thread {
    private static volatile boolean flag = false;
    private final String printString;

    PingPong_3 (String str) {
        this.printString = str;
    }

    public void ping () {
        while (flag) {}
        System.out.println("ping");
        flag = true;
    }

    public void pong () {
        while (!flag) {}
        System.out.println("pong");
        flag = false;
    }

    @Override
    public void run () {
        while (true) {
            if (printString.equals("ping")) {
                ping();
            } else {
                pong();
            }

        }
    }

    public static void main(String[] args) {
        PingPong_3 ping = new PingPong_3("ping");
        PingPong_3 pong = new PingPong_3("pong");
        ping.start();
        pong.start();
    }

}
