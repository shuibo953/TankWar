package com.shuibo.game.fireStrategy;

import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.tank.Tank;

import java.util.HashSet;

public interface FireStrategy {
    void fire(HashSet<Bullet> bullets, Tank tank);
}