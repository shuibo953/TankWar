package com.shuibo.game.fireStrategy;

import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.Dir;
import com.shuibo.game.tank.Tank;

import java.util.HashSet;

public enum FourDirectionFireStrategy implements FireStrategy {
    STRATEGY;

    @Override
    public void fire(HashSet<Bullet> bullets, Tank tank) {
        for (Dir value : Dir.values()) {
            bullets.add(new Bullet(tank, value));
        }
    }
}
