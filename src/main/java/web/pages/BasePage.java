package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import web.drivers.DriverManager;

import java.time.Duration;

public abstract class BasePage {

    public BasePage() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void scrollToElement(WebElement element) {
        new Actions(DriverManager.getDriver()).scrollToElement(element).perform();
    }

    public String getTextElement(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("Text WebElement cannot be find");
        }
        return element.getText();
    }

    public void clickByOffset(Integer x, Integer y) {
        new Actions(DriverManager.getDriver()).pause(Duration.ofSeconds(5))
                .moveByOffset(x, y)
                .click()
                .perform();
    }
}
