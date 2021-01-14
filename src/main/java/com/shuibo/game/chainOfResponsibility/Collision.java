package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.GameModel;
import com.shuibo.game.GameObject;

import java.util.HashSet;

public interface Collision {
    HashSet<GameObject> goingToAdd = GameModel.INSTANCE.getGoingToAdd(), goingToRemove = GameModel.INSTANCE.getGoingToRemove();

    void update(GameObject object1, GameObject object2);
}
