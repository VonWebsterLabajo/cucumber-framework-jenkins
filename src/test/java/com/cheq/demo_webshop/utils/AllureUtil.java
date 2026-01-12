package com.cheq.demo_webshop.utils;

import io.qameta.allure.Allure;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cheq.demo_webshop.listener.StepListener;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Utility class for attaching text, screenshots, and environment details to Allure reports.
 * Provides helper methods to enhance reporting during Cucumber test execution.
 */
public class AllureUtil {
	private final WebDriver driver;
	private static final Logger logger = LoggerUtil.getLogger(AllureUtil.class);

	/**
	 * Creates an AllureUtil instance bound to the given WebDriver.
	 *
	 * @param driver the WebDriver used to capture screenshots
	 */
	public AllureUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Attaches a plain text message to the current Allure step.
	 * Also updates the step name based on the current executed step.
	 *
	 * @param message the text content to attach to the Allure report
	 */
	public void attachText(String message) {
		Allure.getLifecycle().updateStep(s -> s.setName("📸 " + StepListener.currentStep));
		Allure.addAttachment("Sample Text", "text/plain", new ByteArrayInputStream(
			message.getBytes(StandardCharsets.UTF_8)), ".txt");
		}

	/**
	 * Captures a screenshot from the current browser state and attaches it to Allure.
	 * The screenshot is linked to the current Cucumber step.
	 */
	public void captureAndAttachScreenshot() {
		byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Allure.getLifecycle().updateStep(s -> s.setName("📸 " + StepListener.currentStep)); // override name
		Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
	}

	/**
	 * Writes environment details into an Allure environment.properties file.
	 * This information is displayed in the Allure report under the Environment section.
	 *
	 * @param env a map containing environment key-value pairs
	 */
	public void writeAllureEnvironment(Map<String, String> env) {
		// Create the environment.properties file in allure-results directory
		File envFile = new File("target/allure-results/environment.properties");
		// Ensure parent directories exist
		envFile.getParentFile().mkdirs();
		try (FileWriter writer = new FileWriter(envFile)) {
			// Write each property as key=value
			for (Map.Entry<String, String> entry : env.entrySet()) {
				writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
				}
		} catch (IOException e) {
			logger.error("Failed to write Allure environment file", e);
		}
	}
}