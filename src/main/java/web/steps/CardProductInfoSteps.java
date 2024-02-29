package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.CardProductInfoPage;

import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;
import static web.constans.AccountServiceConstants.NOT_EQUALS_MESSAGE;

public class CardProductInfoSteps {
    protected CardProductInfoPage cardProductInfoPage;

    public CardProductInfoSteps() {
        cardProductInfoPage = new CardProductInfoPage();
    }

    @Step("Проверить название карты")
    public void assertCardTitleEquals(String cardTitle) {
        Assertions.assertEquals(cardTitle, cardProductInfoPage.getCardTitle(), String.format(NOT_EQUALS_MESSAGE, "Название карты"));
    }

    @Step("Проверить срок действия карты")
    public void assertCardValidityEquals(String validity) {
        Assertions.assertEquals(validity, cardProductInfoPage.getValidity(), String.format(NOT_EQUALS_MESSAGE, "Срок действия"));
    }

    @Step("Проверить стоимость обслуживания карты")
    public void assertCardServiceCostEquals(String serviceCost) {
        Assertions.assertEquals(serviceCost, cardProductInfoPage.getServiceCost(), String.format(NOT_EQUALS_MESSAGE, "Стоимость обслуживания"));
    }

    @Step("Проверить валюту карты")
    public void assertCardCurrencyEquals(String currency) {
        Assertions.assertEquals(currency, cardProductInfoPage.getCardCurrency(), String.format(NOT_EQUALS_MESSAGE, "Валюта карты"));
    }

    @Step("Изображение карты отображается")
    public void assertIsCardImageDisplayed() {
        Assertions.assertTrue(cardProductInfoPage.isImageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Изображение карты"));
    }
}
