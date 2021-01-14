package com.shuibo.game.fireStrategy;

import com.shuibo.game.GameObject;
import com.shuibo.game.Bullet;
import com.shuibo.game.Tank;

import java.util.ArrayList;

public enum DefaultFireStrategy implements FireStrategy {
    STRATEGY;

    @Override
    public ArrayList<GameObject> fire(Tank tank) {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(new Bullet(tank));
        return gameObjects;
    }
}
