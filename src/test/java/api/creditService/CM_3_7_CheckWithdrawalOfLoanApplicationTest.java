package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static constant.CreditServiceConstants.STATUS_WITHDRAWN;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("СM 3.7 Отзыв кредитной заявки")
public class CM_3_7_CheckWithdrawalOfLoanApplication extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Отзыв кредитной заявки")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-764")
    @Test
    public void checkWithdrawalOfLoanApplication() {
        Response responseReg = creditService.checkListApplyingLoan
                (3, 2500000, 20, "RUB", "2023-11-28"
                        , 60000, 30000, "8698345212");
        Integer idValue = responseReg.jsonPath().getInt("id");

        Response responseWithdrawal = creditService.checkListWithdrawalOfLoanApplication(idValue);
        assertAll(
                () -> assertEquals(SC_OK, responseWithdrawal.getStatusCode()),
                () -> assertEquals(idValue, responseWithdrawal.jsonPath().get("creditOrderId")),
                () -> assertEquals(STATUS_WITHDRAWN, responseWithdrawal.jsonPath().get("status"))
        );
    }

    @DisplayName("Отзыв кредитной заявки c несуществующим значением параметра creditOrderId")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки c несуществующим значением+" +
            " параметра creditOrderId")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-767")
    @Test
    public void checkWithdrawalOfLoanApplicationNotExistParamOfId() {
        Response response = creditService.checkListWithdrawalOfLoanApplicationNotExistParamOfId();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Отзыв кредитной заявки без токена аутентификации")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки без токена аутентификации")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-769")
    @Test
    public void checkWithdrawalOfLoanApplicationEmptyToken() {
        Response response = creditService.checkListWithdrawalOfLoanApplicationEmptyToken();
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Отзыв кредитной заявки по отклоненной заявке")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки по отклоненной заявке")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-771")
    @Test
    public void checkWithdrawalOfLoanApplicationOnAnAlreadyWithdrawnApplication() {
        Response response = creditService.checkListWithdrawalOfLoanApplication(1);
        assertAll(
                () -> assertEquals(SC_CONFLICT, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB-1882")
    @DisplayName("Отзыв кредитной заявки по одобренной заявке")
    @Description("Данный тест-кейс направлен на отзыв кредитной заявки по одобренной заявке")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-772")
    @Test
    public void checkWithdrawalOfLoanApplicationOnAnAlreadyApprovedApplication() {
        Response response = creditService.checkListWithdrawalOfLoanApplication(9);
        assertAll(
                () -> assertEquals(SC_CONFLICT, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }
}
