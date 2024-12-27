package browserFactory;


import org.openqa.selenium.WebDriver;

public  abstract class DriverManager {

    // ThreadLocal to maintain separate WebDriver instances for parallel execution
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Abstract methods to be implemented by specific browser managers
    protected abstract void startService();  // Initialize browser service
    protected abstract void stopService();  // Clean up browser service
    protected abstract  WebDriver createDriverInstance(); // Create new browser instance

    // Get or create WebDriver instance in thread-safe manner
    public WebDriver getDriver(){
        if(driverThreadLocal.get()==null){
            startService();
            driverThreadLocal.set(createDriverInstance());
        }

        return  driverThreadLocal.get();

    }

    public void quitDriver(){
        if(driverThreadLocal.get() != null){
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
        stopService();
    }

}
