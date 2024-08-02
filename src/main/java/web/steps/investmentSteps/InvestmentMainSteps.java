package web.steps.investmentSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.investmentPages.InvestmentMainPage;

import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;

public class InvestmentMainSteps {
    protected InvestmentMainPage investmentMainPage;

    public InvestmentMainSteps() {
        investmentMainPage = new InvestmentMainPage();
    }

    @Step("Отображается кнопка 'Открыть брокерский счет'")
    public void assertOpenBrokerageAccountButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isOpenBrokerageAccountButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Открыть брокерский счет'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Открыть новый счет'")
    public void assertOpenNewBrokerageAccountButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isOpenNewBrokerageAccountButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Открыть новый счет'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Портфель'")
    public void assertBriefcaseButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isBriefcaseButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Портфель'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Каталог'")
    public void assertCatalogButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isCatalogButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Каталог'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Аналитика'")
    public void assertAnalyticsButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isAnalyticsButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Аналитика'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Новости'")
    public void assertNewsButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isNewsButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Новости'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Обучение'")
    public void assertEducationButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isEducationButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Обучение'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Пополнить счет'")
    public void assertTopUpAccountButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isTopUpAccountButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Пополнить счет'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Вывести средства'")
    public void assertWithdrawFundsButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isWithdrawFundsButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Вывести средства'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'История операций'")
    public void assertTransactionHistoryButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isTransactionHistoryButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'История операций'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Акции'")
    public void assertStockButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isStockButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Акции'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Облигации'")
    public void assertBondsButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isBondsButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Облигации'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Валюта'")
    public void assertCurrencyButtonDisplayed() {
        Assertions.assertTrue(investmentMainPage.isCurrencyButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Валюта'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается иконка активного брокерского счета")
    public void assertActiveBrokerageAccountDisplayed() {
        Assertions.assertTrue(investmentMainPage.isActiveBrokerageAccountDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Иконка активного брокерского счета"));
        TestListener.takeScreenshot();
    }
}
