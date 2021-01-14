package com.shuibo.game;

import com.shuibo.game.imageFactory.ImageFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Properties;

public enum PropertyManager {
    INSTANCE;
    private final Properties properties = new Properties();
    private final ImageFactory imageFactory =
            (ImageFactory) (new ClassPathXmlApplicationContext("App.xml"))
                    .getBean("imageFactory");

    {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("configuration"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public ImageFactory getImageFactory() {
        return imageFactory;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
