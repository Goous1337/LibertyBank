package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import model.response.Specifications;
import org.junit.jupiter.api.*;
import pojo.creditService.CreateApplyingLoanRequest;
import pojo.creditService.CreateApplyingLoanResponse;
import pojo.creditService.InvalidDataResponse;

import static constant.ApiEndpoints.CREDIT_BODY;
import static constant.DepositConstants.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;
import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("СМ 3.3 Оформление заявки на кредит")
public class CM_3_3_CheckApplyingLoanTest extends BaseTest {
    @DisplayName("Оформление заявки на кредит")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление" +
            " заявки на кредит авторизованным пользователем.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-606")
    @Test
    public void checkApplyingLoan() {
        int productId = 3;
        int amount = 2500000;
        int periodMonths = 20;
        CreateApplyingLoanRequest applyingRequest = new CreateApplyingLoanRequest
                (3, 2500000, 20, "RUB", "2023-09-04",
                        60000, 30000, "8698345212");
        Specifications.installSpecification
                (Specifications.requestSpec(CREDIT_SERVICE), Specifications.responseSpec(STATUS_200));
        CreateApplyingLoanResponse applyingResponse = given()
                .log().all()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .body(applyingRequest)
                .when()
                .post(CREDIT_BODY)
                .then()
                .log().all()
                .extract()
                .as(CreateApplyingLoanResponse.class);
        Assertions.assertAll(
                () -> assertEquals(productId, applyingResponse.getProductId()),
                () -> assertEquals(amount, applyingResponse.getAmount()),
                () -> assertEquals(periodMonths, applyingResponse.getPeriodMonths()));
    }

    @DisplayName("Оформление заявки на кредит с невалидным токеном")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки" +
            " на кредит авторизованным пользователем в случае неуспешной валидации токена.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-611")
    @Test
    public void checkApplyingLoanInvalidToken() {
        CreateApplyingLoanRequest applyingRequest = new CreateApplyingLoanRequest
                (3, 2500000, 20, "RUB", "2023-09-04",
                        60000, 30000, "8698345212");
        Specifications.installSpecification
                (Specifications.requestSpec(CREDIT_SERVICE), Specifications.responseSpec(STATUS_401));
        InvalidDataResponse invalidDataResponse = given()
                .log().all()
                .header("Authorization", INVALID_ACCESS_TOKEN)
                .body(applyingRequest)
                .when()
                .post(CREDIT_BODY)
                .then().
                log().all()
                .extract().as(InvalidDataResponse.class);
        Assertions.assertNotNull(invalidDataResponse.getErrorMessage());
    }

    @DisplayName("Оформление заявки на кредит в случае ошибки сервера")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 в случае ошибки сервера" +
            " при попытке оформления заявки на кредит авторизованным пользователем.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-610")
    @Test
    public void checkServerError() {
        CreateApplyingLoanRequest applyingRequest = new CreateApplyingLoanRequest
                (3, 2500000, 20, "RUB", "2023-09-04",
                        60000, 30000, "869834521222");
        Specifications.installSpecification
                (Specifications.requestSpec(CREDIT_SERVICE), Specifications.responseSpec(STATUS_500));
        InvalidDataResponse invalidDataResponse = given()
                .log().all()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .body(applyingRequest)
                .when()
                .post(CREDIT_BODY)
                .then().
                log().all()
                .extract().as(InvalidDataResponse.class);
        Assertions.assertNotNull(invalidDataResponse.getErrorMessage());
    }

    @DisplayName("Оформление заявки на кредит в случае, если в результирующей таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки на кредит" +
            " авторизованным пользователем в случае, если в результирующей таблице нет записей по указанным критериям.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-614")
    @Test
    public void checkNoRecordsMatchingCriteria() {
        CreateApplyingLoanRequest applyingRequest = new CreateApplyingLoanRequest
                (0, 2500000, 20, "RUB", "2023-09-04",
                        60000, 30000, "869834521222");
        Specifications.installSpecification
                (Specifications.requestSpec(CREDIT_SERVICE), Specifications.responseSpec(STATUS_404));
        InvalidDataResponse invalidDataResponse = given()
                .log().all()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .body(applyingRequest)
                .when()
                .post(CREDIT_BODY)
                .then().
                log().all()
                .extract().as(InvalidDataResponse.class);
        Assertions.assertNotNull(invalidDataResponse.getErrorMessage());
    }

    @DisplayName("Оформление заявки на кредит в случае некорректной конфигурации запроса")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки" +
            " на кредит авторизованным пользователем в случае некорректной конфигурации запроса.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-612")
    @Test
    public void checkApplyingLoanIncorrectRequestConfig() {
        CreateApplyingLoanRequest applyingRequest = new CreateApplyingLoanRequest
                (null, 2500000, 20, "RUB", "2023-09-04",
                        60000, 30000, "869834521222");
        Specifications.installSpecification
                (Specifications.requestSpec(CREDIT_SERVICE), Specifications.responseSpec(STATUS_400));
        InvalidDataResponse invalidDataResponse = given()
                .log().all()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .body(applyingRequest)
                .when()
                .post(CREDIT_BODY)
                .then().
                log().all()
                .extract().as(InvalidDataResponse.class);
        Assertions.assertNotNull(invalidDataResponse.getErrorMessage());
    }
}
