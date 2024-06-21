package web.epic_5;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.CARDS_URL;

@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("5 - Карты")
@Feature("US-5.4 Просмотр информации по карте пользователя")
@DisplayName("US-5.4 Просмотр информации по карте пользователя")
public class US_5_4_UserCardsInfoTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CARDS_URL);
        cardSteps.clickCardInfoForActiveCard();
    }

    @Test
    @TmsLink("LIB2-1019")
    @DisplayName("Просмотр информации по активной карте пользователя")
    public void userCardInformationTest() {
        cardInfoSteps.assertActiveCardStatusDisplayed();
        cardInfoSteps.assertUserCardTypeIsDisplayed();
        cardInfoSteps.assertUserCardNumberIsDisplayed();
        cardInfoSteps.assertUserCardValidityPeriodIsDisplayed();
        cardInfoSteps.assertCopyCardNumberIsDisplayed();
        cardInfoSteps.assertCopyCvvCodeIsDisplayed();
    }

    @Test
    @TmsLink("LIB2-1015")
    @DisplayName("Отображение элементов на странице с информацией по карте пользователя")
    public void userCardOptionsTest() {
        cardInfoSteps.assertCardTransactionHistoryButtonIsDisplayed();
        cardInfoSteps.assertCardInformationBlocDisplayed();
        cardInfoSteps.goBack();
    }
}
