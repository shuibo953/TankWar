package com.shuibo.game;

import com.shuibo.game.imageFactory.ImageFactory;
import com.shuibo.game.imageFactory.ImageFactory1;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public enum ImageManager {
    INSTANCE;
    public final ImageFactory imageFactory = ImageFactory1.getInstance();
    public final BufferedImage tankUImage, tankDImage, tankLImage, tankRImage,
            bulletUImage, bulletDImage, bulletLImage, bulletRImage;
    public final ArrayList<BufferedImage> explodes = imageFactory.getExplodeImage();

    ImageManager() {
        tankUImage = imageFactory.getTankImage();
        tankDImage = ImageUtil.rotateImage(tankUImage, 180);
        tankLImage = ImageUtil.rotateImage(tankUImage, -90);
        tankRImage = ImageUtil.rotateImage(tankUImage, 90);
        bulletUImage = imageFactory.getBulletImage();
        bulletDImage = ImageUtil.rotateImage(bulletUImage, 180);
        bulletLImage = ImageUtil.rotateImage(bulletUImage, -90);
        bulletRImage = ImageUtil.rotateImage(bulletUImage, 90);
    }
}
