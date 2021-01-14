package com.shuibo.game.imageFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ImageFactory2 extends ImageFactory {

    private ImageFactory2() {
    }

    public static ImageFactory2 getInstance() {
        return ImageFactory1Holder.INSTANCE;
    }

    @Override
    public BufferedImage getTankImage() {
        try {
            return ImageIO.read(Objects.requireNonNull(getClassLoader().getResourceAsStream("image/tankU.gif")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    @Override
    public BufferedImage getBulletImage() {
        try {
            return ImageIO.read(Objects.requireNonNull(getClassLoader().getResourceAsStream("image/bulletU.gif")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<BufferedImage> getExplodeImage() {
        ArrayList<BufferedImage> result = new ArrayList<>();
        ClassLoader classLoader = getClassLoader();
        int i = 0;
        try {
            for (InputStream inputStream; (inputStream = classLoader.getResourceAsStream(String.format("image/%d.gif", i++))) != null; )
                result.add(ImageIO.read(inputStream));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return result;
    }

    @Override
    public BufferedImage getNBombImage() {
        try {
            return ImageIO.read(Objects.requireNonNull(getClassLoader().getResourceAsStream("image/NBomb.png")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    private static class ImageFactory1Holder {
        static final ImageFactory2 INSTANCE = new ImageFactory2();
    }
}
