package web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import property.WebPropertiesReader;
import web.drivers.DriverManager;
import web.steps.AccountInfoSteps;
import web.steps.AccountSteps;

public class BaseTest {

    private static final WebDriver driver = DriverManager.getDriver();

    protected AccountSteps accountSteps = new AccountSteps();

    protected AccountInfoSteps accountInfoSteps = new AccountInfoSteps();

    @BeforeAll
    public static void setUp() {
        driver.get(WebPropertiesReader.getWebBaseUrl());
    }

    @AfterEach
    public void closeTab() {
        driver.close();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
