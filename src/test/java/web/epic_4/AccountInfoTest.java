package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.ACCOUNTS_URL;
import static web.constans.AccountServiceConstants.*;

@Tag("Web")
@Epic("4 - Счета")
@Feature("US-4.3 Просмотр подробной информации о счете")
@DisplayName("US-4.3 Просмотр подробной информации о счете")
public class AccountInfoTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ACCOUNTS_URL);
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации об открытом счете")
    public void checkActiveAccountInfo() {
        accountSteps.selectOpenAccounts();
        accountSteps.clickAccount();
        commonInfoChecks();
        Assertions.assertEquals(OPEN_ACCOUNT_STATUS, accountInfoSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, OPEN_ACCOUNT_STATUS));
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации о закрытом счете")
    public void checkClosedAccountInfo() {
        accountSteps.selectClosedAccounts();
        accountSteps.clickAccount();
        commonInfoChecks();
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountInfoSteps.isCloseDateDisplayed(), String.format(DISPLAYED_MESSAGE, "Дата закрытия счета")),
                () -> Assertions.assertEquals(CLOSED_ACCOUNT_STATUS, accountInfoSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, CLOSED_ACCOUNT_STATUS))
        );
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации о заблокированном счете")
    public void checkBlockedAccountInfo() {
        accountSteps.selectBlockedAccounts();
        accountSteps.clickAccount();
        commonInfoChecks();
        Assertions.assertEquals(BLOCKED_ACCOUNT_STATUS, accountInfoSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, BLOCKED_ACCOUNT_STATUS));
    }

    private void commonInfoChecks() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountInfoSteps.isAccountNameDisplayed(), String.format(DISPLAYED_MESSAGE, "Название счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isAccountNumberDisplayed(), String.format(DISPLAYED_MESSAGE, "Номер счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isOpenDateDisplayed(), String.format(DISPLAYED_MESSAGE, "Дата открытия счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isContractNumberDisplayed(), String.format(DISPLAYED_MESSAGE, "Номер договора")),
                () -> Assertions.assertTrue(accountInfoSteps.isAccountStatusDisplayed(), String.format(DISPLAYED_MESSAGE, "Статус счета")),
                () -> Assertions.assertTrue(accountInfoSteps.isCurrencyIconDisplayed(), String.format(DISPLAYED_MESSAGE, "Иконка валюты")),
                () -> Assertions.assertTrue(accountInfoSteps.isBalanceDisplayed(), String.format(DISPLAYED_MESSAGE, "Баланс"))
        );
    }
}
