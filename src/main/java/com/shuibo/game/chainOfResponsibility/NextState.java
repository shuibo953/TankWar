package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.GameModel;
import com.shuibo.game.GameObject;

import java.util.HashSet;

public interface NextState {
    HashSet<GameObject> goingToAdd = GameModel.INSTANCE.getGoingToAdd(), goingToRemove = GameModel.INSTANCE.getGoingToRemove();

    void update(GameObject gameObject);
}
