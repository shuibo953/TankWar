package com.shuibo.game;

import com.shuibo.game.*;
import com.shuibo.game.imageFactory.ImageFactory1;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {
    public static final int SPEED = Integer.parseInt(PropertyManager.INSTANCE.getValue("BULLET_SPEED")),
            WIDTH = ImageManager.INSTANCE.getBulletUImage().getWidth(),
            HEIGHT = ImageManager.INSTANCE.getBulletUImage().getHeight();
    public final Dir dir;
    public final Group group;
    private final BufferedImage image;
    private final Rectangle rectangle;
    private int x, y;
    // 核弹：垂直移动y image

    public Bullet(Tank tank) {
        x = tank.getX() + (Tank.WIDTH - WIDTH) / 2;
        y = tank.getY() + (Tank.HEIGHT - HEIGHT) / 2;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        group = tank.getGroup();
        switch (dir = tank.getDir()) {
            case UP:
                image = ImageManager.INSTANCE.getBulletUImage();
                break;
            case DOWN:
                image = ImageManager.INSTANCE.getBulletDImage();
                break;
            case LEFT:
                image = ImageManager.INSTANCE.getBulletLImage();
                break;
            case RIGHT:
                image = ImageManager.INSTANCE.getBulletRImage();
                break;
            default:
                image = null;
        }
        imageAdjustment();
    }

    public Bullet(Tank tank, Dir dir) {
        x = tank.getX() + (Tank.WIDTH - WIDTH) / 2;
        y = tank.getY() + (Tank.HEIGHT - HEIGHT) / 2;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        group = tank.getGroup();
        switch (this.dir = dir) {
            case UP:
                image = ImageManager.INSTANCE.getBulletUImage();
                break;
            case DOWN:
                image = ImageManager.INSTANCE.getBulletDImage();
                break;
            case LEFT:
                image = ImageManager.INSTANCE.getBulletLImage();
                break;
            case RIGHT:
                image = ImageManager.INSTANCE.getBulletRImage();
                break;
            default:
                image = null;
        }
        imageAdjustment();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }

    private void imageAdjustment() {
        // ImageFactory2使用的子弹图片不是对称的
        if (ImageManager.INSTANCE.getImageFactory() instanceof ImageFactory1) return;
        switch (this.dir) {
            case UP:
                ++x;
                break;
            case DOWN:
                --x;
                break;
            case LEFT:
                --y;
                break;
            case RIGHT:
                ++y;
        }
    }
}