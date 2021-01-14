package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.*;

public class TankTankCollision implements Collision {
    private TankTankCollision() {
    }

    public static TankTankCollision getInstance() {
        return TankTankCollisionHandler.INSTANCE;
    }

    @Override
    public void update(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Tank && gameObject2 instanceof Tank) {
            Tank tank1 = (Tank) gameObject1, tank2 = (Tank) gameObject2;
            if (tank1.getRectangle().intersects(tank2.getRectangle())) {
                //todo: 掉头
                tank1.setX(tank1.getPreX());
                tank1.setY(tank1.getPreY());
                tank2.setX(tank2.getPreX());
                tank2.setY(tank2.getPreY());
                tank1.setDir(tank1.getDir().getReverse());
                tank2.setDir(tank2.getDir().getReverse());
            }
        }
    }

    private static class TankTankCollisionHandler {
        private static final TankTankCollision INSTANCE = new TankTankCollision();
    }
}
