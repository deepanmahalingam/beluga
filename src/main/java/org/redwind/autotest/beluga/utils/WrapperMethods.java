package org.redwind.autotest.beluga.utils;



import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.redwind.autotest.beluga.configuration.DriverFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class WrapperMethods {

    public static final Logger logger = LogManager.getFormatterLogger();
    private DriverFactory driverFactory;
    private GenericFunctions genericFunctions;


    private WebDriver driver;

    public WrapperMethods() throws IOException {
        driverFactory = new DriverFactory();
        genericFunctions = new GenericFunctions();
    }

    public void openApplication(String url) {
        driver=driverFactory.getCurrentDriver();
        try {
            driver.get(url);
        } catch (WebDriverException getError) {
            logger.error("Failed to load the application because %s",getError);
        }
    }
    public void enterText(By locator, String value) {
        driver=driverFactory.getCurrentDriver();
        try {
            driver.findElement(locator).sendKeys(value);
        } catch (WebDriverException getError) {
            logger.error("Failed to enter Text %s",getError);
        }
    }
    public void clickOnElement(By locator) {
        driver=driverFactory.getCurrentDriver();
        try {
            driver.findElement(locator).click();
        } catch (WebDriverException getError) {
            logger.error("Failed to click on webElement %s", getError);
        }
    }
    public void zoom(int zoomPercentage) {
        driver = driverFactory.getCurrentDriver();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.body.style.zoom = '"+zoomPercentage+"%'");
    }
    public void scrollToElement(By locator) {
        driver=driverFactory.getCurrentDriver();
        try {
            Actions action = new Actions(driver);
            action.scrollToElement((WebElement) locator).build().perform();
        } catch (WebDriverException getException) {
            logger.error("Scroll to element failed %s",getException);
        }
    }
    public String getTextFromElement(By locator) {
        driver=driverFactory.getCurrentDriver();
        String text=null;
        try {
            text = driver.findElement(locator).getText();
        }catch (WebDriverException getException) {
            logger.error("Cannot get the Text %s",getException);
        }
        return text;
    }
    public String getValueFromElement(By locator,String name) {
        driver=driverFactory.getCurrentDriver();
        String value=null;
        try {
            value = driver.findElement(locator).getAttribute(name);
        } catch (WebDriverException getException) {
            logger.error("Cannot get the attribute value %s",getException);
        }
        return value;
    }

    public String getCurrentURL() {
        driver=driverFactory.getCurrentDriver();
        String text = null;
        try {
            text=driver.getCurrentUrl();
        } catch (WebDriverException getException) {
            logger.error("Cannot get the URL %s",getException);
        }
        return text;
    }
    public void refreshThePage() {
        driver = driverFactory.getCurrentDriver();
        try {
            driver.navigate().refresh();
        } catch (WebDriverException getException) {
            logger.error("Cannot refresh the webpage",getException);
        }
    }
    public void navigateBack() {
        driver =driverFactory.getCurrentDriver();
        try {
            driver.navigate().back();
        } catch (WebDriverException getException) {
            logger.error("Cannot navigate back in webpage %s", getException);
        }
    }
    public void clearText(By locator) {
        driver=driverFactory.getCurrentDriver();
        try {
            driver.findElement(locator).clear();
        } catch (WebDriverException getException) {
            logger.error("Cannot clear the Text %s", getException);
        }
    }
    public void waitForPresenceOfElementLocated(By locator, Duration timeoutInSeconds) {
        driver=driverFactory.getCurrentDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (WebDriverException getException) {
            logger.error("Element is not loaded within the time", getException);
        }
    }
    public void waitForVisibilityOfElementLocated(By locator, Duration timeoutInSeconds) {
        driver=driverFactory.getCurrentDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (WebDriverException getException) {
            logger.error("Element is not loaded within the time", getException);
        }
    }
    public void selectByValue(By locator, String value) {
        driver=driverFactory.getCurrentDriver();
        try{
            Select select= new Select(driver.findElement(locator));
            select.selectByValue(value);
        } catch (WebDriverException getException) {
            logger.error("value is not present in dropdown", getException);
        }
    }
    public void selectByVisibleText(By locator, String text) {
        driver=driverFactory.getCurrentDriver();
        try{
            Select select= new Select(driver.findElement(locator));
            select.selectByVisibleText(text);
        } catch (WebDriverException getException) {
            logger.error("Text is not present in dropdown", getException);
        }
    }
    public void selectByIndex(By locator, int index) {
        driver=driverFactory.getCurrentDriver();
        try{
            Select select= new Select(driver.findElement(locator));
            select.selectByIndex(index);
        } catch (WebDriverException getException) {
            logger.error("value is not present in dropdown", getException);
        }
    }
    public List<WebElement> getAllDropdownValues(By locator) {
        driver = driverFactory.getCurrentDriver();
        List<WebElement> options=null;
        try {
            Select select = new Select(driver.findElement(locator));
            options=select.getOptions();
        } catch (WebDriverException getException) {
            logger.error("options are not visible", getException);
        }
        return options;
    }
    public void scrollToButtom() {
        driver = driverFactory.getCurrentDriver();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    public void scrollToTop() {
        driver = driverFactory.getCurrentDriver();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }
    public void mouseHover(By locator) {
        driver = driverFactory.getCurrentDriver();
        try {
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(locator)).build().perform();
        } catch(WebDriverException getException) {
            logger.error("mouse hover is not performed %s", getException);
        }
    }
    public void performRightClick() {
        driver = driverFactory.getCurrentDriver();
        try {
            Actions action = new Actions(driver);
            action.contextClick().build().perform();
        } catch(WebDriverException getException) {
            logger.error("right click cannot be performed %s", getException);
        }
    }
    public void performRightClickOnWebElement(By locator) {
        driver = driverFactory.getCurrentDriver();
        try {
            Actions action = new Actions(driver);
            action.contextClick(driver.findElement(locator)).build().perform();
        } catch(WebDriverException getException) {
            logger.error("right click on web element cannot be performed %s", getException);
        }
    }
    public void openNewTab() {
        driver = driverFactory.getCurrentDriver();
        try {
            driver.switchTo().newWindow(WindowType.TAB);
        } catch(WebDriverException getException) {
            logger.error("Cannot open new tab %s", getException);
        }
    }
    public void openNewWindow() {
        driver = driverFactory.getCurrentDriver();
        try {
            driver.switchTo().newWindow(WindowType.WINDOW);
        } catch(WebDriverException getException) {
            logger.error("Cannot open new window %s", getException);
        }
    }
    public void maximizeWindow() {
        driver = driverFactory.getCurrentDriver();
        driver.manage().window().maximize();
    }
    public void minimizeWindow() {
        driver = driverFactory.getCurrentDriver();
        driver.manage().window().minimize();
    }
    public boolean isElementDisplayed(By locator) {
        driver = driverFactory.getCurrentDriver();
        try {
            boolean flag=driver.findElement(locator).isDisplayed();
            return flag;
        } catch(WebDriverException getException) {
            logger.error("Cannot open new window %s", getException);
            return false;
        }
    }
    public void closeWindow() {
        driver = driverFactory.getCurrentDriver();
        driver.close();
    }
    public void quitBrowser() {
        driver=driverFactory.getCurrentDriver();
        driver.quit();
    }
    public void waitForElementToBeClickable(By locator, Duration timeoutInSeconds) {
        driver=driverFactory.getCurrentDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (WebDriverException getException) {
            logger.error("Element is not clickable within the time %s", getException);
        }
    }
    public void waitForTextToBePresent(By locator,String text, Duration timeoutInSeconds) {
        driver=driverFactory.getCurrentDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (WebDriverException getException) {
            logger.error("Element is not loaded with text within the time $s", getException);
        }
    }
    public void waitForAlertTPresentAndAccept(Duration timeoutInSeconds) {
        driver=driverFactory.getCurrentDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (WebDriverException getException) {
            logger.error("Alert is not present %s", getException);
        }
    }
    public List<WebElement> getListOfElements(By locator) {
        driver=driverFactory.getCurrentDriver();
        List<WebElement> elements=null;
        try {
            elements = driver.findElements(locator);
        } catch (WebDriverException getException) {
            logger.error("List of web elements is not available %s", getException);
        }
        return elements;
    }
    public void waitForFrameToLoadAndSwitch(By locator, Duration timeoutInSeconds) {
        driver= driverFactory.getCurrentDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        } catch (WebDriverException getException) {
            logger.error("Frame is not available %s", getException);
        }
    }
    public void takeScreenshotOfWebElement(By locator, Scenario scenario) {
        driver= driverFactory.getCurrentDriver();
        byte[] screenshot = driver.findElement(locator).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot,"image/png", scenario.getName()+"_"+genericFunctions.getCurrentTimeStamp());
    }
    public void takeScreenshot(Scenario scenario) {
        driver = driverFactory.getCurrentDriver();
        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot,"image/png", scenario.getName()+"_"+genericFunctions.getCurrentTimeStamp());
    }



}