package com.shuibo.game.tank;

import com.shuibo.game.*;
import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.fireStrategy.DefaultFireStrategy;
import com.shuibo.game.fireStrategy.FireStrategy;
import com.shuibo.game.PropertyManager;
import com.shuibo.game.GameFrame;
import com.shuibo.game.ImageManager;

import java.awt.*;
import java.util.HashSet;

public abstract class Tank {
    private static final int SPEED = Integer.parseInt(PropertyManager.INSTANCE.getValue("TANK_SPEED")),
            WIDTH = ImageManager.INSTANCE.tankUImage.getWidth(),
            HEIGHT = ImageManager.INSTANCE.tankUImage.getHeight(),
            UP_LIMIT = 30, DOWN_LIMIT = GameFrame.getInstance().getGameHeight() - HEIGHT,
            LEFT_LIMIT = 0, RIGHT_LIMIT = GameFrame.getInstance().getGameWidth() - WIDTH;
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
                graphics.drawImage(ImageManager.INSTANCE.tankUImage, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ImageManager.INSTANCE.tankDImage, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ImageManager.INSTANCE.tankLImage, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ImageManager.INSTANCE.tankRImage, x, y, null);
        }
    }

    protected void move() {
        switch (dir) {
            case UP:
                rectangle.y = y = (Math.max(y -= SPEED, UP_LIMIT));
                break;
            case DOWN:
                rectangle.y = y = (Math.min(y += SPEED, DOWN_LIMIT));
                break;
            case LEFT:
                rectangle.x = x = (Math.max(x -= SPEED, LEFT_LIMIT));
                break;
            case RIGHT:
                rectangle.x = x = (Math.min(x += SPEED, RIGHT_LIMIT));
        }
    }

    public abstract void paint(Graphics graphics);
}
