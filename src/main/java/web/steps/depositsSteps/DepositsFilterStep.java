package web.steps.depositsSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.depositPages.DepositsFilterPage;

import static constant.DepositConstants.NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE;

public class DepositsFilterStep {

    protected DepositsFilterPage depositsFilterPage;

    public DepositsFilterStep() {
        depositsFilterPage = new DepositsFilterPage();
    }

    @Step("Клик по кнопке 'Фильтры'")
    public void clickFilterButton() {
        depositsFilterPage.clickDepositFilter();
    }

    @Step("Клик по кнопке 'Любая'")
    public void clickAnyButton() {
        depositsFilterPage.clickAnyButton();
    }

    @Step("Клик по кнопке 'Для накопления'")
    public void clickForAccumulationButton() {
        depositsFilterPage.clickForAccumulationButton();
    }

    @Step("Клик по кнопке 'Для расчета'")
    public void clickForCalculationButton() {
        depositsFilterPage.clickForCalculationButton();
    }

    @Step("Клик по кнопке 'RUB'")
    public void clickCurrencyRUBButton() {
        depositsFilterPage.clickCurrencyRUBButton();
    }

    @Step("Клик по кнопке 'USD'")
    public void clickCurrencyUSDButton() {
        depositsFilterPage.clickCurrencyUSDButton();
    }

    @Step("Клик по кнопке 'EUR'")
    public void clickCurrencyEURButton() {
        depositsFilterPage.clickCurrencyEURButton();
    }

    @Step("Ввод значения в поле ввода 'Сумма депозита'")
    public void enterValidateAmountDepositInput(String amountDeposit) {
        depositsFilterPage.fillSumOfDepositField(amountDeposit);
    }

    @Step("Ввод значения в поле ввода 'Срок депозита'")
    public void enterValidateTermOfDepositInput(String periodMonths) {
        depositsFilterPage.fillTermOfDepositField(periodMonths);
    }

    @Step("Проверка отображения кнопки 'Любая'")
    public void assertAnyButtonIsDisplayed() {
        Assertions.assertTrue(depositsFilterPage.anyButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка 'Любая' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения кнопки 'Для накопления'")
    public void assertForAccumulationIsDisplayed() {
        Assertions.assertTrue(depositsFilterPage.forAccumulationButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка 'Для накопления' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения кнопки 'Для расчета'")
    public void assertForCalculationIsDisplayed() {
        Assertions.assertTrue(depositsFilterPage.forCalculationButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка 'Для расчета' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения кнопки 'RUB'")
    public void assertForRubIsDisplayed() {
        Assertions.assertTrue(depositsFilterPage.currencyRUBButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка 'RUB' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения кнопки 'USD'")
    public void assertForUsdIsDisplayed() {
        Assertions.assertTrue(depositsFilterPage.currencyUSDButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка 'USD' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения кнопки 'EUR'")
    public void assertForEurIsDisplayed() {
        Assertions.assertTrue(depositsFilterPage.currencyEURButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка 'EUR' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения поля 'Сумма депозита'")
    public void assertFieldAmountOfDepositDisplayed() {
        Assertions.assertTrue(depositsFilterPage.fieldAmountOfDepositDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Поле 'Сумма депозита' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения поля 'Срок депозита'")
    public void assertFieldTermOfDepositDisplayed() {
        Assertions.assertTrue(depositsFilterPage.fieldTermOfDepositDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Поле 'Срок депозита' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения значений в поле 'Сумма депозита'")
    public void assertFieldMeaningSumRubOfDepositDisplayed() {
        Assertions.assertTrue(depositsFilterPage.fieldMeaningSumRubOfDepositDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Значения в поле 'Сумма депозита'не отображаются"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения значений в поле 'Срок депозита'")
    public void assertFieldMeaningTermOfDepositDisplayed() {
        Assertions.assertTrue(depositsFilterPage.fieldMeaningTermOfDepositDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Значения в поле 'Срок депозита'не отображаются"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения значений в поле 'Срок депозита' для депозитов в валюте")
    public void assertFieldMeaningSumCurrencyOfDepositDisplayed() {
        Assertions.assertTrue(depositsFilterPage.fieldMeaningSumCurrencyOfDepositDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Значения в поле 'Срок депозита' для валютных депозитов не отображаются"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения депозита 'Liberty+Детский'")
    public void asserNameOfLibertyChildDeposit() {
        Assertions.assertTrue(depositsFilterPage.isNameOfLibertyChildDeposit(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Депозит 'Liberty+Детский' не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения отсутствия депозитного продукта, соответствующего фильтрам")
    public void asserEmptyDeposit() {
        Assertions.assertTrue(depositsFilterPage.isEmptyDeposit(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Окно, информирующее об отсутствии депозита, соответствующего условиям фильтра не отображается"));
        TestListener.takeScreenshot();
    }
}
