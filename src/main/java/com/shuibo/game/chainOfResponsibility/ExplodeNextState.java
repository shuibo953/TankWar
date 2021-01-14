package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.Explode;
import com.shuibo.game.GameObject;

public class ExplodeNextState implements NextState {
    private ExplodeNextState() {
    }

    public static ExplodeNextState getInstance() {
        return ExplodeNextStateHandler.INSTANCE;
    }

    @Override
    public void update(GameObject gameObject) {
        if (!(gameObject instanceof Explode)) return;
        Explode explode = (Explode) gameObject;
        explode.setCount(explode.getCount() + 1);
        if (explode.getCount() == Explode.LIMIT) goingToRemove.add(gameObject);
    }

    private static class ExplodeNextStateHandler {
        private static final ExplodeNextState INSTANCE = new ExplodeNextState();
    }
}
