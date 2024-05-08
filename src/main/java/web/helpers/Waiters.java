package web.helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeSelected;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static web.drivers.DriverManager.getDriver;

public class Waiters {

    public static final long TIME_TO_WAIT = 15L;

    public static WebElement waitElement(WebElement element) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(TIME_TO_WAIT))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitIsElementNotDisplayed(WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static WebElement waitElementWithOwnTime(WebElement element, int time) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(time))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitCheckbox(boolean expectedState, WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(TIME_TO_WAIT))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(expectedState ? elementToBeSelected(element)
                        : not(elementToBeSelected(element)));
        return element;
    }

    public static void waitElementWithColor(WebElement element, String color) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(TIME_TO_WAIT))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> element.getCssValue("background-color").
                        equals(color));
    }
}
