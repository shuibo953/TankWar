package com.shuibo.game.imageFactory;

import com.shuibo.game.unchangeable.ImageManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ImageFactory1 extends ImageFactory {

    private ImageFactory1() {
    }

    public static ImageFactory1 getInstance() {
        return ImageFactory1Holder.INSTANCE;
    }

    @Override
    public BufferedImage getTankImage() throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClassLoader().getResourceAsStream("image/GoodTank1.png")));
    }

    @Override
    public BufferedImage getBulletImage() throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClassLoader().getResourceAsStream("image/bulletU.png")));
    }

    @Override
    public ArrayList<BufferedImage> getExplodeImage() throws IOException {
        ArrayList<BufferedImage> result = new ArrayList<>();
        ClassLoader classLoader = getClassLoader();
        int i = 1;
        for (InputStream inputStream; (inputStream = classLoader.getResourceAsStream(String.format("image/e%d.gif", i++))) != null; )
            result.add(ImageIO.read(inputStream));
        return result;
    }

    private static class ImageFactory1Holder {
        static final ImageFactory1 INSTANCE = new ImageFactory1();
    }
}
