package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CREDIT_SERVICE;

@Tags({@Tag("API"), @Tag("3.0")})
@DisplayName("СM 3.7 Отзыв кредитной заявки")
public class CM_3_7_CheckWithdrawalOfLoanApplicationTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    Integer idValue;
    String idStatus;

    @DisplayName("Отзыв кредитной заявки")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки")
    @Tag("Smoke")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-764")
    @Test
    public void checkWithdrawalOfLoanApplication() {
        String jsonSchemaPath = "schemas/creditService/CM_3_7/checkWithdrawalOfLoanApplication.json";
        Response responseReg = creditService.checkListApplyingLoan
                (3, 25000, 20, 60000, 30000, "8698345212", "RUB");
        idValue = responseReg.jsonPath().get("id");
        idStatus = responseReg.jsonPath().get("status");
        Response responseWithdrawal = creditService.checkListWithdrawalOfLoanApplication(idValue, idStatus);
        assertAll(
                () -> assertEquals(SC_OK, responseWithdrawal.getStatusCode()),
                () -> responseWithdrawal.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отзыв кредитной заявки c несуществующим значением параметра creditOrderId")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки c несуществующим значением+" +
            " параметра creditOrderId")
    @Tag("Negative")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-767")
    @Test
    public void checkWithdrawalOfLoanApplicationNotExistParamOfId() {
        String jsonSchemaPath = "schemas/creditService/CM_3_7/notSuccessSchem.json";
        Response response = creditService.checkListWithdrawalOfLoanApplicationNotExistParamOfId();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отзыв кредитной заявки без токена аутентификации")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки без токена аутентификации")
    @Tag("Negative")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-769")
    @Test
    public void checkWithdrawalOfLoanApplicationEmptyToken() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response responseReg = creditService.checkListApplyingLoan
                (3, 25000, 20, 60000, 30000, "8698345212", "RUB");
        idValue = responseReg.jsonPath().get("id");
        idStatus = responseReg.jsonPath().get("status");
        Response responseWithdrawal = creditService.checkListWithdrawalOfLoanApplicationEmptyToken(idValue, idStatus);
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, responseWithdrawal.getStatusCode()),
                () -> responseWithdrawal.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отзыв кредитной заявки по одобренной заявке")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки по одобренной заявке")
    @Tag("Negative")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-772")
    @Test
    public void checkListWithdrawalOfLoanApplication() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response responseReg = creditService.checkListWithdrawalOfLoanApprovedApplication("APPROVED");
        idStatus = responseReg.jsonPath().get("status");
        Response response = creditService.checkListWithdrawalOfLoanApprovedApplication(idStatus);
        assertAll(
                () -> assertEquals(SC_CONFLICT, responseReg.getStatusCode()),
                () -> responseReg.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отзыв кредитной заявки")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки при ошибке сервера")
    @Tag("Smoke")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-2721")
    @Test
    public void checkWithdrawalOfLoanApplicationErrorServer() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response responseReg = creditService.checkListApplyingLoan
                (3, 25000, 20, 60000, 30000, "8698345212", "RUB");
        idValue = responseReg.jsonPath().get("id");
        idStatus = responseReg.jsonPath().get("status");
        Response responseWithdrawal = creditService.checkListWithdrawalOfLoanApplicationErrorServer(idValue, idStatus);
        assertAll(
                () -> assertEquals(SC_SERVER_ERROR, responseWithdrawal.getStatusCode()),
                () -> responseWithdrawal.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @AfterEach
    public void clearDataBase() {
        Response response = creditService.checkListWithdrawalOfLoanApplication(idValue, idStatus);
    }
}
