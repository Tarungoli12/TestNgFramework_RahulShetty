package com.automation.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src//test//java//com//automation//cucumber//featureFiles",
        tags = "@smoke", glue = "com.automation.cucumber.stepDefinitions",
        monochrome = true,plugin = {"html:target/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
