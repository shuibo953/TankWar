package com.shuibo.tank;

import java.awt.*;

public class Bullet {
    private int x, y;
    private static final int SIDE = 5, SPEED = 10;
    private final Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x + (Tank.getSide() - SIDE) / 2;
        this.y = y + (Tank.getSide() - SIDE) / 2;
        this.dir = dir;
    }

    public void paint(Graphics graphics) {
        updateXY();
        graphics.fillRect(x, y, SIDE, SIDE);
    }

    private void updateXY() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
