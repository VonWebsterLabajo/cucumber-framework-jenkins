package com.cheq.demo_webshop.manager;

import org.openqa.selenium.WebDriver;

/**
* Provides a global access point for managing the WebDriver instance
* used throughout the test session. Ensures that all tests use the same
* WebDriver, supporting browser automation and resource management.
*/
public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Retrieves the current thread's WebDriver instance.
     *
     * @return the active WebDriver for this thread, or null if not set
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Assigns a WebDriver instance for the current thread.
     *
     * @param driverInstance the WebDriver to use for this thread
     */
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    /**
     * Removes the WebDriver instance for the current thread.
     * Use this after quitting the driver to prevent memory leaks.
     */
    public static void removeDriver() {
        driver.remove();
    }
}