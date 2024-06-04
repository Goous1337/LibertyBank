package api.investmentService;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import pojo.investmentService.AuthResponse;
import pojo.investmentService.CustomerIdNotFoundResponse;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.ACCESS_TOKEN_INVALID_CUSTOMER_ID;
import static constant.InvestmentConstants.INVALID_ACCESS_TOKEN;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.*;
import static service.InvestmentService.postAuthRequest;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("INV-1.1 Авторизация пользователя")
public class INV_1_1_UserAuth {

    private static final String JSON = "/jsons/investmentJsons/authData.json";
    private static final String JSON_NOT_FOUND = "/jsons/investmentJsons/authDataInvalidCustomerId.json";
    private static final String JSON_UNAUTHORIZED = "/jsons/investmentJsons/authDataUnauthorized.json";

    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @Test
    @TmsLink("LIB6-1027")
    @DisplayName("Авторизация пользователя")
    @Description("Данный тест-кейс проверяет авторизацию пользователя")
    public void postAuthData() {
        AuthResponse actualData = postAuthRequest(ACCESS_TOKEN_INVESTMENT_SERVICE)
                .as(AuthResponse.class);
        AuthResponse expectedData = parseJson(AuthResponse.class, JSON);

        assertEquals(actualData, expectedData, "Данные пользователя не соотвествуют ожидаемым");

    }

    @Test
    @TmsLink("LIB6-1121")
    @DisplayName("Авторизация пользователя")
    @Description("Данный тест-кейс проверяет авторизацию пользователя с невалидным CustomerId")
    public void postAuthDataInvalidCustomerId() {
        CustomerIdNotFoundResponse actualData = postAuthRequest(ACCESS_TOKEN_INVALID_CUSTOMER_ID)
                .as(CustomerIdNotFoundResponse.class);
        CustomerIdNotFoundResponse expectedData = parseJson(CustomerIdNotFoundResponse.class, JSON_NOT_FOUND);
        assertAll(
                () -> assertEquals(actualData.getUri(), expectedData.getUri(), "Эндпоинт не соответствует ожидаемому"),
                () -> assertEquals(actualData.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(actualData.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));

    }

    @Test
    @TmsLink("LIB6-1038")
    @DisplayName("Авторизация пользователя")
    @Description("Данный тест-кейс проверяет авторизацию пользователя с невалидным Token")
    public void postAuthDataInvalidToken() {
        CustomerIdNotFoundResponse actualData = postAuthRequest(INVALID_ACCESS_TOKEN)
                .as(CustomerIdNotFoundResponse.class);
        CustomerIdNotFoundResponse expectedData = parseJson(CustomerIdNotFoundResponse.class, JSON_UNAUTHORIZED);
        assertAll(
                () -> assertEquals(actualData.getUri(), expectedData.getUri(), "Эндпоинт не соответствует ожидаемому"),
                () -> assertEquals(actualData.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(actualData.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));

    }

}

