import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTest {

    private static final Logger logger = LogManager.getLogger(GoogleTest.class);


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    @DisplayName("Custom test name containing spaces")
    public void testGoogleSearch1() {
        logger.info("Информационное сообщение");
        logger.error("This is an error message");
        driver.get("https://www.google.com");
        assertEquals("Google", driver.getTitle());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}