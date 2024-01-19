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

import static web.constans.AccountStatusConstant.*;
import static web.constans.UrlConfig.ACCOUNTS_URL;
import static web.constans.UrlConfig.BASE_URL;

@Epic("Epic-4")
@Feature("US-4.2 Просмотр списка активных счетов")
@DisplayName("US-4.2 Просмотр списка активных счетов")
public class ViewingListOfActiveAccountsTest extends BaseTest {

    private static final String ERROR_MESSAGE = "'%s' не отображается";
    private static final String STATUS_ERROR_MESSAGE = "Статус не совпадает с '%s'";

    @BeforeEach
    public void goToAccounts() {
        driver.get(BASE_URL + ACCOUNTS_URL);
    }


    @Test
    @TmsLink("LIB2-2431")
    @DisplayName("Просмотр открытых счетов")
    public void viewingOpenAccounts() {
        Assertions.assertEquals(OPEN, accountSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, "Активный"));
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountSteps.accountStatusIsDisplayed(), String.format(ERROR_MESSAGE, "Статус счета")),
                () -> Assertions.assertTrue(accountSteps.accountNameOrNumberIsDisplayed(), String.format(ERROR_MESSAGE, "Номер или имя счета")),
                () -> Assertions.assertTrue(accountSteps.amountIsDisplayed(), String.format(ERROR_MESSAGE, "Сумма счета")),
                () -> Assertions.assertTrue(accountSteps.currencyIsDisplayed(), String.format(ERROR_MESSAGE, "Валюта счета")),
                () -> Assertions.assertTrue(accountSteps.accountTypeIsDisplayed(), String.format(ERROR_MESSAGE, "Тип счета"))
        );
    }

    @Test
    @TmsLink("LIB2-2432")
    @DisplayName("Просмотр закрытых счетов")
    public void viewingClosedAccounts() {
        accountSteps.selectClosedAccounts();
        Assertions.assertEquals(CLOSED, accountSteps.getAccountStatus(), String.format(ERROR_MESSAGE, "Закрыт"));
    }

    @Test
    @TmsLink("LIB2-2433")
    @DisplayName("Просмотр заблокированных счетов")
    public void viewingBlockedAccounts() {
        accountSteps.selectBlockedAccounts();
        Assertions.assertEquals(BLOCKED, accountSteps.getAccountStatus(), String.format(ERROR_MESSAGE, "Заблокирован"));
    }

    @Test
    @TmsLinks({@TmsLink("LIB2-2437"), @TmsLink("LIB2-2438"), @TmsLink("LIB2-2439"), @TmsLink("LIB2-2440")})
    @DisplayName("Фильтрация счетов по валюте")
    public void filterAccountsByCurrency() {
        accountSteps.filterByRubles();
        Assertions.assertTrue(accountSteps.rubleImageIsDisplayed(), String.format(ERROR_MESSAGE, "Значок рубля"));
        accountSteps.filterByDollars();
        Assertions.assertTrue(accountSteps.dollarImageIsDisplayed(), String.format(ERROR_MESSAGE, "Значок доллара"));
        accountSteps.filterByEuro();
        Assertions.assertTrue(accountSteps.euroImageIsDisplayed(), String.format(ERROR_MESSAGE, "Значок евро"));
        accountSteps.filterByAllCurrency();
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountSteps.rubleImageIsDisplayed(), String.format(ERROR_MESSAGE, "Значок рубля")),
                () -> Assertions.assertTrue(accountSteps.dollarImageIsDisplayed(), String.format(ERROR_MESSAGE, "Значок доллара")),
                () -> Assertions.assertTrue(accountSteps.euroImageIsDisplayed(), String.format(ERROR_MESSAGE, "Значок евро"))
        );
    }
}

