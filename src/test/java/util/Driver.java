package util;

import contstants.SeleniumConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;

public class Driver {
    private static WebDriver driver = null;
    public static final String propertyPath = "./src/test/resources/conf/configuration.properties";
    public static final String sauceUsername = ConfigReader.readProperty("sauceUser");
    public static final String sauceKey = ConfigReader.readProperty("sauceKey");
    public static final String sauceURL = "https://" + sauceUsername + ":" + sauceKey + "@ondemand.saucelabs.com:443/wd/hub";

    public static void initialize(String browser) {
        if (driver != null)
            return;

        String runInSauceLabs = ConfigReader.readProperty("runInSauceLabs");

        if ("true".equalsIgnoreCase(runInSauceLabs)) {
            driver = getRemoteDriver();
        } else {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    System.out.println("Invalid browser type specified.");
                    return;
            }

            // Driver settings for local browsers
            driver.manage().timeouts().implicitlyWait(SeleniumConstants.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(SeleniumConstants.PAGE_LOAD_TIME, TimeUnit.SECONDS);
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    public static WebDriver getDriver() {
        if (driver != null)
            return driver;

        // Ensure that the browser is set in the configuration
        String browser = ConfigReader.readProperty("browser");
        initialize(browser);
        return driver;
    }

    private static WebDriver getRemoteDriver() {
        WebDriver driver = null;

        try {
            ChromeOptions options = new ChromeOptions();
            // Set up browser version and OS for Sauce Labs
            options.setCapability("browserVersion", ConfigReader.readProperty("browser_version"));
            options.setCapability("platformName", ConfigReader.readProperty("os"));
            options.setCapability("sauce:options", new HashMap<>());

            // Setup RemoteWebDriver with the Sauce Labs URL
            driver = new RemoteWebDriver(new URL(sauceURL), options);
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL for Sauce Labs: " + sauceURL);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error while setting up the remote WebDriver: ");
            e.printStackTrace();
        }

        return driver;
    }
}
