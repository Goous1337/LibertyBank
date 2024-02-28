package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.CardInfoPage;

import static web.constans.AccountServiceConstants.NOT_EQUALS_MESSAGE;

public class CardInfoSteps {
    protected CardInfoPage cardInfoPage;

    public CardInfoSteps() {
        cardInfoPage = new CardInfoPage();
    }

    @Step("Проверить название карты")
    public void cardTitleCheck(String cardTitle) {
        Assertions.assertEquals(cardTitle, cardInfoPage.getCardTitle(), String.format(NOT_EQUALS_MESSAGE, "Название карты"));
    }

    @Step("Проверить срок действия карты")
    public void cardValidityCheck(String validity) {
        Assertions.assertEquals(validity, cardInfoPage.getValidity(), String.format(NOT_EQUALS_MESSAGE, "Срок действия"));
    }

    @Step("Проверить стоимость обслуживания карты")
    public void cardServiceCostCheck(String serviceCost) {
        Assertions.assertEquals(serviceCost, cardInfoPage.getServiceCost(), String.format(NOT_EQUALS_MESSAGE, "Стоимость обслуживания"));
    }

    @Step("Проверить валюту карты")
    public void cardCurrencyCheck(String currency) {
        Assertions.assertEquals(currency, cardInfoPage.getCardCurrency(), String.format(NOT_EQUALS_MESSAGE, "Валюта карты"));
    }
}
