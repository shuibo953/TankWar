package com.shuibo.game;

import com.shuibo.game.borrowed.Audio;

import java.awt.*;

public class Explode {
    private static final int LIMIT = ImageManager.INSTANCE.getExplodes().size();
    private final int x, y;
    private int count = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(ImageManager.INSTANCE.getExplodes().get(count++), x, y, null);
    }

    public boolean isExploded() {
        return count == LIMIT;
    }
}
