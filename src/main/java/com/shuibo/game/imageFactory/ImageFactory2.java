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
    public BufferedImage getTankImage() throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClassLoader().getResourceAsStream("image/tankU.gif")));
    }

    @Override
    public BufferedImage getBulletImage() throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClassLoader().getResourceAsStream("image/bulletU.gif")));
    }

    @Override
    public ArrayList<BufferedImage> getExplodeImage() throws IOException {
        ArrayList<BufferedImage> result = new ArrayList<>();
        ClassLoader classLoader = getClassLoader();
        int i = 0;
        for (InputStream inputStream; (inputStream = classLoader.getResourceAsStream(String.format("image/%d.gif", i++))) != null; )
            result.add(ImageIO.read(inputStream));
        return result;
    }

    private static class ImageFactory1Holder {
        static final ImageFactory2 INSTANCE = new ImageFactory2();
    }
}
