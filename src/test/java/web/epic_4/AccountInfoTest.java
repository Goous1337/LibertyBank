package web.epic_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import property.WebPropertiesReader;
import web.BaseTest;
import web.drivers.DriverManager;

import static web.constans.UrlConfig.ACCOUNTS_URL;

public class AccountInfoTest extends BaseTest {

    private static final WebDriver driver = DriverManager.getDriver();

    private static final String ELEMENT_VERIFICATION_MESSAGE = "%s не отображается";

    @BeforeEach
    public void setUpTest() {
        driver.get(WebPropertiesReader.getWebBaseUrl() + ACCOUNTS_URL);
        accountSteps.clickMainAccountLabel();
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации в открытом счете")
    public void checkActiveAccountInfo() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountInfoSteps.isAccountNameDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Название счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isAccountNumberDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Номер счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isOpenDateDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Дата открытия счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isContractNumberDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Номер договора")),
                () -> Assertions.assertTrue(accountInfoSteps.isMainAccountLabelDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Знак основного счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isAccountStatusDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Статус счета"))
        );
    }
}
