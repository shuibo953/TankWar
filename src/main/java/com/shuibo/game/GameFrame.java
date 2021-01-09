package com.shuibo.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
    // todo: Singleton -> no static
    private static final int GAME_WIDTH = Integer.parseInt(PropertyManager.INSTANCE.getValue("GAME_WIDTH")),
            GAME_HEIGHT = Integer.parseInt(PropertyManager.INSTANCE.getValue("GAME_HEIGHT"));
    private final GameModel gameModel = GameModel.INSTANCE;
    private Image offScreenImage = null;

    private GameFrame() {
        setTitle("坦克世界");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameModel.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameModel.keyReleased(e);
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

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        Graphics gOffScreen = offScreenImage.getGraphics();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
        gameModel.paint(graphics);
    }

    private static class TankWarFrameHolder {
        private static final GameFrame INSTANCE = new GameFrame();
    }
}