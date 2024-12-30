package utilities;

import browserFactory.DriverManager;
import io.cucumber.java.et.Ja;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.apache.commons.logging.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import javax.swing.*;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.logging.Logger;

public class Utils {


    public static DriverManager driverManager;



    Function<String,Boolean> pageText = new Function<String, Boolean>() {
        @Override
        public Boolean apply(String pageText) {
            return driverManager.getDriver().getPageSource().contains(pageText);

        }
    };

    static Function<WebElement,Boolean> visibility = new Function<WebElement, Boolean>() {
        @Override
        public Boolean apply(WebElement webElement) {
            return webElement.isDisplayed();
        }
    };

    static Function<WebElement,Boolean> inVisibility = new Function<WebElement, Boolean>() {
        @Override
        public Boolean apply(WebElement webElement) {
            return webElement.isDisplayed();
        }
    };

    static Function<WebElement,Boolean> present = new Function<WebElement, Boolean>() {
        @Override
        public Boolean apply(WebElement webElement) {
            try {
                webElement.getTagName();
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
                return  false;
            }catch (StaleElementReferenceException e){
                System.out.println(e.getMessage());
                return  false;

            }
            return true;
        }
    };

    static Function<WebDriver,Boolean> pageLoad = new Function<WebDriver, Boolean>() {
        @Override
        public Boolean apply(WebDriver driver){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("return document.readyState").equals("complete");

        }
    };



    Function<WebElement,Boolean> active = new Function<WebElement, Boolean>() {
        @Override
        public Boolean apply(WebElement element) {
            return element.isEnabled();
        }
    };











    public Utils(DriverManager driverManager) {

        Utils.driverManager=driverManager;
    }
public synchronized void navigateToURL(String url,int timeout) {

    WebDriver driver = driverManager.getDriver();
    FluentWait<WebDriver> wait = new FluentWait<>(driverManager.getDriver())
            .withTimeout(Duration.ofSeconds(timeout));
    wait.until(pageLoad);
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
    driver.navigate().to(url);

}
public synchronized  void openNewWindow() {
        WebDriver driver = driverManager.getDriver();
        driver.switchTo().newWindow(WindowType.WINDOW);
}

public synchronized  void openNewTab(String url,int timeout){
        WebDriver driver = driverManager.getDriver();
        driver.switchTo().newWindow(WindowType.TAB);
        navigateToURL(url,timeout);
}

public String getPageTitle(){
        String title = driverManager.getDriver().getTitle();
        //System.out.println(title);
        return title;


}

public boolean waitForPageText(String text,int timout){

        FluentWait<String> wait = new FluentWait<String>(text).withTimeout(Duration.ofSeconds(timout)).ignoring(NoSuchElementException.class);
        wait.until(pageText);
        return true;
}

public boolean waitForElementToBeClickable(WebElement element,int timeout){
        try {
            FluentWait<WebElement> wait = new FluentWait<>(element).withTimeout(Duration.ofSeconds(timeout))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
            wait.until(active);
            return true;
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            return false;
        }
}

    public boolean waitForElementToBeVisible(WebElement element,int timeout){
        try {
            FluentWait<WebElement> wait = new FluentWait<>(element).withTimeout(Duration.ofSeconds(timeout))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
            wait.until(visibility);
            return true;
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean waitForElementToBeInVisible(WebElement element,int timeout){
        try {
            FluentWait<WebElement> wait = new FluentWait<>(element).withTimeout(Duration.ofSeconds(timeout));
            wait.until(inVisibility);
            return true;
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean waitForElementToBePresent(WebElement element,int timeout){
        try {
            FluentWait<WebElement> wait = new FluentWait<>(element).withTimeout(Duration.ofSeconds(timeout))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
            wait.until(present);
            return true;
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void click(WebElement element,int timout){

        if(waitForElementToBeClickable(element,timout)){
            try {
                element.click();
            }catch (ElementClickInterceptedException e){
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("element can't click");
        }
    }


    public void hover(WebElement element,int timeout){
        Actions action = new Actions(Utils.driverManager.getDriver());

        if(waitForElementToBeVisible(element,timeout)){
            action.moveToElement(element).build().perform();
        }


    }


    public void doubleClick(WebElement element,int timeout){
        Actions action = new Actions(Utils.driverManager.getDriver());

        if(waitForElementToBeClickable(element,timeout)){
            action.doubleClick(element).build().perform();
        }


    }


    public void rightClick(WebElement element,int timeout){
        Actions action = new Actions(Utils.driverManager.getDriver());

        if(waitForElementToBeClickable(element,timeout)){
            action.contextClick(element).build().perform();
        }


    }


    public void sendKeys(WebElement element,String value,int timeout){
        if(waitForElementToBeClickable(element,timeout)){
           clearField(element);
           try{
               element.sendKeys(value);
           }catch (ElementNotInteractableException e){
               System.out.println(e.getMessage());
           }
        }

    }

    private void clearField(WebElement element) {

        try {
            element.clear();
        } catch (ElementNotInteractableException e) {
            System.out.println(e.getMessage());
        }

    }

    public void scrollIntoViewUsingJsExe(WebElement element,int timeout){

        JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

        while (waitForElementToBeInVisible(element,timeout)){
            js.executeScript("arguments[0].scrollIntoView()",element);
            System.out.println("scrolling down");
        }
    }


}


