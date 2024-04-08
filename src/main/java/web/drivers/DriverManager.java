package web.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import property.WebPropertiesReader;

import java.time.Duration;

import static web.constans.DriverConfig.*;
import static web.helpers.Waiters.TIME_TO_WAIT;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static WebDriver createDriver() {
        String browser = WebPropertiesReader.getWebDriver();
        return switch (browser) {
            case CHROME -> createChromeDriver();
            case FIREFOX -> createFirefoxDriver();
            case SAFARI -> createSafariDriver();
            default -> throw new IllegalArgumentException("Unknown browser: " + browser);
        };
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(TIME_TO_WAIT));
        options.addArguments("start-maximized");
//        options.addArguments("--headless=new");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        return new FirefoxDriver(options);
    }

    private static WebDriver createSafariDriver() {
        SafariOptions options = new SafariOptions();
        return new SafariDriver(options);
    }

    public static void resetDriver() {
        if (driver != null) {
//           driver.quit();
            driver = null;
        }
    }
}

