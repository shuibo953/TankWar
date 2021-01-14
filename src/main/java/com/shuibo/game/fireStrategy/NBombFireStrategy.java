package com.shuibo.game.fireStrategy;

import com.shuibo.game.GameModel;
import com.shuibo.game.GameObject;
import com.shuibo.game.NBomb;
import com.shuibo.game.Tank;

import java.util.ArrayList;

public enum NBombFireStrategy implements FireStrategy {
    STRATEGY;

    @Override
    public ArrayList<GameObject> fire(Tank tank) {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        if (!GameModel.INSTANCE.getGameObjects().contains(NBomb.getINSTANCE())) gameObjects.add(NBomb.getINSTANCE());
        return gameObjects;
    }
}