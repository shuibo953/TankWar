package com.shuibo.game.imageFactory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class ImageFactory {
    static private final ClassLoader CLASS_LOADER = ImageFactory.class.getClassLoader();

    public static ClassLoader getClassLoader() {
        return CLASS_LOADER;
    }

    public abstract BufferedImage getTankImage();

    public abstract BufferedImage getBulletImage();

    public abstract ArrayList<BufferedImage> getExplodeImage();
}
