package com.shuibo.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankWarFrame tankWarFrame = new TankWarFrame();
        while (true) {
            Thread.sleep(100);
            tankWarFrame.repaint();
        }
    }
}
