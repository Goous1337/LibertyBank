package api.depositService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.DEPOSIT_SERVICE;

@DisplayName("Отправка чека на электронную почту")
public class DM_9_11_CheckSendingEmailAfterDepositWithdrawal extends BaseTest {

    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }

    @DisplayName("Отправка чека на электронную почту отзыва депозита")
    @Description("Данный тест-кейс направлен на проверку корректного отправления чека на электронную почту после отзыва " +
            "депозита или отказа от пролонгации депозита.")
    @Tags({@Tag("API"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-1106")
    @Test
    public void checkSendingEmailAfterDepositWithdrawal() {
        String eMail = "test@mail.ru";
        Integer id = 1;
        assertEquals(SC_OK, depositService.checkSendingByEmail(eMail, id).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @Disabled("BUG")
    @DisplayName("Отправка чека на невалидную электронную почту после отзыва депозита")
    @Description("Данный тест-кейс направлен на проверку  отображения ошибки в случае отправления чека на невалидную " +
            "электронную почту после отзыва депозита или отказа от пролонгации депозита.")
    @Tags({@Tag("API"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-1107")
    @ParameterizedTest(name = "eMail: {0}, id: {1}")
    @CsvSource({
            "testmail.ru, 1",
            " , 1",
            "asasasd, 1",
            "1112223, 1"
    })
    public void checkSendingIvalidEmal(String eMail, Integer id) {
        assertEquals(SC_BAD_REQUEST, depositService.checkSendingByEmail(eMail, id).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }
}
