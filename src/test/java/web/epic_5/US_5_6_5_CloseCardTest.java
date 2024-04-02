package web.epic_5;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.CardServiceConstants.CONFIRMATION_CODE;
import static web.constans.CardServiceConstants.INVALID_CONFIRMATION_CODE;
import static web.constans.UrlConfig.CARDS_URL;

@Tag("Web")
@Epic("5 - Карты")
@Feature("US-5.6.5 Закрытие карты")
@DisplayName("US-5.6.5 Закрытие карты")
public class US_5_6_5_CloseCardTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CARDS_URL);
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-2943")
    @DisplayName("Закрытие карты")
    public void checkClosedCard() {
        cardSteps.clickCardInfoForActiveCard();
        cardInfoSteps.closeCard();
        confirmationSteps.assertCloseCardDialogBoxDisplayed();
        confirmationSteps.accept();
        closeCardSteps.enterCode(CONFIRMATION_CODE);
        closeCardSteps.assertButtonConfirmActive();
        closeCardSteps.clickConfirmCloseCardButton();
        cardInfoSteps.assertCardStatusClosed();
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-2943")
    @DisplayName("Отмена действия 'Закрыть карту'")
    public void cancellationActionClosedCard() {
        cardSteps.clickCardInfoForActiveCard();
        cardInfoSteps.closeCard();
        confirmationSteps.assertCloseCardDialogBoxDisplayed();
        confirmationSteps.deny();
        cardInfoSteps.assertStatusCardActiveDisplayed();
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-2943")
    @Disabled("Отключен пока не исправят баг. Не выводиться сообщение о ошибке. Можно ввести не валидные значения")
    @DisplayName("Отображение сообщения об ошибке при вводе невалидных значений")
    public void checkEnterInvalidValuesCard() {
        cardSteps.clickCardInfoForActiveCard();
        cardInfoSteps.closeCard();
        confirmationSteps.assertCloseCardDialogBoxDisplayed();
        confirmationSteps.accept();
        closeCardSteps.enterCode(INVALID_CONFIRMATION_CODE);
    }
}
