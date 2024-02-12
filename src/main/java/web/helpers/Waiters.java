package web.helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static web.drivers.DriverManager.getDriver;

public class Waiters {

    public static final long TIME_TO_WAIT = 15L;

    public static void waitElement(WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(TIME_TO_WAIT))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }
}
