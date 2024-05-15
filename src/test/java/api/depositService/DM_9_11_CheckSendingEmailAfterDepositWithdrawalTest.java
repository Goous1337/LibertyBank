package api.depositService;

import api.BaseTest;
import dataBase.requests.DepositServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.DEPOSIT_SERVICE;

@Tags({@Tag("API"), @Tag("3.0")})
@DisplayName("DM-9.11 Отправка чека на электронную почту")
public class DM_9_11_CheckSendingEmailAfterDepositWithdrawalTest extends BaseTest {

    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }

    public void assertSendingByEmail(String email, int statusCode) {
        Integer id = null;
        try {
            id = DepositServiceDataBaseRequest.getFirstDepositId();
            DepositServiceDataBaseRequest.changeDepositStatus(id, false);
            assertEquals(statusCode, depositService.checkSendingByEmail(email, id).statusCode(),
                    RESPONSE_CODE_NOT_EXPECTED);
        } finally {
            if (id != null) {
                DepositServiceDataBaseRequest.changeDepositStatus(id, true);
            }
        }
    }

    @DisplayName("Отправка чека на электронную почту отзыва депозита")
    @Description("Данный тест-кейс направлен на проверку корректного отправления чека на электронную почту после отзыва " +
            "депозита или отказа от пролонгации депозита.")
    @Tags({@Tag("Smoke"), @Tag("Positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-1106")
    @Test
    public void checkSendingEmailAfterDepositWithdrawal() {
        assertSendingByEmail("test@mail.ru", SC_OK);
    }

    @DisplayName("Отправка чека на невалидную электронную почту после отзыва депозита")
    @Description("Данный тест-кейс направлен на проверку  отображения ошибки в случае отправления чека на невалидную " +
            "электронную почту после отзыва депозита или отказа от пролонгации депозита.")
    @Tags({@Tag("Smoke"), @Tag("Positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-1107")
    @ParameterizedTest()
    @EmptySource
    @ValueSource(strings = {"testmail.ru", "asasasd", "1112223"})
    public void checkSendingInvalidEmail(String email) {
        assertSendingByEmail(email, SC_BAD_REQUEST);
    }
}
