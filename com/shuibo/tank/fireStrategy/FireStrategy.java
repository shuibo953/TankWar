package com.shuibo.tank.fireStrategy;

import com.shuibo.tank.Bullet;
import com.shuibo.tank.Tank;

import java.util.HashSet;

public interface FireStrategy {
    void fire(HashSet<Bullet> bullets, Tank tank);
}
