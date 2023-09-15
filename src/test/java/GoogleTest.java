import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTest {

    private static final Logger logger = LogManager.getLogger(GoogleTest.class);


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Custom test name containing spaces")
    public void testGoogleSearch1() {
        logger.info("Информационное сообщение");
        logger.error("This is an error message");
        driver.get("https://www.onliner.by");
        WebElement element = driver.findElement(By.xpath("//input[@class='fast-search__input']"));
        element.sendKeys("iphone");
        driver.switchTo().frame(0);
        WebElement checkboxElement = driver.findElement(By.xpath("//a[text()='Смартфон Apple iPhone 14 128GB (полуночный)']/ancestor::div[@class='result__item result__item_product']//label[@class='product__compare']"));
        checkboxElement.click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}