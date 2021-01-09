package com.shuibo.game;

import com.shuibo.game.imageFactory.ImageFactory;
import com.shuibo.game.imageFactory.ImageFactory1;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public enum ImageManager {
    INSTANCE;
    private final ImageFactory imageFactory = ImageFactory1.getInstance();
    private final ArrayList<BufferedImage> explodes = imageFactory.getExplodeImage();
    private final BufferedImage tankUImage, tankDImage, tankLImage, tankRImage,
            bulletUImage, bulletDImage, bulletLImage, bulletRImage;

    {
        tankUImage = imageFactory.getTankImage();
        tankDImage = ImageUtil.rotateImage(tankUImage, 180);
        tankLImage = ImageUtil.rotateImage(tankUImage, -90);
        tankRImage = ImageUtil.rotateImage(tankUImage, 90);
        bulletUImage = imageFactory.getBulletImage();
        bulletDImage = ImageUtil.rotateImage(bulletUImage, 180);
        bulletLImage = ImageUtil.rotateImage(bulletUImage, -90);
        bulletRImage = ImageUtil.rotateImage(bulletUImage, 90);
    }

    public ImageFactory getImageFactory() {
        return imageFactory;
    }

    public ArrayList<BufferedImage> getExplodes() {
        return explodes;
    }

    public BufferedImage getTankUImage() {
        return tankUImage;
    }

    public BufferedImage getTankDImage() {
        return tankDImage;
    }

    public BufferedImage getTankLImage() {
        return tankLImage;
    }

    public BufferedImage getTankRImage() {
        return tankRImage;
    }

    public BufferedImage getBulletUImage() {
        return bulletUImage;
    }

    public BufferedImage getBulletDImage() {
        return bulletDImage;
    }

    public BufferedImage getBulletLImage() {
        return bulletLImage;
    }

    public BufferedImage getBulletRImage() {
        return bulletRImage;
    }
}
