package com.shuibo.tank;

import java.awt.*;
import java.util.ArrayList;

public class Tank {
    private static final int SIDE = 50, SPEED = 5;
    private final TankWarFrame tankWarFrame;
    private boolean isGoingToMove, isLaunching;
    private int x, y;           //坦克的位置
    private Dir dir = Dir.UP;   //坦克的朝向

    public Tank(int x, int y, TankWarFrame frame) {
        this.x = x;
        this.y = y;
        tankWarFrame = frame;
    }

    public static int getSide() {
        return SIDE;
    }

    public void paint(Graphics graphics) {
        if (isGoingToMove) updateXY();
        if (isLaunching) tankWarFrame.addBullet(new Bullet(x, y, dir));
        graphics.fillRect(x, y, SIDE, SIDE);
    }

    public void setMotion(ArrayList<Dir> dirs) {
        if (isGoingToMove = !dirs.isEmpty()) dir = dirs.get(dirs.size() - 1);
    }

    public void setLaunching(boolean isLaunching) {
        this.isLaunching = isLaunching;
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
}
