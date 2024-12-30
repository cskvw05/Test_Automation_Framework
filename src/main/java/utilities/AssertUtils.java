package utilities;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertUtils {

    private SoftAssert softAssert;

    // Constructor to initialize SoftAssert
    public AssertUtils() {
        this.softAssert = new SoftAssert();
    }

    // Hard Assert - Asserts that two strings are equal
    public void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            System.out.println("Hard assert passed: " + message);
        } catch (AssertionError e) {
            System.err.println("Hard assert failed: " + e.getMessage());
            throw e;
        }
    }

    // Hard Assert - Asserts that a condition is true
    public void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            System.out.println("Hard assert passed: " + message);
        } catch (AssertionError e) {
            System.err.println("Hard assert failed: " + e.getMessage());
            throw e;
        }
    }

    // Hard Assert - Asserts that a condition is false
    public void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
            System.out.println("Hard assert passed: " + message);
        } catch (AssertionError e) {
            System.err.println("Hard assert failed: " + e.getMessage());
            throw e;
        }
    }

    // Soft Assert - Asserts that two strings are equal
    public void softAssertEquals(String actual, String expected, String message) {
        try {
            softAssert.assertEquals(actual, expected, message);
            System.out.println("Soft assert recorded: " + message);
        } catch (Exception e) {
            System.err.println("Soft assert encountered an error: " + e.getMessage());
        }
    }

    // Soft Assert - Asserts that a condition is true
    public void softAssertTrue(boolean condition, String message) {
        try {
            softAssert.assertTrue(condition, message);
            System.out.println("Soft assert recorded: " + message);
        } catch (Exception e) {
            System.err.println("Soft assert encountered an error: " + e.getMessage());
        }
    }

    // Soft Assert - Asserts that a condition is false
    public void softAssertFalse(boolean condition, String message) {
        try {
            softAssert.assertFalse(condition, message);
            System.out.println("Soft assert recorded: " + message);
        } catch (Exception e) {
            System.err.println("Soft assert encountered an error: " + e.getMessage());
        }
    }

    // Method to validate all soft assertions
    public void assertAll() {
        try {
            softAssert.assertAll();
            System.out.println("All soft assertions validated successfully.");
        } catch (AssertionError e) {
            System.err.println("Soft assertion validation failed: " + e.getMessage());
            throw e;
        }
    }
}



