package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tag("Web")
@Epic("4 - Счета")
@Feature("US-4.3 Просмотр подробной информации о счете")
@DisplayName("US-4.3 Просмотр подробной информации о счете")
public class AccountInfoTest extends BaseTest {

    private static final String ELEMENT_VERIFICATION_MESSAGE = "%s не отображается";

    @BeforeEach
    public void setUpTest() {
        open(ACCOUNTS_URL);
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации об открытом счете")
    public void checkActiveAccountInfo() {
        accountSteps.selectOpenAccounts();
        accountSteps.clickAccount();
        commonInfoChecks();
        Assertions.assertEquals("Активный", accountInfoSteps.getAccountStatus(), "Счет не является активным");
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации о закрытом счете")
    public void checkClosedAccountInfo() {
        accountSteps.selectClosedAccounts();
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
        accountSteps.selectBlockedAccounts();
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
