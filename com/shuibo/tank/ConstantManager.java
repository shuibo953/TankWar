package com.shuibo.tank;

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

    public static int getValue(String key) {
        return Integer.parseInt((String) properties.get(key));
    }
}
