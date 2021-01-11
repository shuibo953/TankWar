package com.shuibo.game.fireStrategy;

import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.tank.Tank;

import java.util.HashSet;

public enum DefaultFireStrategy implements FireStrategy {
    STRATEGY;

    @Override
    public void fire(HashSet<Bullet> bullets, Tank tank) {
        bullets.add(new Bullet(tank));
    }
}
