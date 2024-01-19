package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import web.drivers.DriverManager;

public abstract class BasePage {

    public BasePage() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
}
