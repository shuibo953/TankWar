package com.shuibo.game.tank;

import com.shuibo.game.*;
import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.fireStrategy.DefaultFireStrategy;
import com.shuibo.game.fireStrategy.FireStrategy;
import com.shuibo.game.unchangeable.ConstantManager;
import com.shuibo.game.unchangeable.GameFrame;
import com.shuibo.game.unchangeable.ImageManager;

import java.awt.*;
import java.util.HashSet;

public abstract class Tank {
    private static final int WIDTH = ImageManager.tankUImage.getWidth(), HEIGHT = ImageManager.tankUImage.getHeight(),
            GAME_WIDTH = GameFrame.getGameWidth(), GAME_HEIGHT = GameFrame.getGameHeight(),
            SPEED = Integer.parseInt(ConstantManager.getValue("TANK_SPEED"));
    private final Rectangle rectangle;
    private int x, y;           //坦克的位置
    private FireStrategy fireStrategy = DefaultFireStrategy.STRATEGY;
    private Dir dir = Dir.UP;   //坦克的朝向
    private boolean isFiring;

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

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public FireStrategy getFireStrategy() {
        return fireStrategy;
    }

    public void setFireStrategy(FireStrategy fireStrategy) {
        this.fireStrategy = fireStrategy;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setIsFiring(boolean isFiring) {
        this.isFiring = isFiring;
    }

    public void fire(HashSet<Bullet> bullets) {
        if (isFiring) fireStrategy.fire(bullets, this);
    }

    protected void paintHelper(Graphics graphics) {
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
    }

    protected void move() {
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

    public abstract void paint(Graphics graphics);
}
