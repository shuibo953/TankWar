package com.shuibo.game;

import com.shuibo.game.unchangeable.Audio;
import com.shuibo.game.unchangeable.ConstantManager;
import com.shuibo.game.unchangeable.ImageManager;

import java.awt.*;

public class Explode {
    private final int x, y, LIMIT = Integer.parseInt(ConstantManager.getValue("EXPLODE_SPEED"));
    private int count = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(ImageManager.explodes[count++], x, y, null);
    }

    public boolean isExploded() {
        return count >= LIMIT;
    }
}
