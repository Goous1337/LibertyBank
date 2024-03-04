package web.epic_5;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.BaseTest;

import static web.constans.UrlConfig.CARDS_URL;

@Tag("Web")
@Epic("5 - Карты")
@Feature("US-5.3 Просмотр списка активных карт")
@DisplayName("US-5.3 Просмотр списка активных карт")
public class ViewingListOfActiveCardsTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CARDS_URL);
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-991")
    @DisplayName("Просмотр списка активных карт пользователя")
    public void viewActiveCards() {
        cardSteps.assertCardTypeIsDisplayed();
        cardSteps.assertCardNumberIsDisplayed();
        cardSteps.assertCardValidityPeriodIsDisplayed();
        cardSteps.assertCardBalanceIsDisplayed();
        cardSteps.assertCardCurrencyIsDisplayed();
        cardSteps.assertCardPaymentSystemIsDisplayed();
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-999")
    @DisplayName("Фильтрация списка активных карт пользователя по валютам")
    public void filterActiveCardsByCurrencies() {
        filterCardsSteps.filterCardsByCurrencyInRub();
        cardSteps.assertCurrencyEqualsExpected();
        filterCardsSteps.filterCardsByCurrencyInUsd();
        cardSteps.assertCurrencyEqualsExpected();
        filterCardsSteps.filterCardsByCurrencyInEur();
        cardSteps.assertCurrencyEqualsExpected();
        filterCardsSteps.filterCardsByAllCurrencies();
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-1000")
    @DisplayName("Отображение сообщения в случае, если у пользователя отсутствуют карточные продукты")
    public void messageIsDisplayedWhenCardProductsAreMissing() {
        filterCardsSteps.filterCardsByCreditType();
        confirmationSteps.assertMissingCardProductsMessageIsDisplayed();
        confirmationSteps.assertOpenCardProductIsEnabled();
    }
}
