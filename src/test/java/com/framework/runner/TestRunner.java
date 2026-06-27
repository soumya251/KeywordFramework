
package com.framework.runner;

import com.framework.engine.KeywordEngine;
import com.framework.utils.ConfigReader;

public class TestRunner {

    public static void main(String[] args) {

        ConfigReader config = new ConfigReader();

        String excelFile = config.get("excelPath");

        KeywordEngine engine = new KeywordEngine();
        engine.runTest(excelFile);
    }
}