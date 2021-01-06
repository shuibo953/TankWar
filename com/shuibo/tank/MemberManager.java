package com.shuibo.tank;

import com.shuibo.tank.fireStrategy.AllDir;
import com.shuibo.tank.fireStrategy.DefaultFireStrategy;
import com.shuibo.tank.fireStrategy.FireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class MemberManager {
    private static final Tank mainTank = new Tank(0, 30);
    private static final HashSet<Bullet> mainBullets = new HashSet<>();
    private static final HashSet<Tank> enemies = new HashSet<>();
    private static final HashSet<Bullet> enemyBullets = new HashSet<>();
    private static final HashSet<Explode> explodes = new HashSet<>();
    private static final ArrayList<Dir> mainTankDirs = new ArrayList<>();
    private static final Random random = new Random();
    private static final FireStrategy oneDir = new DefaultFireStrategy(), allDir = new AllDir();
    private static boolean isFire;
    private static int strategy;

    static {
        for (int i = 0, enemyAmount = ConstantManager.getValue("enemyAmount"); i < enemyAmount; i++)
            enemies.add(new Tank(TankWarFrame.getGameWidth() / (enemyAmount + 1) * (i + 1), TankWarFrame.getGameHeight() / 2));
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
                isFire = true;
                break;
            case KeyEvent.VK_CONTROL:
                strategy = (strategy++ == 1 ? 0 : strategy);
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
                isFire = false;
        }
    }

    public static void paint(Graphics graphics) {
        updateMember();
        mainTank.paint(graphics);
        mainBullets.forEach(bullet -> bullet.paint(graphics));
        enemies.forEach(tank -> tank.paint(graphics));
        enemyBullets.forEach(bullet -> bullet.paint(graphics));
        explodes.forEach(explode -> explode.paint(graphics));
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("我方子弹数量：%d", mainBullets.size()), 10, 60);
        graphics.drawString(String.format("敌方数量：%d", enemies.size()), 10, 80);
        graphics.drawString(String.format("敌方子弹数量：%d", enemyBullets.size()), 10, 100);
        graphics.drawString(String.format("爆炸数量：%d", explodes.size()), 10, 120);
    }

    private static void updateMember() {
        ifHitMain();
        ifHitEnemy();

        mainBullets.removeIf(MemberManager::isOffRange);
        enemyBullets.removeIf(MemberManager::isOffRange);

        mainAction();
        enemyAction();

        explodes.removeIf(Explode::isExploded);
    }

    private static void ifHitMain() {
        for (Bullet bullet : enemyBullets) if (isCashing(mainTank, bullet)) System.exit(0);
    }

    private static void ifHitEnemy() {
        Iterator<Tank> enemyIterator = enemies.iterator();
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

    private static void mainAction() {
        mainTank.setDir(mainTankDirs);
        if (isFire) {
            switch (strategy) {
                case 0:
                    mainTank.fire(mainBullets, oneDir);
                    break;
                case 1:
                    mainTank.fire(mainBullets, allDir);
                    break;
                case 2:
//                    mainTank.fire(mainBullets, new NBomb());
            }
        }
    }

    private static void enemyAction() {
        for (Tank enemy : enemies) {
            if (random.nextInt(10) > 8) enemy.setDir(Dir.values()[random.nextInt(4)]);
            if (random.nextInt(10) > 8) enemy.fire(enemyBullets, oneDir);
        }
    }

    private static boolean isCashing(Tank tank, Bullet bullet) {
        return tank.getRectangle().intersects(bullet.getRectangle());
    }

    private static boolean isOffRange(Bullet bullet) {
        int x = bullet.getX(), y = bullet.getY();
        return (x < 0 || y < 0 || x > TankWarFrame.getGameWidth() || y > TankWarFrame.getGameHeight());
    }
}