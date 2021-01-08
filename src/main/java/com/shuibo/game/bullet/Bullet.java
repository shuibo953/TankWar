package com.shuibo.game.bullet;

import com.shuibo.game.Dir;
import com.shuibo.game.tank.Tank;
import com.shuibo.game.unchangeable.ConstantManager;
import com.shuibo.game.unchangeable.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private static final int WIDTH = ImageManager.bulletUImage.getWidth(),
            HEIGHT = ImageManager.bulletUImage.getHeight(),
            SPEED = Integer.parseInt(ConstantManager.getValue("BULLET_SPEED"));
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
                image = ImageManager.bulletUImage;
                break;
            case DOWN:
                image = ImageManager.bulletDImage;
                break;
            case LEFT:
                image = ImageManager.bulletLImage;
                break;
            case RIGHT:
                image = ImageManager.bulletRImage;
                break;
            default:
                image = null;
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