package dataProviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {


    private static Properties properties;


    // Static block to load the properties file when the class is loaded
    static {
       try(FileInputStream fis = new FileInputStream("src/main/resources/Configuration.properties"))
       {
           properties = new Properties();
           properties.load(fis);

        } catch (IOException e) {
           e.printStackTrace();
           throw new RuntimeException("Failed to load configuration file: config.properties");
       }
    }
    /**
     * Retrieve the value for a given key from the properties file.
     *
     * @param key The key to look up in the configuration file.
     * @return The value associated with the key, or null if the key doesn't exist.
     */
    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}





