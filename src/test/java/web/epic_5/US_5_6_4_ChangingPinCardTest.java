package web.epic_5;

import dataBase.requests.CardServiceDataBaseRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import web.BaseTest;
import web.helpers.ClipBoardHelper;
import web.helpers.FakerHelper;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static web.constans.UrlConfig.CARDS_URL;

@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("5-Карты")
@Feature("US-5.6.4 ALL Изменение ПИН-кода Web")
@DisplayName("US-5.6.4 ALL Изменение ПИН-кода Web")

public class US_5_6_4_ChangingPinCardTest extends BaseTest {
    String newPinCode = FakerHelper.generateFourDigitNumber();

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CARDS_URL);
    }

    @Test
    @TmsLink("LIB2-3422")
    @DisplayName("Изменение ПИН-кода")
    public void changingPin() {
        changePinSteps.clickInfoAboutCard();
        changePinSteps.clickCopyNumber();
        String firstTwelveNumbers = ClipBoardHelper.getDataFromClipBoard().substring(0, 12);
        String oldPin = CardServiceDataBaseRequest.getPINByFirstTwelveNumbers(firstTwelveNumbers);
        changePinSteps.clickChangePinCard();
        changePinSteps.setInputOldPinCard(oldPin);
        changePinSteps.setKeysToNewPinInput(newPinCode);
        changePinSteps.setApprovedNewPinCardInput(newPinCode);
        changePinSteps.clickConfirmButton();
        changePinSteps.assertSuccessAnswer();
    }

    @ParameterizedTest
    @MethodSource("invalidPins")
    @TmsLink("LIB2-3422")
    @DisplayName("Изменение ПИН-кода с недопустимым вводом данных")
    public void changingPin(String invalidPin) {
        changePinSteps.clickInfoAboutCard();
        changePinSteps.clickCopyNumber();
        String firstTwelveNumbers = ClipBoardHelper.getDataFromClipBoard().substring(0, 12);
        String oldPin = CardServiceDataBaseRequest.getPINByFirstTwelveNumbers(firstTwelveNumbers);
        changePinSteps.clickChangePinCard();
        changePinSteps.setInputOldPinCard(oldPin);
        changePinSteps.setKeysToNewPinInput(invalidPin);
        changePinSteps.setApprovedNewPinCardInput(invalidPin);
        changePinSteps.clickConfirmButton();
        changePinSteps.assertUnSuccessAnswer();
    }

    private static Stream<Arguments> invalidPins() {
        return Stream.of(
                arguments(FakerHelper.generateInvalidPin()),
                arguments(FakerHelper.generateRandomCharacter()),
                arguments(FakerHelper.generateRandomWord())
        );
    }
}
