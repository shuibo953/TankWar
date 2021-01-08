package com.shuibo.game.unchangeable;

import java.io.IOException;
import java.util.Properties;

public class ConstantManager {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(ConstantManager.class.getClassLoader().getResourceAsStream("constant"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private ConstantManager() {}

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
