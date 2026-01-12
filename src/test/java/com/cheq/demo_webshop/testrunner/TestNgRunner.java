package com.cheq.demo_webshop.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestNG runner for executing Cucumber feature files with parallel execution support.
 * Configured to run features under "src/test/resources/feature/" with specified glue and plugins.
 * Supports tags for selective test execution and integrates with Allure reporting.
 */
@CucumberOptions(
 features = "src/test/resources/feature/",
 glue = {
  "com.cheq.demo_webshop.stepdefinitions",
  "com.cheq.demo_webshop.hooks",
  "com.cheq.demo_webshop.listener"
 },
 plugin = {
  "pretty",
  "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
  "com.cheq.demo_webshop.listener.StepListener"
 },
 monochrome = true,
 tags = "@login or @validateMultipleProducts or @register"
)
public class TestNgRunner extends AbstractTestNGCucumberTests {

    /**
     * Overrides the default Cucumber scenario provider to enable parallel execution.
     *
     * @return two-dimensional array of Cucumber scenarios for TestNG
     */
	 @Override
	 @DataProvider(parallel = true)
	 public Object[][] scenarios() {
	  return super.scenarios();
	 }	
}