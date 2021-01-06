package com.shuibo.tank;

import com.shuibo.tank.fireStrategy.FireStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Tank {
    private static final int WIDTH = ImageManager.tankUImage.getWidth(), HEIGHT = ImageManager.tankUImage.getHeight(),
            GAME_WIDTH = TankWarFrame.getGameWidth(), GAME_HEIGHT = TankWarFrame.getGameHeight(),
            SPEED = ConstantManager.getValue("TANK_SPEED");
    private final Rectangle rectangle;
    private int x, y;           //坦克的位置
    private Dir dir = Dir.UP;   //坦克的朝向
    private boolean isMoving = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        rectangle = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(ArrayList<Dir> dir) {
        if (dir.isEmpty()) isMoving = false;
        else isMoving = this.dir == (this.dir = dir.get(dir.size() - 1));
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void fire(HashSet<Bullet> bullets, FireStrategy fireStrategy) {
        fireStrategy.fire(bullets, this);
    }

    public void paint(Graphics graphics) {
        switch (dir) {
            case UP:
                graphics.drawImage(ImageManager.tankUImage, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ImageManager.tankDImage, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ImageManager.tankLImage, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ImageManager.tankRImage, x, y, null);
        }
        if (isMoving) updateXY();
    }

    public void updateXY() {
        switch (dir) {
            case UP:
                y = (Math.max(y -= SPEED, 30));
                break;
            case DOWN:
                y = (Math.min(y += SPEED, GAME_HEIGHT - HEIGHT));
                break;
            case LEFT:
                x = (Math.max(x -= SPEED, 0));
                break;
            case RIGHT:
                x = (Math.min(x += SPEED, GAME_WIDTH - WIDTH));
        }
        rectangle.x = this.x;
        rectangle.y = this.y;
    }
}
