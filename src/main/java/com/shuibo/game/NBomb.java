package com.shuibo.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NBomb extends GameObject {
    private final BufferedImage image = ImageManager.INSTANCE.getNBombImage();
    public final int speed = Integer.parseInt(PropertyManager.INSTANCE.getValue("BULLET_SPEED")),
            height = image.getHeight();
    private final int x = (Integer.parseInt(PropertyManager.INSTANCE.getValue("gameWidth")) - image.getWidth()) / 2;
    private int y = -image.getHeight();

    private NBomb() {
    }

    public static NBomb getINSTANCE() {
        return NBombHolder.INSTANCE;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }

    private static class NBombHolder {
        private static final NBomb INSTANCE = new NBomb();
    }
}