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
@Feature("US-5.6.1.ALL Блокировка/разблокировка карты")
@DisplayName("US-5.6.1.ALL Блокировка/разблокировка карты")
public class US_5_6_1_ALL_BlockCardTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CARDS_URL);
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-2941")
    @DisplayName("Заблокировать карту")
    public void blockCardTest() {
        cardSteps.clickCardInfoForActiveCard();
        cardInfoSteps.blockCard();
        confirmationSteps.accept();
        cardInfoSteps.assertBlockedCardStatusDisplayed();
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-2941")
    @DisplayName("Разблокировать карту")
    public void unblockCardTest() {
        cardSteps.clickCardInfoForBlockedCard();
        cardInfoSteps.unblockCard();
        confirmationSteps.accept();
        cardInfoSteps.assertActiveCardStatusDisplayed();
    }
}
