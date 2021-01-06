package com.shuibo.tank.fireStrategy;

import com.shuibo.tank.Bullet;
import com.shuibo.tank.Tank;

import java.util.HashSet;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(HashSet<Bullet> bullets, Tank tank) {
        bullets.add(new Bullet(tank.getX(), tank.getY(), tank.getDir()));
    }
}
