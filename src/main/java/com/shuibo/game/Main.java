package com.shuibo.game;

import com.shuibo.game.unchangeable.GameFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        GameFrame gameFrame = GameFrame.getInstance();
        while (true) {
            Thread.sleep(25);
            gameFrame.repaint();
        }
    }
}
