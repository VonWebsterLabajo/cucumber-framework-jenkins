package com.cheq.demo_webshop.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for loading and accessing environment-specific configuration properties.
 * Supports reading properties files based on the selected environment.
 */
public class ConfigReader {
    private static final Properties properties = new Properties();
    private Properties property;

    /**
     * Loads configuration properties for the given environment into a static context.
     * Intended for global configuration access during test execution.
     *
     * @param env the environment name used to resolve the properties file
     */
    public static void loadProperties(String env) {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/" + env + ".properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load environment config: " + env, e);
        }
    }

    /**
     * Initializes and returns a Properties object for the given environment.
     * Useful when an instance-based configuration is required.
     *
     * @param env the environment name used to resolve the properties file
     * @return the loaded Properties object
     */
    public Properties initProperty(String env) {
        property = new Properties();
        String configFilePath = System.getProperty("user.dir") + "src/test/resources/config/" + env + ".properties";
        try (FileInputStream ip = new FileInputStream(configFilePath)) {
            property.load(ip);
        } catch (FileNotFoundException e) {
            System.err.println("Config file not found: " + configFilePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error loading config file: " + e.getMessage());
            e.printStackTrace();
        }

        return property;
    }

    /**
     * Retrieves a configuration value by key from the loaded properties.
     *
     * @param key the property key to look up
     * @return the corresponding property value
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
}