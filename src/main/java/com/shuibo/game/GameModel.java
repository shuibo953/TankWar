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
    private final ManualTank mainTank = new ManualTank(0, 30);
    private final HashSet<Bullet> mainBullets = new HashSet<>();
    private final HashSet<AutoTank> enemies = new HashSet<>();
    private final HashSet<Bullet> enemyBullets = new HashSet<>();
    private final HashSet<Explode> explodes = new HashSet<>();
    private final ArrayList<Dir> mainTankDirs = new ArrayList<>();
    private final int width = GameFrame.getInstance().getGameWidth(), height = GameFrame.getInstance().getGameHeight();

    {
        for (int i = 0, enemyAmount = Integer.parseInt(PropertyManager.INSTANCE.getValue("enemyAmount")); i < enemyAmount; i++)
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
        mainBullets.forEach(bullet -> bullet.paint(graphics));
        enemyBullets.forEach(bullet -> bullet.paint(graphics));
        mainTank.paint(graphics);
        enemies.forEach(tank -> tank.paint(graphics));
        explodes.forEach(explode -> explode.paint(graphics));
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("我方子弹数量：%d", mainBullets.size()), 10, 60);
        graphics.drawString(String.format("敌方数量：%d", enemies.size()), 10, 80);
        graphics.drawString(String.format("敌方子弹数量：%d", enemyBullets.size()), 10, 100);
        graphics.drawString(String.format("爆炸数量：%d", explodes.size()), 10, 120);
        nextState();
    }

    private void nextState() {
        ifHitMain();
        ifFireNBomb();
        ifHitEnemy();

        mainBullets.removeIf(this::isOffRange);
        enemyBullets.removeIf(this::isOffRange);
        explodes.removeIf(Explode::isExploded);

        mainTank.setIsMoving_Dir(mainTankDirs);
        mainTank.fire(mainBullets);
        for (Tank enemy : enemies) enemy.fire(enemyBullets);
    }

    private void ifHitMain() {
        for (Bullet bullet : enemyBullets) if (isCashing(mainTank, bullet)) System.exit(0);
    }

    private void ifFireNBomb() {
        Iterator<Bullet> iterator = mainBullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet instanceof NBomb) {
                enemies.forEach(tank -> explodes.add(new Explode(tank.getX(), tank.getY())));
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
            int pre = mainBullets.size();
            mainBullets.removeIf(bullet -> isCashing(enemy, bullet));
            if (pre != mainBullets.size()) {
                enemyIterator.remove();
                explodes.add(new Explode(enemy.getX(), enemy.getY()));
            }
        }
    }

    private boolean isCashing(Tank tank, Bullet bullet) {
        return tank.getRectangle().intersects(bullet.getRectangle());
    }

    private boolean isOffRange(Bullet bullet) {
        int x = bullet.getX(), y = bullet.getY();
        return (x < 0 || y < 0 || x > width || y > height);
    }
}