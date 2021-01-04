package com.shuibo.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageManager {
    public static BufferedImage tankUImage, tankDImage, tankLImage, tankRImage,
            bulletUImage, bulletDImage, bulletLImage, bulletRImage;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            ClassLoader classLoader = ImageManager.class.getClassLoader();
            tankUImage = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("image/GoodTank1.png")));
            tankDImage = ImageUtil.rotateImage(tankUImage, 180);
            tankLImage = ImageUtil.rotateImage(tankUImage, -90);
            tankRImage = ImageUtil.rotateImage(tankUImage, 90);
            bulletUImage = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("image/bulletU.gif")));
            bulletDImage = ImageUtil.rotateImage(bulletUImage, 180);
            bulletLImage = ImageUtil.rotateImage(bulletUImage, -90);
            bulletRImage = ImageUtil.rotateImage(bulletUImage, 90);
            for (int i = 0; i < explodes.length; ) {
                explodes[i] = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream(String.format("image/e%d.gif", ++i))));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
