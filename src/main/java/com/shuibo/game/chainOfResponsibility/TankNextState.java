package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.*;

import java.util.Random;

public class TankNextState implements NextState {
    private TankNextState() {
    }

    public static TankNextState getInstance() {
        return TankNextStateHandler.INSTANCE;
    }

    @Override
    public void update(GameObject gameObject) {
        // 2.如果是坦克：移动、发射（添加新的子弹） o
        if (!(gameObject instanceof Tank)) return;
        Tank tank = (Tank) gameObject;
        Random random = new Random();
        if (random.nextInt(10) > 8) goingToAdd.addAll(tank.fire());
        if (random.nextInt(100) > 95) tank.setDir(Dir.values()[random.nextInt(4)]);
        int x = tank.getX(), y = tank.getY();
        tank.setPreX(x);
        tank.setPreY(y);
        switch (tank.getDir()) {
            case UP:
                tank.setY(tank.getRectangle().y = Math.max(y - Tank.SPEED, Tank.UP_LIMIT));
                break;
            case DOWN:
                tank.setY(tank.getRectangle().y = Math.min(y + Tank.SPEED, Tank.DOWN_LIMIT));
                break;
            case LEFT:
                tank.setX(tank.getRectangle().x = Math.max(x - Tank.SPEED, Tank.LEFT_LIMIT));
                break;
            case RIGHT:
                tank.setX(tank.getRectangle().x = Math.min(x + Tank.SPEED, Tank.RIGHT_LIMIT));
        }
    }

    private static class TankNextStateHandler {
        private static final TankNextState INSTANCE = new TankNextState();
    }
}
