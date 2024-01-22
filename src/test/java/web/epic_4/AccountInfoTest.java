package web.epic_4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import property.WebPropertiesReader;
import web.BaseTest;
import web.drivers.DriverManager;

import static web.constans.UrlConfig.ACCOUNTS_URL;

public class AccountInfoTest extends BaseTest {

    private static WebDriver driver = DriverManager.getDriver();

    private static final String ELEMENT_VERIFICATION_MESSAGE = "%s не отображается";

    @BeforeEach
    public void setUpTest() {
        driver.get(WebPropertiesReader.getWebBaseUrl() + ACCOUNTS_URL);
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации об открытом счете")
    public void checkActiveAccountInfo() {
        accountSteps.clickOpenAccountsTab();
        accountSteps.clickAccount();
        commonInfoChecks();
        Assertions.assertEquals("Активный", accountInfoSteps.getAccountStatus(), "Счет не является активным");
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации о закрытом счете")
    public void checkClosedAccountInfo() {
        accountSteps.clickClosedAccountsTab();
        accountSteps.clickAccount();
        commonInfoChecks();
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountInfoSteps.isCloseDateDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Дата закрытия счета")),
                () -> Assertions.assertEquals("Закрыт", accountInfoSteps.getAccountStatus(), "Счет не является закрытым")
        );
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации о заблокированном счете")
    public void checkBlockedAccountInfo() {
        accountSteps.clickBlockedAccountsTab();
        accountSteps.clickAccount();
        commonInfoChecks();
        Assertions.assertEquals("Заблокирован", accountInfoSteps.getAccountStatus(), "Счет не является заблокированным");
    }

    private void commonInfoChecks() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountInfoSteps.isAccountNameDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Название счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isAccountNumberDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Номер счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isOpenDateDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Дата открытия счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isContractNumberDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Номер договора")),
                () -> Assertions.assertTrue(accountInfoSteps.isAccountStatusDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Статус счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isCurrencyIconDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Иконка валюты")),
                () -> Assertions.assertTrue(accountInfoSteps.isBalanceDisplayed(), String.format(ELEMENT_VERIFICATION_MESSAGE, "Баланс"))
        );
    }
}
