package com.shuibo.game;

import com.shuibo.game.bullet.Bullet;
import com.shuibo.game.bullet.NBomb;
import com.shuibo.game.tank.AutoTank;
import com.shuibo.game.tank.ManualTank;
import com.shuibo.game.tank.Tank;
import com.shuibo.game.unchangeable.ConstantManager;
import com.shuibo.game.unchangeable.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class MemberManager {
    private static final ManualTank mainTank = new ManualTank(0, 30);
    private static final HashSet<Bullet> mainBullets = new HashSet<>();
    private static final HashSet<AutoTank> enemies = new HashSet<>();
    private static final HashSet<Bullet> enemyBullets = new HashSet<>();
    private static final HashSet<Explode> explodes = new HashSet<>();
    private static final ArrayList<Dir> mainTankDirs = new ArrayList<>();

    static {
        for (int i = 0, enemyAmount = Integer.parseInt(ConstantManager.getValue("enemyAmount")); i < enemyAmount; i++)
            enemies.add(new AutoTank(GameFrame.getGameWidth() / (enemyAmount + 1) * (i + 1), GameFrame.getGameHeight() / 2));
    }

    private MemberManager() {
    }

    public static void keyPressed(KeyEvent e) {
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

    public static void keyReleased(KeyEvent e) {
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

    public static void paint(Graphics graphics) {
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

    private static void nextState() {
        ifHitMain();
        ifFireNBomb();
        ifHitEnemy();

        mainBullets.removeIf(MemberManager::isOffRange);
        enemyBullets.removeIf(MemberManager::isOffRange);
        explodes.removeIf(Explode::isExploded);

        mainTank.setIsMoving_Dir(mainTankDirs);
        mainTank.fire(mainBullets);
        for (Tank enemy : enemies) enemy.fire(enemyBullets);
    }

    private static void ifHitMain() {
        for (Bullet bullet : enemyBullets) if (isCashing(mainTank, bullet)) System.exit(0);
    }

    private static void ifFireNBomb() {
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

    private static void ifHitEnemy() {
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

    private static boolean isCashing(Tank tank, Bullet bullet) {
        return tank.getRectangle().intersects(bullet.getRectangle());
    }

    private static boolean isOffRange(Bullet bullet) {
        int x = bullet.getX(), y = bullet.getY();
        return (x < 0 || y < 0 || x > GameFrame.getGameWidth() || y > GameFrame.getGameHeight());
    }
}