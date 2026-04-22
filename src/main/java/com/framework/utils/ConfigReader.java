//package com.framework.utils;
//
//import java.io.FileInputStream;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    Properties prop;
//
//    public ConfigReader() {
//        try {
//            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
//            prop = new Properties();
//            prop.load(fis);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getValue(String key) {
//        return prop.getProperty(key);
//    }
//}

package com.framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {
        try {
            FileInputStream fis =
                    new FileInputStream("src/test/resources/config.properties");

            prop = new Properties();
            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
}