package com.shuibo.game.bullet;

import com.shuibo.game.Dir;
import com.shuibo.game.imageFactory.ImageFactory1;
import com.shuibo.game.tank.Tank;
import com.shuibo.game.PropertyManager;
import com.shuibo.game.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private static final int WIDTH = ImageManager.INSTANCE.bulletUImage.getWidth(),
            HEIGHT = ImageManager.INSTANCE.bulletUImage.getHeight(),
            SPEED = Integer.parseInt(PropertyManager.INSTANCE.getValue("BULLET_SPEED"));
    private final Dir dir;
    private final BufferedImage image;
    private final Rectangle rectangle;
    private int x, y;

    public Bullet(int tankX, int tankY, Dir dir) {
        x = tankX + (Tank.getWidth() - WIDTH) / 2;
        y = tankY + (Tank.getHeight() - HEIGHT) / 2;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        switch (this.dir = dir) {
            case UP:
                image = ImageManager.INSTANCE.bulletUImage;
                break;
            case DOWN:
                image = ImageManager.INSTANCE.bulletDImage;
                break;
            case LEFT:
                image = ImageManager.INSTANCE.bulletLImage;
                break;
            case RIGHT:
                image = ImageManager.INSTANCE.bulletRImage;
                break;
            default:
                image = null;
        }
        // ImageFactory2使用的子弹图片不是对称的
        if (ImageManager.INSTANCE.imageFactory instanceof ImageFactory1) return;
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
}