package com.shuibo.game;

import java.io.IOException;
import java.util.Properties;

public enum PropertyManager {
    INSTANCE;
    private final Properties properties = new Properties();

    {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("configuration"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
