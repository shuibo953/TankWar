package com.shuibo.game.fireStrategy;

import com.shuibo.game.GameObject;
import com.shuibo.game.Bullet;
import com.shuibo.game.Dir;
import com.shuibo.game.Tank;

import java.util.ArrayList;

public enum FourDirectionFireStrategy implements FireStrategy {
    STRATEGY;

    @Override
    public ArrayList<GameObject> fire(Tank tank) {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        for (Dir dir : Dir.values()) {
            gameObjects.add(new Bullet(tank, dir));
        }
        return gameObjects;
    }
}
