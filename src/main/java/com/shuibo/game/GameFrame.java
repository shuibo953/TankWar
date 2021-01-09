package com.shuibo.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
    private final int gameWidth = Integer.parseInt(PropertyManager.INSTANCE.getValue("gameWidth")),
            gameHeight = Integer.parseInt(PropertyManager.INSTANCE.getValue("gameHeight"));
    private Image offScreenImage = null;

    private GameFrame() {
        setTitle("坦克世界");
        setSize(gameWidth, gameHeight);
        setResizable(false);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                GameModel.INSTANCE.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GameModel.INSTANCE.keyReleased(e);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static GameFrame getInstance() {
        return TankWarFrameHolder.INSTANCE;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) offScreenImage = this.createImage(gameWidth, gameHeight);
        Graphics gOffScreen = offScreenImage.getGraphics();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, gameWidth, gameHeight);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
        GameModel.INSTANCE.paint(graphics);
    }

    private static class TankWarFrameHolder {
        private static final GameFrame INSTANCE = new GameFrame();
    }
}