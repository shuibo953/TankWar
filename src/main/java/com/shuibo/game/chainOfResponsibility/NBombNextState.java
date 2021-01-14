package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.*;
import com.shuibo.game.NBomb;

public class NBombNextState implements NextState {
    private NBombNextState() {
    }

    public static NBombNextState getInstance() {
        return NBombNextStateHandler.INSTANCE;
    }

    @Override
    public void update(GameObject gameObject) {
        if (!(gameObject instanceof NBomb)) return;
        NBomb nBomb = (NBomb) gameObject;
        nBomb.setY(nBomb.getY() + nBomb.speed);
        if (nBomb.getY() >= GameObject.GAME_HEIGHT) {
            nBomb.setY(-nBomb.height);
            goingToRemove.add(nBomb);
        }

        for (GameObject instanceGameObject : GameModel.INSTANCE.getGameObjects()) {
            Tank tank;
            if (instanceGameObject instanceof Tank
                    && (tank = (Tank) instanceGameObject).getGroup().equals(Group.ENEMY)
                    && nBomb.getY() + nBomb.height >= tank.getY()) {
                goingToRemove.add(instanceGameObject);
                goingToAdd.add(new Explode(tank));
            }
        }
    }

    private static class NBombNextStateHandler {
        private static final NBombNextState INSTANCE = new NBombNextState();
    }
}
