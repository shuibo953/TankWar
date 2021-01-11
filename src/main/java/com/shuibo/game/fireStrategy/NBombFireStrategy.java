package com.shuibo.game.fireStrategy;

import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.bullet.NBomb;
import com.shuibo.game.tank.Tank;

import java.util.HashSet;

public enum NBombFireStrategy implements FireStrategy {
    STRATEGY;

    @Override
    public void fire(HashSet<Bullet> bullets, Tank tank) {
        bullets.add(new NBomb(tank));
    }
}