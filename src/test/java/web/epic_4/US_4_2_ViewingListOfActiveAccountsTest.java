package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("4 - Счета")
@Feature("US-4.2 Просмотр списка активных счетов")
@DisplayName("US-4.2 Просмотр списка активных счетов")
public class US_4_2_ViewingListOfActiveAccountsTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ACCOUNTS_URL);
    }

    @Test
    @TmsLink("LIB2-2431")
    @DisplayName("Просмотр открытых счетов")
    public void viewingOpenAccounts() {
        accountSteps.assertAccountStatusIsOpen();
        accountSteps.assertAccountStatusIsDisplayed();
        accountSteps.accountNameOrNumberIsDisplayed();
        accountSteps.amountIsDisplayed();
        accountSteps.currencyIsDisplayed();
        accountSteps.accountTypeIsDisplayed();
    }

    @Test
    @TmsLink("LIB2-2432")
    @DisplayName("Просмотр закрытых счетов")
    public void viewingClosedAccounts() {
        accountSteps.selectClosedAccounts();
        accountSteps.assertAccountStatusIsClosed();
    }

    @Test
    @TmsLink("LIB2-2433")
    @DisplayName("Просмотр заблокированных счетов")
    public void viewingBlockedAccounts() {
        accountSteps.selectBlockedAccounts();
        accountSteps.assertAccountStatusIsBlocked();
    }

    @Test
    @TmsLinks({@TmsLink("LIB2-2437"), @TmsLink("LIB2-2438"), @TmsLink("LIB2-2439"), @TmsLink("LIB2-2440")})
    @DisplayName("Фильтрация счетов по валюте")
    public void filterAccountsByCurrency() {
        accountSteps.filterByRubles();
        accountSteps.assertRubleImageIsDisplayed();
        accountSteps.filterByDollars();
        accountSteps.assertDollarImageIsDisplayed();
        accountSteps.filterByEuro();
        accountSteps.assertEuroImageIsDisplayed();
        accountSteps.filterByAllCurrency();
        accountSteps.assertRubleImageIsDisplayed();
        accountSteps.assertDollarImageIsDisplayed();
        accountSteps.assertEuroImageIsDisplayed();
    }
}

