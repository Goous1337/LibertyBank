package web.steps.cardSteps;

import io.qameta.allure.Step;
import web.pages.cardPages.FilterCardsPage;

public class FilterCardsSteps {

    protected FilterCardsPage filterCardsPage;

    public FilterCardsSteps() {
        filterCardsPage = new FilterCardsPage();
    }

    @Step("Отфильтровать список карт по всем типам")
    public void filterCardsByAllTypes() {
        filterCardsPage.clickAllCardTypesButton();
    }

    @Step("Отфильтровать список карт по типу 'Дебетовые'")
    public void filterCardsByDebitType() {
        filterCardsPage.clickDebitCardTypeButton();
    }

    @Step("Отфильтровать список карт по типу 'Кредитные'")
    public void filterCardsByCreditType() {
        filterCardsPage.clickCreditCardTypeButton();
    }

    @Step("Отфильтровать список карт по всей валюте")
    public void filterCardsByAllCurrencies() {
        filterCardsPage.clickAllCurrenciesButton();
    }

    @Step("Отфильтровать список карт по валюте 'RUB'")
    public void filterCardsByCurrencyInRub() {
        filterCardsPage.clickRubCurrencyButton();
    }

    @Step("Отфильтровать список карт по валюте 'USD'")
    public void filterCardsByCurrencyInUsd() {
        filterCardsPage.clickRubCurrencyButton();
    }

    @Step("Отфильтровать список карт по валюте 'EUR'")
    public void filterCardsByCurrencyInEur() {
        filterCardsPage.clickRubCurrencyButton();
    }
}
