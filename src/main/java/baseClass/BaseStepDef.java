package baseClass;

import browserFactory.DriverManager;
import hooks.Hooks;
import org.openqa.selenium.WebDriver;
import utilities.Utils;

public class BaseStepDef {

    protected WebDriver driver;
    protected Utils utils;


public BaseStepDef(){

    DriverManager driverManager = Hooks.getDriverManager(); // Adjust as per your Hooks implementation
    this.driver = driverManager.getDriver();
    this.utils = new Utils(driverManager);

}



}
