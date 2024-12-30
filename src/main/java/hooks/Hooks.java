package hooks;

import browserFactory.DriverManager;
import browserFactory.DriverManagerFactory;
import dataProviders.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Hooks {



    private static DriverManager driverManager; // Static to make it accessible to BaseStepDefinitions
    private WebDriver driver;



    @Before
    public void setUp() {

        String browserType = ConfigReader.getProperty("default.driver.type").toUpperCase();//property file value get from them config file
        driverManager = DriverManagerFactory.getManager(browserType); // Initializes the DriverManager// return the new chromedriver manager or edgedriver manager
        driver = driverManager.getDriver(); // Starts the WebDriver instance therefore driverManager wil be of chromedriver object or edgedriver object
    }


    @After
    public void tearDown(Scenario scenario) {
        // Capture screenshot for failed scenarios

        // Quit WebDriver and cleanup
        if (driverManager != null) {
            driverManager.quitDriver();
        }

    }
    // Static method to get DriverManager for BaseStepDefinitions or other classes
    public static DriverManager getDriverManager() {

        return driverManager;
    }



}
