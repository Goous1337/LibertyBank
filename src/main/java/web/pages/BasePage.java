package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import web.drivers.DriverManager;

import java.time.Duration;

import static web.constans.credit.BaseConstants.BASE_DURATION;
import static web.constans.credit.BaseConstants.SUGGESTION_FIELD_OFFSET_X;
import static web.constans.credit.BaseConstants.SUGGESTION_FIELD_OFFSET_Y;
import static web.helpers.Waiters.waitElement;

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

    public void inputIntoSuggestionField(WebElement element, String keys) {
        new Actions(DriverManager.getDriver())
                .click(element)
                .sendKeys(keys)
                .pause(Duration.ofSeconds(BASE_DURATION))
                .moveByOffset(SUGGESTION_FIELD_OFFSET_X, SUGGESTION_FIELD_OFFSET_Y)
                .click()
                .perform();
    }

    public void inputIntoBasicField(WebElement elementBorder, WebElement elementInput, String data) {
        waitElement(elementBorder).click();
        waitElement(elementInput).sendKeys(data);
    }
}
