package web.steps.cardSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.cardPages.CardProductInfoPage;

import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;
import static web.constans.AccountServiceConstants.NOT_EQUALS_MESSAGE;

public class CardProductInfoSteps {
    protected CardProductInfoPage cardProductInfoPage;

    public CardProductInfoSteps() {
        cardProductInfoPage = new CardProductInfoPage();
    }

    @Step("Название карты соответствует ожидаемому")
    public void assertCardTitleEquals(String cardTitle) {
        Assertions.assertEquals(cardTitle, cardProductInfoPage.getCardTitle(), String.format(NOT_EQUALS_MESSAGE, "Название карты"));
    }

    @Step("Срок действия карты соответствует ожидаемому")
    public void assertCardValidityEquals(String validity) {
        Assertions.assertEquals(validity, cardProductInfoPage.getValidity(), String.format(NOT_EQUALS_MESSAGE, "Срок действия"));
    }

    @Step("Стоимость обслуживания карты соответствует ожидаемой")
    public void assertCardServiceCostEquals(String serviceCost) {
        Assertions.assertEquals(serviceCost, cardProductInfoPage.getServiceCost(), String.format(NOT_EQUALS_MESSAGE, "Стоимость обслуживания"));
    }

    @Step("Валюта карты соответствует ожидаемой")
    public void assertCardCurrencyEquals(String currency) {
        Assertions.assertEquals(currency, cardProductInfoPage.getCardCurrency(), String.format(NOT_EQUALS_MESSAGE, "Валюта карты"));
    }

    @Step("Отображается изображение карты")
    public void assertIsCardImageDisplayed() {
        Assertions.assertTrue(cardProductInfoPage.isImageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Изображение карты"));
    }
}
