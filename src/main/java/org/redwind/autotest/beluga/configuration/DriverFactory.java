package org.redwind.autotest.beluga.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.redwind.autotest.beluga.utils.Environment;
import org.redwind.autotest.beluga.utils.PropertyReader;

import java.io.IOException;

public class DriverFactory {
    private static ThreadLocal<WebDriver> currentDriver = new ThreadLocal<>();
    public WebDriver driver = null;
    private Logger logger = LogManager.getFormatterLogger();
    private PropertyReader propertyReader = new PropertyReader();
    Environment environment;

    public DriverFactory() {
        environment = propertyReader.getEnvironment();
    }


    public WebDriver getCurrentDriver() {
        driver = currentDriver.get();
        if(driver!=null) {
            return driver;
        } else {
            logger.info("Driver is not initiated");
            return null;
        }
    }

    public void initializeBrowser() {
        currentDriver.set(getDesktopDriver(environment.getPlatform()));
    }
    public WebDriver getDesktopDriver(String browser) {
        if(browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
            logger.info("************** Launching Chrome browser ****************");
        } else if(browser.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else if(browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if(browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if(browser.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
        }
        return driver;
    }
    public void cleanUpCurrentDriver() {
        currentDriver.remove();
    }
}