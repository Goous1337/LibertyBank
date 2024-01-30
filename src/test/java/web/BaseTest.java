package web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import web.drivers.DriverManager;
import web.steps.*;

import static web.constans.UrlConfig.BASE_URL;

public class BaseTest {

    protected static final WebDriver driver = DriverManager.getDriver();

    protected AccountSteps accountSteps = new AccountSteps();

    protected AccountInfoSteps accountInfoSteps = new AccountInfoSteps();

    protected CreateAccountSteps createAccountSteps = new CreateAccountSteps();

    protected ConfirmationSteps confirmationSteps = new ConfirmationSteps();

    protected RenameAccountSteps renameAccountSteps = new RenameAccountSteps();

    @BeforeAll
    public static void setUp() {
        driver.get(BASE_URL);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    protected void open(String pageUrl) {
        driver.get(BASE_URL + pageUrl);
    }
}