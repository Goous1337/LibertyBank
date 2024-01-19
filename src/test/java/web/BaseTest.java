package web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import web.drivers.DriverManager;
import web.steps.AccountSteps;
import web.steps.AccountInfoSteps;

import static web.constans.UrlConfig.BASE_URL;

public class BaseTest {

    protected static final WebDriver driver = DriverManager.getDriver();

    protected AccountSteps accountSteps = new AccountSteps();

    protected AccountInfoSteps accountInfoSteps = new AccountInfoSteps();

    @BeforeAll
    public static void setUp() {
       driver.get(BASE_URL);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
