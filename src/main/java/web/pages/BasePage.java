package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import web.drivers.DriverManager;

public abstract class BasePage {

    public BasePage() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void scrollToElement(WebElement element) {
        new Actions(DriverManager.getDriver()).scrollToElement(element).perform();
    }
}
