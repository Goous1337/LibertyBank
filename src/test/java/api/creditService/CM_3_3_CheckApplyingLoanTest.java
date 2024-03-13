package api.creditService;

import api.BaseTest;
import dataBase.requests.CreditServiceDataBaseRequests;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pojo.creditService.CreditInfo;

import java.util.List;
import java.util.stream.Stream;

import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("СМ 3.3 Оформление заявки на кредит")
public class CM_3_3_CheckApplyingLoanTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    static Stream<Object> testData() {
        return Stream.of(
                Arguments.of("Liberty Наличными", 9000, 1000, "0123456789"),
                Arguments.of("Liberty Срочный", 90000, 10000, "0123456789"),
                Arguments.of("Liberty Money", 900000, 100000, "0123456789"),
                Arguments.of("Liberty Easy", 9000000, 1000000, "0123456789"),
                Arguments.of("Liberty Car", 90000000, 10000000, "0123456789")
        );
    }

    @DisplayName("Оформление заявки на кредит")
    @Description("""
            Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление
            заявки на кредит авторизованным пользователем.
            """)
    @Tags({@Tag("API"), @Tag("CM"), @Tag("Smoke")})
    @TmsLink("LIB3-606")
    @ParameterizedTest
    @MethodSource("testData")
    public void checkApplyingLoan(String creditName, int monthlyIncome, int monthlyExpenditure, String employerIdentificationNumber) {
        String jsonSchemaPath = "schemas/creditService/CM_3_3/checkApplyingLoan.json";
        CreditInfo creditInfo = CreditServiceDataBaseRequests.getCreditInfoByName(creditName);
        Response responseWithMinParams = creditService.checkListApplyingLoan
                (creditInfo.getId(), creditInfo.getMin_sum(), creditInfo.getMin_period_months(), monthlyIncome,
                        monthlyExpenditure, employerIdentificationNumber);
        Response responseWithMaxParams = creditService.checkListApplyingLoan(creditInfo.getId(), creditInfo.getMax_sum(),
                creditInfo.getMax_period_months(), monthlyIncome, monthlyExpenditure, employerIdentificationNumber);
        List<Integer> idList = CreditServiceDataBaseRequests.getCreditsOrders();
        int actualCreditOrderIdWithMinParams = responseWithMinParams.jsonPath().get("id");
        int actualCreditOrderIdWithMaxParams = responseWithMaxParams.jsonPath().get("id");
        assertAll(
                () -> assertEquals(SC_OK,
                        responseWithMinParams.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertTrue(idList.contains(actualCreditOrderIdWithMinParams)),
                () -> responseWithMinParams.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> assertEquals(SC_OK, responseWithMaxParams.getStatusCode()),
                () -> assertTrue(idList.contains(actualCreditOrderIdWithMaxParams)),
                () -> responseWithMaxParams.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CreditServiceDataBaseRequests.deleteCreditById(actualCreditOrderIdWithMinParams);
        CreditServiceDataBaseRequests.deleteCreditById(actualCreditOrderIdWithMaxParams);
    }

    @DisplayName("Оформление заявки на кредит с невалидным токеном")
    @Description("""
            Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки
            на кредит авторизованным пользователем в случае неуспешной валидации токена.
            """)
    @Tags({@Tag("API"), @Tag("CM"), @Tag("Negative")})
    @TmsLink("LIB3-611")
    @ParameterizedTest
    @MethodSource("testData")
    public void checkApplyingLoanInvalidToken(String creditName, int monthlyIncome, int monthlyExpenditure, String employerIdentificationNumber) {
        String jsonSchemaPath = "schemas/creditService/CM_3_3/errorMessage.json";
        CreditInfo creditInfo = CreditServiceDataBaseRequests.getCreditInfoByName(creditName);
        Response response = creditService.checkListApplyingLoanInvalidToken
                (creditInfo.getId(), creditInfo.getMin_sum(), creditInfo.getMin_period_months(), monthlyIncome,
                        monthlyExpenditure, employerIdentificationNumber);
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Оформление заявки на кредит в случае, если в результирующей таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки на кредит" +
            " авторизованным пользователем в случае, если в результирующей таблице нет записей по указанным критериям.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-614")
    @Test
    public void checkApplyingLoanServerNoRecordsMatchingCriteria() {
        String jsonSchemaPath = "schemas/creditService/CM_3_3/errorMessage.json";
        Response response = creditService.checkListApplyingLoan
                (0, 2500000, 20, 60000, 30000, "8698345212");
        Assertions.assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Оформление заявки на кредит в случае некорректной конфигурации запроса")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки" +
            " на кредит авторизованным пользователем в случае некорректной конфигурации запроса.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-612")
    @Test
    public void checkApplyingLoanInvalidConfig() {
        String jsonSchemaPath = "schemas/creditService/CM_3_3/errorMessage.json";
        Response response = creditService.checkListApplyingLoan
                (null, 2500000, 20,
                        60000, 30000, "8698345212");
        Assertions.assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}

