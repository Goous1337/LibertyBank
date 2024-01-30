package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.BaseTest;

import static web.constans.AccountServiceConstants.*;
import static web.constans.UrlConfig.ACCOUNTS_URL;

@Epic("Epic-4")
@Feature("US-4.2 Просмотр списка активных счетов")
@DisplayName("US-4.2 Просмотр списка активных счетов")
public class ViewingListOfActiveAccountsTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        open(ACCOUNTS_URL);
    }

    @Test
    @TmsLink("LIB2-2431")
    @DisplayName("Просмотр открытых счетов")
    public void viewingOpenAccounts() {
        Assertions.assertEquals(OPEN_ACCOUNT_STATUS, accountSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, OPEN_ACCOUNT_STATUS));
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountSteps.accountStatusIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус счета")),
                () -> Assertions.assertTrue(accountSteps.accountNameOrNumberIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Номер или имя счета")),
                () -> Assertions.assertTrue(accountSteps.amountIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сумма счета")),
                () -> Assertions.assertTrue(accountSteps.currencyIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Валюта счета")),
                () -> Assertions.assertTrue(accountSteps.accountTypeIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Тип счета"))
        );
    }

    @Test
    @TmsLink("LIB2-2432")
    @DisplayName("Просмотр закрытых счетов")
    public void viewingClosedAccounts() {
        accountSteps.selectClosedAccounts();
        Assertions.assertEquals(CLOSED_ACCOUNT_STATUS, accountSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, CLOSED_ACCOUNT_STATUS));
    }

    @Test
    @TmsLink("LIB2-2433")
    @DisplayName("Просмотр заблокированных счетов")
    public void viewingBlockedAccounts() {
        accountSteps.selectBlockedAccounts();
        Assertions.assertEquals(BLOCKED_ACCOUNT_STATUS, accountSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, BLOCKED_ACCOUNT_STATUS));
    }

    @Test
    @TmsLinks({@TmsLink("LIB2-2437"), @TmsLink("LIB2-2438"), @TmsLink("LIB2-2439"), @TmsLink("LIB2-2440")})
    @DisplayName("Фильтрация счетов по валюте")
    public void filterAccountsByCurrency() {
        accountSteps.filterByRubles();
        Assertions.assertTrue(accountSteps.rubleImageIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, RUB));
        accountSteps.filterByDollars();
        Assertions.assertTrue(accountSteps.dollarImageIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, USD));
        accountSteps.filterByEuro();
        Assertions.assertTrue(accountSteps.euroImageIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, EUR));
        accountSteps.filterByAllCurrency();
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountSteps.rubleImageIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, RUB)),
                () -> Assertions.assertTrue(accountSteps.dollarImageIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, USD)),
                () -> Assertions.assertTrue(accountSteps.euroImageIsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, EUR))
        );
    }
}

