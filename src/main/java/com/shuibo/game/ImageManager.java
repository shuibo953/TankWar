package com.shuibo.game;

import com.shuibo.game.imageFactory.ImageFactory;
import com.shuibo.game.imageFactory.ImageFactory1;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ImageManager {
    public static final ImageFactory imageFactory = ImageFactory1.getInstance();
    public static BufferedImage tankUImage, tankDImage, tankLImage, tankRImage,
            bulletUImage, bulletDImage, bulletLImage, bulletRImage;
    public static ArrayList<BufferedImage> explodes;

    static {
        try {
            tankUImage = imageFactory.getTankImage();
            tankDImage = ImageUtil.rotateImage(tankUImage, 180);
            tankLImage = ImageUtil.rotateImage(tankUImage, -90);
            tankRImage = ImageUtil.rotateImage(tankUImage, 90);
            bulletUImage = imageFactory.getBulletImage();
            bulletDImage = ImageUtil.rotateImage(bulletUImage, 180);
            bulletLImage = ImageUtil.rotateImage(bulletUImage, -90);
            bulletRImage = ImageUtil.rotateImage(bulletUImage, 90);
            explodes = imageFactory.getExplodeImage();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private ImageManager() {
    }
}
