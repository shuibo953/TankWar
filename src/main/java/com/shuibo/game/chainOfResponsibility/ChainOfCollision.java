package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.GameObject;

import java.util.HashSet;

public class ChainOfCollision implements Collision {
    private final HashSet<Collision> collisions = new HashSet<>();

    private ChainOfCollision() {
        collisions.add(TankBulletCollision.getInstance());
        collisions.add(TankTankCollision.getInstance());
    }

    public static ChainOfCollision getInstance() {
        return ChainOfCollisionHandler.INSTANCE;
    }

    @Override
    public void update(GameObject object1, GameObject object2) {
        collisions.forEach(collision -> collision.update(object1, object2));
    }

    private static class ChainOfCollisionHandler {
        private static final ChainOfCollision INSTANCE = new ChainOfCollision();
    }
}
