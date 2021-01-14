package com.shuibo.game;

import java.awt.*;

public abstract class GameObject {
    public static final int GAME_WIDTH = Integer.parseInt(PropertyManager.INSTANCE.getValue("gameWidth")),
            GAME_HEIGHT = Integer.parseInt(PropertyManager.INSTANCE.getValue("gameHeight")),
            GAME_UP_LIMIT = Integer.parseInt(PropertyManager.INSTANCE.getValue("UP_LIMIT"));

    public abstract void paint(Graphics graphics);
}
