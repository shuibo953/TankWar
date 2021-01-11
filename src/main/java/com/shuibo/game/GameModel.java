package com.shuibo.game;

import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.bullet.NBomb;
import com.shuibo.game.tank.AutoTank;
import com.shuibo.game.tank.ManualTank;
import com.shuibo.game.tank.Tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public enum GameModel {
    INSTANCE;
    private final int width = Integer.parseInt(PropertyManager.INSTANCE.getValue("gameWidth")),
            height = Integer.parseInt(PropertyManager.INSTANCE.getValue("gameHeight")),
            upLimit = Integer.parseInt(PropertyManager.INSTANCE.getValue("UP_LIMIT"));
    private final ManualTank mainTank;
    private final HashSet<AutoTank> enemies = new HashSet<>();
    private final HashSet<Bullet> bullets = new HashSet<>();
    private final HashSet<Explode> explodes = new HashSet<>();
    private final ArrayList<Dir> mainTankDirs = new ArrayList<>();

    {
        mainTank = new ManualTank(0, upLimit);
        for (int i = 0, enemyAmount = Integer.parseInt(PropertyManager.INSTANCE.getValue("enemyAmount"));
             i < enemyAmount; i++)
            enemies.add(new AutoTank(width / (enemyAmount + 1) * (i + 1), height / 2));
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!mainTankDirs.contains(Dir.UP)) mainTankDirs.add(Dir.UP);
                break;
            case KeyEvent.VK_DOWN:
                if (!mainTankDirs.contains(Dir.DOWN)) mainTankDirs.add(Dir.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if (!mainTankDirs.contains(Dir.LEFT)) mainTankDirs.add(Dir.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if (!mainTankDirs.contains(Dir.RIGHT)) mainTankDirs.add(Dir.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                mainTank.setIsFiring(true);
                break;
            case KeyEvent.VK_CONTROL:
                mainTank.changeFireStrategy();
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                mainTankDirs.remove(Dir.UP);
                break;
            case KeyEvent.VK_DOWN:
                mainTankDirs.remove(Dir.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                mainTankDirs.remove(Dir.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                mainTankDirs.remove(Dir.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                mainTank.setIsFiring(false);
        }
    }

    public void paint(Graphics graphics) {
        bullets.forEach(bullet -> bullet.paint(graphics));
        mainTank.paint(graphics);
        enemies.forEach(tank -> tank.paint(graphics));
        explodes.forEach(explode -> explode.paint(graphics));
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("子弹数量：%d", bullets.size()), 10, 60);
        graphics.drawString(String.format("敌方数量：%d", enemies.size()), 10, 80);
        graphics.drawString(String.format("爆炸数量：%d", explodes.size()), 10, 100);
        nextState();
    }

    private void nextState() {
        ifHitMain();
        ifFireNBomb();
        ifHitEnemy();

        bullets.removeIf(this::isOffRange);
        explodes.removeIf(Explode::isExploded);

        mainTank.setIsMoving_Dir(mainTankDirs);
        mainTank.fire(bullets);
        for (Tank tank : enemies) tank.fire(bullets);
    }

    private void ifHitMain() {
        for (Bullet bullet : bullets) if (isCashing(mainTank, bullet)) System.exit(0);
    }

    private void ifFireNBomb() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.getGroup() == Group.PLAYER && bullet instanceof NBomb) {
                enemies.forEach(tank -> explodes.add(new Explode(tank)));
                enemies.clear();
                iterator.remove();
                break;
            }
        }
    }

    private void ifHitEnemy() {
        Iterator<AutoTank> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Tank enemy = enemyIterator.next();
            int pre = bullets.size();
            bullets.removeIf(bullet -> isCashing(enemy, bullet));
            if (pre != bullets.size()) {
                enemyIterator.remove();
                explodes.add(new Explode(enemy));
            }
        }
    }

    private boolean isCashing(Tank tank, Bullet bullet) {
        return tank.getGroup() != bullet.getGroup() && tank.getRectangle().intersects(bullet.getRectangle());
    }

    private boolean isOffRange(Bullet bullet) {
        int x = bullet.getX(), y = bullet.getY();
        return (x < -Bullet.getWIDTH() || y < upLimit - Bullet.getHEIGHT() || x > width || y > height);
    }
}