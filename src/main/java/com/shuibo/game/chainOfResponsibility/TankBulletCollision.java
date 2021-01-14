package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.*;

public class TankBulletCollision implements Collision {
    private TankBulletCollision() {
    }

    public static TankBulletCollision getInstance() {
        return TankBulletCollisionHandler.INSTANCE;
    }

    @Override
    public void update(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Tank && gameObject2 instanceof Bullet) {
            Tank tank = (Tank) gameObject1;
            Bullet bullet = (Bullet) gameObject2;
            if (bullet.group.equals(Group.PLAYER) && tank.getRectangle().intersects(bullet.getRectangle())) {
                goingToRemove.add(tank);
                goingToAdd.add(new Explode(tank));
                goingToRemove.add(bullet);
            }
        } else if (gameObject1 instanceof Bullet && gameObject2 instanceof Tank) {
            update(gameObject2, gameObject1);
        }
    }

    private static class TankBulletCollisionHandler {
        private static final TankBulletCollision INSTANCE = new TankBulletCollision();
    }
}
