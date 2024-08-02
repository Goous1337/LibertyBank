package web.epic_11;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.INVESTMENT_URL;

@Tags({@Tag("Web"), @Tag("2.0")})
@Epic("11 - Инвестиции")
@Feature("US-11.0 Вход в Сервис инвестиций")
@DisplayName("US-11.0 Вход в Сервис инвестиций, если у клиента есть брокерский счет")
public class US_11_0_LoginInvestmentService extends BaseTest {

    @Test
    @TmsLink("LIB6-1034")
    @DisplayName("US-11.0 Вход в Сервис инвестиций, если у клиента есть брокерский счет")
    public void viewingActiveAccount() {
        authorization();
        open(INVESTMENT_URL);
        investmentMainSteps.assertOpenNewBrokerageAccountButtonDisplayed();
        investmentMainSteps.assertBriefcaseButtonDisplayed();
        investmentMainSteps.assertCatalogButtonDisplayed();
        investmentMainSteps.assertAnalyticsButtonDisplayed();
        investmentMainSteps.assertNewsButtonDisplayed();
        investmentMainSteps.assertEducationButtonDisplayed();
        investmentMainSteps.assertTopUpAccountButtonDisplayed();
        investmentMainSteps.assertWithdrawFundsButtonDisplayed();
        investmentMainSteps.assertTransactionHistoryButtonDisplayed();
        investmentMainSteps.assertStockButtonDisplayed();
        investmentMainSteps.assertBondsButtonDisplayed();
        investmentMainSteps.assertCurrencyButtonDisplayed();
        investmentMainSteps.assertActiveBrokerageAccountDisplayed();
    }

    @Test
    @TmsLink("LIB6-1031")
    @DisplayName("US-11.0 Вход в Сервис инвестиций, если у клиента нет брокерский счет")
    public void viewingMainPageAccounts() {
        authorizationWithoutBrokerage();
        open(INVESTMENT_URL);
        investmentMainSteps.assertOpenBrokerageAccountButtonDisplayed();
        investmentMainSteps.assertBriefcaseButtonDisplayed();
        investmentMainSteps.assertCatalogButtonDisplayed();
        investmentMainSteps.assertAnalyticsButtonDisplayed();
        investmentMainSteps.assertNewsButtonDisplayed();
        investmentMainSteps.assertEducationButtonDisplayed();
    }
}