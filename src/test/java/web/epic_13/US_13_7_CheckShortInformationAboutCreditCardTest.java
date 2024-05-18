package web.epic_13;

import web.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;

@Tag("Web")
@Epic("13 - Кредитные карты")
@Feature("US-13.7 Просмотр краткой информации о кредитных картах банка ")
@DisplayName("US-13.7 Просмотр краткой информации о кредитных картах банка ")
public class US_13_7_CheckShortInformationAboutCreditCardTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        cardProductsSteps.clickAllCardsButton();
        cardProductsSteps.clickCardProductButton();
        cardProductsSteps.clickCardCreditButton();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр краткой информации о кредитных картах банка, проверка соответствия")
    @TmsLink("LIB3-2366")
    public void checkShortInfoAboutCreditCards() {
        cardProductsSteps.assertCardClassicIsDisplayed();
//      cardProductsSteps.assertFixHomeCardIsDisplayed();
        cardProductsSteps.assertPremiumCardLabelIsDisplayed();
        //cardProductsSteps.checkCreditCardBasket();
    }

}
