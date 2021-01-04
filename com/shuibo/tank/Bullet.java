package com.shuibo.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private static final int WIDTH = ImageManager.bulletUImage.getWidth(),
            HEIGHT = ImageManager.bulletUImage.getHeight(), SPEED = ConstantManager.getValue("BULLET_SPEED");
    private final Dir dir;
    private final BufferedImage image;
    private final Rectangle rectangle;
    private int x, y;

    public Bullet(int tankX, int tankY, Dir dir) {
        this.x = tankX + (Tank.getWidth() - WIDTH) / 2;
        this.y = tankY + (Tank.getHeight() - HEIGHT) / 2;
        rectangle = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
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
        rectangle.x = this.x;
        rectangle.y = this.y;
    }
}
