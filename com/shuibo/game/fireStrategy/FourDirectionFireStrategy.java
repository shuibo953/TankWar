package com.shuibo.game.fireStrategy;

import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.Dir;
import com.shuibo.game.tank.Tank;

import java.util.HashSet;

public enum FourDirectionFireStrategy implements FireStrategy {
    STRATEGY;

    @Override
    public void fire(HashSet<Bullet> bullets, Tank tank) {
        bullets.add(new Bullet(tank.getX(), tank.getY(), Dir.UP));
        bullets.add(new Bullet(tank.getX(), tank.getY(), Dir.DOWN));
        bullets.add(new Bullet(tank.getX(), tank.getY(), Dir.LEFT));
        bullets.add(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT));
    }
}
