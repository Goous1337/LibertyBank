package api.deposit_service;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import model.response.Specifications;
import org.junit.jupiter.api.*;
import pojo.depositService.DepositData;
import pojo.depositService.DepositDataInvalidRequest;
import pojo.depositService.DepositDataResponse;

import static constant.ApiEndpoints.DEPOSIT_SETTINGS;
import static constant.DepositConstants.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;
import static property.BaseProperties.DEPOSIT_SERVICE;

@DisplayName("DM-9.2 Оформление нового депозита")
public class DM_9_2_MakeNewDeposit extends BaseTest {
    @DisplayName("Оформление нового депозита")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита" +
            " авторизованным пользователем.")
    @Tags({@Tag("API"), @Tag("smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-805")
    @Test
    public void checkMakeNewDeposit() {
        Specifications.installSpecification
                (Specifications.requestSpec(DEPOSIT_SERVICE), Specifications.responseSpec(STATUS_200));
        Integer depositId = 1;
        Double amount = 19000.0000;
        String months = "24";
        Boolean renewal = false;
        DepositData depositData = new DepositData
                (1, 19000.0000, "24", false);
        DepositDataResponse depositDataResponse =
                given().log().all()
                        .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                        .body(depositData)
                        .when().post(DEPOSIT_SETTINGS)
                        .then().log().all()
                        .extract().as(DepositDataResponse.class);
        Assertions.assertAll(
                () -> assertEquals(depositId, depositDataResponse.getDepositProductId()),
                () -> assertEquals(amount, depositDataResponse.getInitialAmount()),
                () -> assertEquals(months, depositDataResponse.getPeriodMonths()),
                () -> assertEquals(renewal, depositDataResponse.getAutoRenewal()));
    }

    @DisplayName("Оформление нового депозита в случае некорректной конфигурации запроса")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита" +
            "авторизованным пользователем в случае некорректной конфигурации запроса.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-812")
    @Test
    public void checkMakeNewDepositInvalidRequest() {
        Specifications.installSpecification
                (Specifications.requestSpec(DEPOSIT_SERVICE), Specifications.responseSpec(STATUS_400));
        DepositData depositData = new DepositData
                (1, 19000.0000, false);
        DepositDataInvalidRequest depositDataInvalidRequest =
                given().log().all()
                        .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                        .body(depositData)
                        .when().post(DEPOSIT_SETTINGS)
                        .then().log().all()
                        .extract().as(DepositDataInvalidRequest.class);

        Assertions.assertEquals(ERROR_REQUEST, depositDataInvalidRequest.getErrorMessage());

    }

    @DisplayName("Оформление нового депозита с невалидным токеном")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита" +
            "авторизованным пользователем в случае неуспешной валидации токена.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-811")
    @Test
    public void checkMakeNewDepositInvalidToken() {
        Specifications.installSpecification
                (Specifications.requestSpec(DEPOSIT_SERVICE), Specifications.responseSpec(STATUS_401));
        DepositData depositData = new DepositData(1, 19000.0000, "24", false);
        DepositDataInvalidRequest depositDataInvalidRequest =
                given().log().all()
                        .header("Authorization", INVALID_ACCESS_TOKEN)
                        .body(depositData)
                        .when()
                        .post(DEPOSIT_SETTINGS)
                        .then()
                        .log().all()
                        .extract().as(DepositDataInvalidRequest.class);
        Assertions.assertNotNull(depositDataInvalidRequest.getErrorMessage());

    }
}
