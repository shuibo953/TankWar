package com.shuibo.tank.fireStrategy;

import com.shuibo.tank.Bullet;
import com.shuibo.tank.Dir;
import com.shuibo.tank.Tank;

import java.util.HashSet;

public class AllDir implements FireStrategy {
    @Override
    public void fire(HashSet<Bullet> bullets, Tank tank) {
        bullets.add(new Bullet(tank.getX(), tank.getY(), Dir.UP));
        bullets.add(new Bullet(tank.getX(), tank.getY(), Dir.DOWN));
        bullets.add(new Bullet(tank.getX(), tank.getY(), Dir.LEFT));
        bullets.add(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT));
    }
}
