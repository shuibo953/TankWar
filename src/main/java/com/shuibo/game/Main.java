package com.shuibo.game;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameFrame gameFrame = GameFrame.getInstance();
        while (true) {
            Thread.sleep(25);
            gameFrame.repaint();
        }
    }
}
