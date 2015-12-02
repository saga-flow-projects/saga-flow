package com.demosoft.investiogation.neuronlan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class ConfigProvider {

    private static final ConfigProvider instance = new ConfigProvider();

    public static final String NETWORK_CONFIG_FILE = "networkConfigFile";
    public static final String NETWORK_DATA_FILE = "networkDataFile";
    public static final String CONFIG_FILE = "config.properties";

    public static Properties getConfigs() {
        Properties prop = new Properties();
        InputStream input = instance.getClass().getClassLoader()
                .getResourceAsStream(CONFIG_FILE);
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
