package browserFactory;

public class DriverManagerFactory {


    public static DriverManager getManager(String driverType) {

        switch (driverType) {
            case "CHROME":
                return new ChromeDriverManager();
            case "EDGE":
                return new EdgeDriverManager(); // Implement similarly
            default:
                throw new IllegalArgumentException("Invalid driver type: " + driverType);
        }



    }


}
