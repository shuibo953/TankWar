package com.shuibo.game.imageFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ImageFactory {
    static private final ClassLoader CLASS_LOADER = ImageFactory.class.getClassLoader();

    public static ClassLoader getClassLoader() {
        return CLASS_LOADER;
    }

    public abstract BufferedImage getTankImage() throws IOException;

    public abstract BufferedImage getBulletImage() throws IOException;

    public abstract ArrayList<BufferedImage> getExplodeImage() throws IOException;
}
