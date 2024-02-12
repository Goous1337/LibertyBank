package web;

import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import web.drivers.DriverManager;
import web.steps.*;

import static property.UserPropertiesReader.USER_PASSWORD;
import static property.UserPropertiesReader.USER_PHONE;
import static web.constans.UrlConfig.BASE_URL;
import static web.constans.UrlConfig.LOGIN_URL;

public class BaseTest {

    protected static WebDriver driver = DriverManager.getDriver();

    protected AccountSteps accountSteps = new AccountSteps();

    protected AccountInfoSteps accountInfoSteps = new AccountInfoSteps();

    protected CreateAccountSteps createAccountSteps = new CreateAccountSteps();

    protected ConfirmationSteps confirmationSteps = new ConfirmationSteps();

    protected RenameAccountSteps renameAccountSteps = new RenameAccountSteps();

    protected static LoginSteps loginSteps = new LoginSteps();

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    protected static void open(String pageUrl) {
        driver.get(BASE_URL + pageUrl);
    }

    protected static void authorization() {
        open(LOGIN_URL);
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.enterPassword(USER_PASSWORD);
        loginSteps.tapSubmitButton();
    }
}