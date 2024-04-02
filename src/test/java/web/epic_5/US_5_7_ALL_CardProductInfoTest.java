package web.epic_5;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import web.BaseTest;

import static constant.CardInfoConstants.*;
import static constant.CardServiceConstants.*;
import static web.constans.UrlConfig.CARD_PRODUCTS_URL;

@Tag("Web")
@Epic("5 - Карты")
@Feature("US-5.7.ALL Просмотр подробной информации по карточному продукту")
@DisplayName("US-5.7.ALL Просмотр подробной информации по карточному продукту")
public class US_5_7_ALL_CardProductInfoTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @ParameterizedTest
    @Tag("Web")
    @TmsLink("LIB2-2945")
    @DisplayName("Проверка отображения необходимой информации о карте")
    @Description("Должны корректно отображаться имя, срок действия, стоимость обслуживания и валюта карты")
    @CsvSource({
            CARD_NAME_CLASSIC + ", " + VALIDITY_60_MONTHS + ", " + CLASSIC_CARD_COST + ", " + CURRENCY_RUB,
            CARD_NAME_CHILD + ", " + VALIDITY_36_MONTHS + ", " + CHILD_CARD_COST + ", " + CURRENCY_RUB,
            CARD_NAME_GOLD + ", " + VALIDITY_60_MONTHS + ", " + GOLD_CARD_COST + ", " + CURRENCY_RUB_USD_EUR,
            CARD_NAME_PLATINUM + ", " + VALIDITY_60_MONTHS + ", " + PLATINUM_CARD_COST + ", " + CURRENCY_USD_RUB_EUR,
            CARD_NAME_VIRTUAL + ", " + VALIDITY_36_MONTHS + ", " + VIRTUAL_CARD_COST + ", " + CURRENCY_RUB_USD_EUR,
            CARD_NAME_SECURE + ", " + VALIDITY_12_MONTHS + ", " + SECURE_CARD_COST + ", " + CURRENCY_RUB_USD_EUR,
            CARD_NAME_TRAVEL + ", " + VALIDITY_60_MONTHS + ", " + TRAVEL_CARD_COST + ", " + CURRENCY_USD_EUR
    })
    public void cardProductsInfoTest(String name, String validity, String cost, String currency) {
        open(CARD_PRODUCTS_URL + "/" + name);
        cardProductInfoSteps.assertIsCardImageDisplayed();
        //cardProductInfoSteps.assertCardTitleEquals(name);
        cardProductInfoSteps.assertCardValidityEquals(validity);
        cardProductInfoSteps.assertCardServiceCostEquals(cost);
        cardProductInfoSteps.assertCardCurrencyEquals(currency);
    }
}
