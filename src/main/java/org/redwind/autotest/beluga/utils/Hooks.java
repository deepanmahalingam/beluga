package org.redwind.autotest.beluga.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redwind.autotest.beluga.configuration.DriverFactory;

import java.io.IOException;

public class Hooks {

    private Logger logger = LogManager.getFormatterLogger();
    DriverFactory driverFactory = new DriverFactory();
    private PropertyReader propertyReader = new PropertyReader();
    private WrapperMethods wrapperMethods = new WrapperMethods();
    public static Scenario sec;
    private Environment environment;

    public Hooks() throws IOException {
        environment = propertyReader.getEnvironment();
    }

    @Before
    public void scenarioStartUp(Scenario scenario) throws IOException {
        sec=scenario;
        startLogger(scenario);
        if(!environment.getPlatform().equals("Restful")) {
            driverFactory.initializeBrowser();
        }
    }

    private void startLogger(Scenario scenario) {
        logger.info("**************************************************************************");
        logger.info("$$$$$$$$$$$$$$$$$$$$$ Starting Scenario ----- %s $$$$$$$$$$$$$$$",scenario.getName());
        logger.info("*************************************************************************");
    }

    private void endLogger(Scenario scenario) {
        logger.info("*************************************************************************");
        logger.info("$$$$$$$$$$$$$$$$$$$$$ Ending Scenario ----- %s $$$$$$$$$$$$$$$",scenario.getName());
        logger.info("**************************************************************************");
    }

    @After
    public void scenarioTailEnd(Scenario scenario) {
        endLogger(scenario);
        if(!environment.getPlatform().equals("Restful")) {
            if(!scenario.isFailed()) {
                wrapperMethods.takeScreenshot(scenario);
            }
            driverFactory.getCurrentDriver().quit();
        }
    }


}