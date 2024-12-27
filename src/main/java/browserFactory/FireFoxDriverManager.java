package browserFactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.io.IOException;

public class FireFoxDriverManager extends DriverManager {

    private GeckoDriverService service;


    @Override
    protected void startService() {
        if(service == null){
            try {
                String firefoxDriverPath = dataProviders.ConfigReader.getProperty("firefox.driver.path");
                service = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(firefoxDriverPath))
                        .usingAnyFreePort()
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
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-fullscreen");
        // Optional headless mode:
        // options.setHeadless(true);
        return new FirefoxDriver(service, options);
    }
}
