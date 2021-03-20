package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader
{
    private static final Properties properties = new Properties();
    static {
        try{
            FileInputStream file = new FileInputStream("src/main/java/com/utilities/config.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String keyWord){
        return properties.getProperty(keyWord);
    }
}
