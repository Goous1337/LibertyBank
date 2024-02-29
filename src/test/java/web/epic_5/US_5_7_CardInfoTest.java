package web.epic_5;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import web.BaseTest;

import static web.constans.UrlConfig.*;

public class US_5_7_CardInfoTest extends BaseTest {
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
            "Liberty Card Classic, 60 месяцев, 99 ₽, RUB",
            "Liberty Card Child, 36 месяцев, 0 ₽, RUB",
            "Liberty Card Gold, 60 месяцев, 500 ₽ \\ 5 $ \\ 5 €, RUB \\ USD \\ EUR",
            "Liberty Card Platinum, 60 месяцев, 10 $ \\ 1 000 ₽ \\ 10 €, USD \\ RUB \\ EUR",
            "Liberty Card Virtual, 36 месяцев, 0 ₽ \\ 0 $ \\ 0 €, RUB \\ USD \\ EUR",
            "Liberty Card Secure, 12 месяцев, 0 ₽ \\ 0 $ \\ 0 €, RUB \\ USD \\ EUR",
            "Liberty Card Travel, 60 месяцев, 20 $ \\ 20 €, USD \\ EUR"
    })
    public void cardProductsInfoTest(String name, String validity, String cost, String currency) {
        open(CARD_PRODUCTS_URL + "/" + name);
        cardProductInfoSteps.assertIsCardImageDisplayed();
        cardProductInfoSteps.assertCardTitleEquals(name);
        cardProductInfoSteps.assertCardValidityEquals(validity);
        cardProductInfoSteps.assertCardServiceCostEquals(cost);
        cardProductInfoSteps.assertCardCurrencyEquals(currency);
    }
}
