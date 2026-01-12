package com.cheq.demo_webshop.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
* Factory class for creating WebDriver instances based on browser type.
*/
public class WebDriverFactory {
    /**
     * Loads a WebDriver instance for the specified browser.
     *
     * @param browser the name of the browser ("chrome", "firefox", "edge")
     * @return the WebDriver instance
     * @throws IllegalArgumentException if the browser is not supported
     */
	public static WebDriver loadDriver(String browser) {

		boolean headless = Boolean.parseBoolean(
			System.getProperty("headless", "false")
		);

		switch (browser.toLowerCase()) {

			case "chrome":
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				if (headless) {
					options.addArguments("--headless=new");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--disable-gpu");
					options.addArguments("--window-size=1920,1080");
					options.addArguments("--disable-features=VizDisplayCompositor");
					options.addArguments("--disable-extensions");
					options.addArguments("--disable-infobars");
				}

				return new ChromeDriver(options);

		   case "firefox":
		    WebDriverManager.firefoxdriver().setup();
		    return new FirefoxDriver();
		
		   case "edge":
		    WebDriverManager.edgedriver().setup();
		    return new EdgeDriver();
		
		   default:
		    throw new IllegalArgumentException("Unsupported browser: " + browser);
			  }
		}
}