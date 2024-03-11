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
@Feature("US-5.4 Просмотр информации по карте пользователя")
@DisplayName("US-5.4 Просмотр информации по карте пользователя")
public class UserCardsInfoTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CARDS_URL);
        cardSteps.clickCardInfoForActiveCard();
    }

    @Test
    @Tag("Web")
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
    @Tag("Web")
    @TmsLink("LIB2-1015")
    @DisplayName("Отображение элементов на странице с информацией по карте пользователя")
    public void userCardOptionsTest() {
        cardInfoSteps.assertCardTransactionHistoryButtonIsDisplayed();
        cardInfoSteps.assertCardInformationBlocDisplayed();
        cardInfoSteps.goBack();
    }
}
