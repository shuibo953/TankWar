package com.shuibo.game;

import com.shuibo.game.chainOfResponsibility.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

public enum GameModel {
    INSTANCE;
    private final ChainOfNextState chainOfNextState = ChainOfNextState.getInstance();
    private final ChainOfCollision chainOfCollision = ChainOfCollision.getInstance();
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private final HashSet<GameObject> goingToAdd = new HashSet<>(), goingToRemove = new HashSet<>();

    private final Tank playerTank;
    private final ArrayList<Dir> playerDirs = new ArrayList<>();
    private boolean isPlayerFiring;

    {
        playerTank = new Tank(0, GameObject.GAME_UP_LIMIT, Group.PLAYER);
        for (int enemyAmount = Integer.parseInt(PropertyManager.INSTANCE.getValue("enemyAmount")),
             width = GameObject.GAME_WIDTH,
             height = GameObject.GAME_HEIGHT,
             i = 0; i < enemyAmount; i++)
            gameObjects.add(new Tank(width / (enemyAmount + 1) * (i + 1), height / 2, Group.ENEMY));
    }

    public HashSet<GameObject> getGoingToAdd() {
        return goingToAdd;
    }

    public HashSet<GameObject> getGoingToRemove() {
        return goingToRemove;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!playerDirs.contains(Dir.UP)) playerDirs.add(Dir.UP);
                break;
            case KeyEvent.VK_DOWN:
                if (!playerDirs.contains(Dir.DOWN)) playerDirs.add(Dir.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if (!playerDirs.contains(Dir.LEFT)) playerDirs.add(Dir.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if (!playerDirs.contains(Dir.RIGHT)) playerDirs.add(Dir.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                isPlayerFiring = true;
                break;
            case KeyEvent.VK_CONTROL:
                playerTank.changeFireStrategy();
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                playerDirs.remove(Dir.UP);
                break;
            case KeyEvent.VK_DOWN:
                playerDirs.remove(Dir.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                playerDirs.remove(Dir.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                playerDirs.remove(Dir.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                isPlayerFiring = false;
        }
    }

    public void paint(Graphics graphics) {
        for (int i = gameObjects.size() - 1; i >= 0; i--) gameObjects.get(i).paint(graphics);
        playerTank.paint(graphics);
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("数量：%d", gameObjects.size()), 10, 50);
        graphics.drawString(String.format("新增数量：%d", goingToAdd.size()), 10, 70);
        graphics.drawString(String.format("删除数量：%d", goingToRemove.size()), 10, 90);
        nextState();
    }

    private void nextState() {
        gameObjects.forEach(chainOfNextState::update);
        updateGameObjects();

        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject gameObject1 = gameObjects.get(i);
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject gameObject2 = gameObjects.get(j);
                chainOfCollision.update(gameObject1, gameObject2);
            }
        }
        updateGameObjects();

        playerAction();
    }

    private void updateGameObjects() {
        gameObjects.addAll(goingToAdd);
        gameObjects.removeAll(goingToRemove);
        goingToAdd.clear();
        goingToRemove.clear();
    }

    private void playerAction() {
        if (isPlayerFiring) gameObjects.addAll(playerTank.fire());
        if (!playerDirs.isEmpty()) {
            Dir dir = playerDirs.get(playerDirs.size() - 1);
            if (playerTank.getDir().equals(dir)) playerTank.move();
            else playerTank.setDir(dir);
        }
    }

    public Tank getPlayer() {
        return playerTank;
    }
}