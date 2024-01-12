package web;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import property.WebPropertiesReader;
import web.drivers.DriverManager;

public class BaseTest {

    private static final WebDriver driver = DriverManager.getDriver();

    @BeforeAll
    public static void setUp() {
       driver.get(WebPropertiesReader.getWebBaseUrl());
    }
}
