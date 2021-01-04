package com.shuibo.tank;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        TankWarFrame tankWarFrame = new TankWarFrame();
        while (true) {
            Thread.sleep(25);
            tankWarFrame.repaint();
        }
    }
}
