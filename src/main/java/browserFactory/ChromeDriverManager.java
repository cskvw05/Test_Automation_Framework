package browserFactory;

import dataProviders.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class ChromeDriverManager extends DriverManager {


    private ChromeDriverService service; //chrome specific services

    @Override
    protected void startService() {
        if(service==null){
            try{
                String chromeDriverPath = ConfigReader.getProperty("chrome.driver.path");
                service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(chromeDriverPath))
                        .usingAnyFreePort()
                        .withReadableTimestamp(true)
                        .build();
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void stopService() {
        if (service != null && service.isRunning()) {
            service.stop();
        }

    }

    @Override
    protected WebDriver createDriverInstance() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu");
        options.addArguments("start-maximized");
        return new ChromeDriver(service, options);

    }
}
