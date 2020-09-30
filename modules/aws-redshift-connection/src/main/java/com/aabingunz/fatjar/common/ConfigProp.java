package com.aabingunz.fatjar.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Abhishek Raj Simon
 * @version 1.0
 */
public class ConfigProp {
    private static Properties configProperties;
    private static String filepath = "config.properties";

    /*
     * Static block to load config properties.
     */
    static {
        configProperties = new Properties();
        try {
            configProperties.load(new FileInputStream(new File(filepath)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @description retrieves the property
     * @param key of the property.
     * @return value of the property
     */
    public static String get(String key) {
        String result = "";
        if (configProperties != null) {
            result = configProperties.getProperty(key);
        }
        return result;
    }

    public static Properties getConfiguration() {
        return new Properties(configProperties);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(ConfigProp.get("jdbc_connection_string"));
    }

}
