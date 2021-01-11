package com.shuibo.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
    private Image offScreenImage = null;
    private final GameModel gameModel = GameModel.INSTANCE;

    private GameFrame() {
        setTitle("坦克世界");
        setSize(Integer.parseInt(PropertyManager.INSTANCE.getValue("gameWidth")),
                Integer.parseInt(PropertyManager.INSTANCE.getValue("gameHeight")));
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

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) offScreenImage = this.createImage(getWidth(), getHeight());
        Graphics gOffScreen = offScreenImage.getGraphics();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, getWidth(), getHeight());
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