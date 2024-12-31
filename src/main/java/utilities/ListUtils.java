package utilities;

import browserFactory.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ListUtils {

    private Utils utils;
    Select select;


    public ListUtils(DriverManager driverManager){
        utils = new Utils(driverManager);
    }



    public void selectDropListOptions(WebElement element,int value,int timeout){

        if(utils.waitForElementToBeClickable(element,timeout)){
            select = new Select(element);

            select.selectByIndex(value);
        }
    }



    public void selectDropListOptions(WebElement element,String value,int timeout){

        if(utils.waitForElementToBeClickable(element,timeout)){
            select = new Select(element);

            select.selectByValue(value);
        }
    }



}
