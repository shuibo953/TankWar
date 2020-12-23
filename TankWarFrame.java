package com.shuibo.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class TankWarFrame extends Frame {
    private final Tank mainTank = new Tank(200, 200, this);
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Dir> mainTankDirs = new ArrayList<>();
    private static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankWarFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == ' ') mainTank.setLaunching(true);
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
                }
                mainTank.setMotion(mainTankDirs);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == ' ') mainTank.setLaunching(false);
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
                }
                mainTank.setMotion(mainTankDirs);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;//todo
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
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("子弹数量：%d%n", bullets.size()), 10,60);
        mainTank.paint(graphics);
        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
            Bullet bullet = iterator.next();
            if (isLive(bullet)) bullet.paint(graphics);
            else iterator.remove();
        }
    }

    private boolean isLive(Bullet bullet) {
        int x = bullet.getX(), y = bullet.getY();
        return (x >= 0 && y >= 0 && x <= GAME_WIDTH && y <= GAME_HEIGHT);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }
}
