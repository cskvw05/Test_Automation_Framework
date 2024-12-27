package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;

public class EdgeDriverManager extends DriverManager {

    private EdgeDriverService service;


    @Override
    protected void startService() {
        if (service == null) {
            try {
                String edgeDriverPath = dataProviders.ConfigReader.getProperty("edge.driver.path");
                service = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File(edgeDriverPath))
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
        EdgeOptions options = new EdgeOptions();
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        return new EdgeDriver(service, options);
    }
}
