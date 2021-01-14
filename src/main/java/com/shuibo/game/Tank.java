package com.shuibo.game;

import com.shuibo.game.fireStrategy.DefaultFireStrategy;
import com.shuibo.game.fireStrategy.FireStrategy;
import com.shuibo.game.fireStrategy.FourDirectionFireStrategy;
import com.shuibo.game.fireStrategy.NBombFireStrategy;

import java.awt.*;
import java.util.ArrayList;

public class Tank extends GameObject {
    public static final int SPEED = Integer.parseInt(PropertyManager.INSTANCE.getValue("TANK_SPEED")),
            WIDTH = ImageManager.INSTANCE.getTankUImage().getWidth(),
            HEIGHT = ImageManager.INSTANCE.getTankUImage().getHeight(),
            UP_LIMIT = GameObject.GAME_UP_LIMIT,
            DOWN_LIMIT = GameObject.GAME_HEIGHT - HEIGHT,
            LEFT_LIMIT = 0,
            RIGHT_LIMIT = GameObject.GAME_WIDTH - HEIGHT;
    public final Group group;
    private final Rectangle rectangle;
    private FireStrategy fireStrategy = DefaultFireStrategy.STRATEGY;
    private Dir dir = Dir.UP;
    private int x, y, preX, preY;

    public Tank(int x, int y, Group group) {
        preX = this.x = x;
        preY = this.y = y;
        this.group = group;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getPreX() {
        return preX;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public int getPreY() {
        return preY;
    }

    public void setPreY(int preY) {
        this.preY = preY;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public ArrayList<GameObject> fire() {
        return fireStrategy.fire(this);
    }

    public void changeFireStrategy() {
        if (fireStrategy.equals(DefaultFireStrategy.STRATEGY)) {
            fireStrategy = FourDirectionFireStrategy.STRATEGY;
        } else if (fireStrategy.equals(FourDirectionFireStrategy.STRATEGY)) {
            fireStrategy = NBombFireStrategy.STRATEGY;
        } else if (fireStrategy.equals(NBombFireStrategy.STRATEGY)) {
            fireStrategy = DefaultFireStrategy.STRATEGY;
        }
    }

    public void move() {//todo: 边缘检测由TankAction完成
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

    public void paint(Graphics graphics) {
        switch (dir) {
            case UP:
                graphics.drawImage(ImageManager.INSTANCE.getTankUImage(), x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ImageManager.INSTANCE.getTankDImage(), x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ImageManager.INSTANCE.getTankLImage(), x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ImageManager.INSTANCE.getTankRImage(), x, y, null);
        }
    }
}
