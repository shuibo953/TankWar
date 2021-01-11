package com.shuibo.game.bullet;

import com.shuibo.game.Dir;
import com.shuibo.game.Group;
import com.shuibo.game.imageFactory.ImageFactory1;
import com.shuibo.game.tank.ManualTank;
import com.shuibo.game.tank.Tank;
import com.shuibo.game.PropertyManager;
import com.shuibo.game.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private static final int WIDTH = ImageManager.INSTANCE.getBulletUImage().getWidth(),
            HEIGHT = ImageManager.INSTANCE.getBulletUImage().getHeight(),
            SPEED = Integer.parseInt(PropertyManager.INSTANCE.getValue("BULLET_SPEED"));
    private final Dir dir;
    private final BufferedImage image;
    private final Rectangle rectangle;
    private final Group group;
    private int x, y;

    public Bullet(Tank tank) {
        x = tank.getX() + (Tank.getWidth() - WIDTH) / 2;
        y = tank.getY() + (Tank.getHeight() - HEIGHT) / 2;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        group = (tank instanceof ManualTank ? Group.PLAYER : Group.ENEMY);
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
        x = tank.getX() + (Tank.getWidth() - WIDTH) / 2;
        y = tank.getY() + (Tank.getHeight() - HEIGHT) / 2;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        group = (tank instanceof ManualTank ? Group.PLAYER : Group.ENEMY);
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

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
        updateXY();
    }

    private void updateXY() {
        switch (dir) {
            case UP:
                rectangle.y = y -= SPEED;
                break;
            case DOWN:
                rectangle.y = y += SPEED;
                break;
            case LEFT:
                rectangle.x = x -= SPEED;
                break;
            case RIGHT:
                rectangle.x = x += SPEED;
        }
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

    public Group getGroup() {
        return group;
    }
}