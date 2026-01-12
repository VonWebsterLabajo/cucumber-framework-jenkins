package com.cheq.demo_webshop.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cheq.demo_webshop.factory.WebDriverFactory;
import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.utils.AllureUtil;
import com.cheq.demo_webshop.utils.ConfigReader;
import com.cheq.demo_webshop.utils.LoggerUtil;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;

/**
* Cucumber hooks for managing WebDriver lifecycle and Allure reporting.
* Handles setup, teardown, screenshot capture, and driver access per scenario.
*/
public class Hooks {

//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ThreadLocal<AllureUtil> allureUtil = new ThreadLocal<>();
    private static final Logger logger = LoggerUtil.getLogger(Hooks.class);

    /**
    * Initializes WebDriver, loads environment configuration,
    * sets up Allure reporting, and starts the test scenario.
    *
    * @param scenario the current Cucumber scenario
    * @throws IOException if configuration properties fail to load
    */    
    @Before
    public void setUp(Scenario scenario) throws IOException {
    	// Verify if the run is parallel or sequential!
		System.out.println(
				 "Thread=" + Thread.currentThread().getName() +
				 " | Scenario=" + scenario.getName()
				);
    	
	     String env = System.getProperty("env", "dev");
	     ConfigReader.loadProperties(env);
	
	     String browser = System.getProperty("browser", ConfigReader.get("browser"));
	     String url = ConfigReader.get("baseUrl");
	
	     WebDriver drv = WebDriverFactory.loadDriver(browser);
	     drv.manage().window().maximize();
	     drv.get(url);
	
	     DriverManager.setDriver(drv);
	
	     allureUtil.set(new AllureUtil(drv));
	     allureUtil.get().writeAllureEnvironment(
	         ImmutableMap.<String, String>builder()
	             .put("OS", System.getProperty("os.name"))
	             .put("Browser", browser)
	             .put("Environment", env)
	             .build()
	     );
	
	     logger.info("Starting scenario: " + scenario.getName());
    }


    /**
     * Captures and attaches a screenshot to the Allure report
     * if the scenario execution fails.
     *
     * @param scenario the executed Cucumber scenario
     */
    @After(order = 0)
    public void captureFailure(Scenario scenario) {
    	WebDriver drv = DriverManager.getDriver();
    	if (scenario.isFailed() && drv != null) {
    		allureUtil.get().captureAndAttachScreenshot();
    	}
    }
    
    /**
    * Quits the WebDriver instance and cleans up thread-local resources
    * after scenario execution.
    *
    * @param scenario the executed Cucumber scenario
    */
    @After(order = 1)
    public void tearDown(Scenario scenario) {
     WebDriver drv = DriverManager.getDriver();
     if (drv != null) {
      drv.quit();
     }
     DriverManager.removeDriver();
     allureUtil.remove();
    }


    /**
    * Captures and attaches a screenshot to the Allure report
    * after each executed step.
    *
    * @param scenario the current Cucumber scenario
    */
    @AfterStep
    public void afterEachStep(Scenario scenario) {
     WebDriver drv = DriverManager.getDriver();
     if (drv == null) {
      return;
     }
     try {
      allureUtil.get().captureAndAttachScreenshot();
     } catch (Exception e) {
      // swallow to prevent CI flakiness
     }
    }
}
