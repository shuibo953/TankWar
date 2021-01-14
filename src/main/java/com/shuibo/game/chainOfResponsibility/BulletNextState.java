package com.shuibo.game.chainOfResponsibility;

import com.shuibo.game.GameModel;
import com.shuibo.game.GameObject;
import com.shuibo.game.Group;
import com.shuibo.game.Bullet;

public class BulletNextState implements NextState {
    private BulletNextState() {
    }

    public static BulletNextState getInstance() {
        return BulletNextStateHandler.INSTANCE;
    }

    @Override
    public void update(GameObject gameObject) {
        // 1.如果是子弹：移动、是否出界（删除），如果是敌方子弹、是否攻击到玩家 o
        if (!(gameObject instanceof Bullet)) return;
        Bullet bullet = (Bullet) gameObject;
        if (bullet.group.equals(Group.ENEMY)
                && bullet.getRectangle().intersects(GameModel.INSTANCE.getPlayer().getRectangle()))
            System.exit(0);

        int x = bullet.getX(), y = bullet.getY();
        switch (bullet.dir) {
            case UP:
                bullet.setY(bullet.getRectangle().y = y -= Bullet.SPEED);
                break;
            case DOWN:
                bullet.setY(bullet.getRectangle().y = y += Bullet.SPEED);
                break;
            case LEFT:
                bullet.setX(bullet.getRectangle().x = x -= Bullet.SPEED);
                break;
            case RIGHT:
                bullet.setX(bullet.getRectangle().x = x += Bullet.SPEED);
        }
        if (x < -Bullet.WIDTH || y < GameObject.GAME_UP_LIMIT - Bullet.HEIGHT
                || x > GameObject.GAME_WIDTH || y > GameObject.GAME_HEIGHT) goingToRemove.add(gameObject);
    }

    private static class BulletNextStateHandler {
        private static final BulletNextState INSTANCE = new BulletNextState();
    }
}