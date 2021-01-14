package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.GameObject;

import java.util.HashSet;

public class ChainOfNextState implements NextState {
    private final HashSet<NextState> nextStates = new HashSet<>();

    private ChainOfNextState() {
        nextStates.add(NBombNextState.getInstance());
        nextStates.add(BulletNextState.getInstance());
        nextStates.add(ExplodeNextState.getInstance());
        nextStates.add(TankNextState.getInstance());
    }

    public static ChainOfNextState getInstance() {
        return ChainOfNextStateHandler.INSTANCE;
    }

    @Override
    public void update(GameObject gameObject) {
        nextStates.forEach(nextState -> nextState.update(gameObject));
    }

    private static class ChainOfNextStateHandler {
        private static final ChainOfNextState INSTANCE = new ChainOfNextState();
    }
}
