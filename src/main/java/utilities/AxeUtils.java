package utilities;

import browserFactory.DriverManager;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AxeUtils {

    private final DriverManager driverManager;

    /**
     * Constructor to initialize AxeUtils with DriverManager
     *
     * @param driverManager DriverManager instance
     */
    public AxeUtils(DriverManager driverManager) {
        if (driverManager == null) {
            throw new IllegalArgumentException("DriverManager cannot be null");
        }
        this.driverManager = driverManager;
    }

    /**
     * Get WebDriver instance from DriverManager
     *
     * @return WebDriver instance
     */
    private WebDriver getDriver() {
        if (driverManager == null) {
            throw new IllegalStateException("DriverManager is not initialized");
        }
        return driverManager.getDriver();
    }

    /**
     * Runs accessibility analysis on the full page.
     *
     * @return AxeResults containing accessibility issues
     */
    public Results analyzeFullPage() {
        AxeBuilder axeBuilder = new AxeBuilder();
        return axeBuilder.analyze(getDriver());
    }

    /**
     * Runs accessibility analysis with specific WCAG standards.
     *
     * @param wcagStandard WCAG standard to use ("wcag2a", "wcag2aa", "wcag21a", "wcag21aa")
     * @return AxeResults containing accessibility issues
     */
    public Results analyzeWithWCAG(String wcagStandard) {
        List<String> stds = new ArrayList<>(Arrays.asList("wcag2a", "wcag2aa"));
        AxeBuilder axeBuilder = new AxeBuilder().withTags(stds);
        return axeBuilder.analyze(getDriver());
    }


}
