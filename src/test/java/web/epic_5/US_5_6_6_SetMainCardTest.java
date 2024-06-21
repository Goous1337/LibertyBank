package web.epic_5;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.CardServiceConstants.CONFIRMATION_CODE;
import static web.constans.UrlConfig.CARDS_URL;

@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("5 - Карты")
@Feature("US-5.6.6 Сделать карту основной")
@DisplayName("US-5.6.6 Сделать карту основной")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class US_5_6_6_SetMainCardTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CARDS_URL);
    }

    @Test
    @Order(1)
    @TmsLink("LIB2-1473")
    @DisplayName("Сделать карту основной")
    public void setMainCard() {
        cardSteps.clickCardInfoForSecondCard();
        cardInfoSteps.setMainCard();
        cardInfoSteps.assertMainCardStatusDisplayed();
        cardInfoSteps.goBack();
        cardSteps.assertMainStatusDisplayed();
    }

    @Test
    @Order(2)
    @TmsLink("LIB2-1473")
    @DisplayName("Убрать статус 'Основная карта', если карта уже является основной")
    public void setNotMainCardWhenCardIsMain() {
        cardSteps.clickCardInfoForActiveCard();
        cardInfoSteps.assertMainCardStatusDisplayed();
        cardInfoSteps.setMainCard();
        cardInfoSteps.assertMainCardStatusNotDisplayed();
        cardInfoSteps.goBack();
        cardSteps.assertMainStatusNotDisplayed();
    }

    @Test
    @Order(3)
    @TmsLink("LIB2-1476")
    @DisplayName("Отсутствие статуса 'Основная карта' после блокирования карты")
    public void mainCardStatusNotDisplayedAfterBlocked() {
        cardSteps.clickCardInfoForSecondCard();
        cardInfoSteps.setMainCard();
        cardInfoSteps.assertMainCardStatusDisplayed();
        cardInfoSteps.blockCard();
        confirmationSteps.assertBlockCardDialogBoxDisplayed();
        confirmationSteps.accept();
        cardInfoSteps.assertMainCardStatusNotDisplayed();
    }

    @Test
    @Order(4)
    @TmsLink("LIB2-1474")
    @DisplayName("Отсутствие возможности сделать карту основной, если карта заблокирована")
    public void setMainCardNotDisplayedWhenCardIsBlocked() {
        cardSteps.clickCardInfoForBlockedCard();
        cardInfoSteps.assertSetMainCardIsNotDisplayed();
    }


    @Test
    @Order(5)
    @TmsLink("LIB2-1476")
    @DisplayName("Отсутствие статуса 'Основная карта' после закрытия карты")
    public void mainCardStatusNotDisplayedAfterClosed() {
        cardSteps.clickCardInfoForSecondCard();
        cardInfoSteps.setMainCard();
        cardInfoSteps.assertMainCardStatusDisplayed();
        cardInfoSteps.closeCard();
        confirmationSteps.assertCloseCardDialogBoxDisplayed();
        confirmationSteps.accept();
        confirmationSteps.assertEnterCodeFromSmsBoxDisplayed();
        confirmationSteps.setConfirmationCode(CONFIRMATION_CODE);
        confirmationSteps.confirm();
        cardInfoSteps.assertMainCardStatusNotDisplayed();
    }

    @Test
    @Order(6)
    @TmsLink("LIB2-1474")
    @DisplayName("Отсутствие возможности сделать карту основной, если карта закрыта")
    public void setMainCardNotDisplayedWhenCardIsClosed() {
        cardSteps.clickCardInfoForClosedCard();
        cardInfoSteps.assertSetMainCardIsNotDisplayed();
    }
}
