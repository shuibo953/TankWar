package com.shuibo.game.unchangeable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ImageManager {
    public static BufferedImage tankUImage, tankDImage, tankLImage, tankRImage,
            bulletUImage, bulletDImage, bulletLImage, bulletRImage;
    public static ArrayList<BufferedImage> explodes = new ArrayList<>();

    static {
        try {
            ClassLoader classLoader = ImageManager.class.getClassLoader();
            tankUImage = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("image/GoodTank1.png")));
            tankDImage = ImageUtil.rotateImage(tankUImage, 180);
            tankLImage = ImageUtil.rotateImage(tankUImage, -90);
            tankRImage = ImageUtil.rotateImage(tankUImage, 90);
            bulletUImage = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("image/bulletU.png")));
            bulletDImage = ImageUtil.rotateImage(bulletUImage, 180);
            bulletLImage = ImageUtil.rotateImage(bulletUImage, -90);
            bulletRImage = ImageUtil.rotateImage(bulletUImage, 90);
            int i = 1;
            for (InputStream inputStream; (inputStream = classLoader.getResourceAsStream(String.format("image/e%d.gif", i++))) != null; )
                explodes.add(ImageIO.read(inputStream));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private ImageManager() {}
}
