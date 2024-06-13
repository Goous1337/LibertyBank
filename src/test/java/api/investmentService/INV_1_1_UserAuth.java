package api.investmentService;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pojo.investmentService.AuthResponse;
import pojo.investmentService.CustomerIdNotFoundResponse;

import java.util.stream.Stream;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.*;
import static service.InvestmentService.postAuthRequest;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("INV-1.1 Авторизация пользователя")
public class INV_1_1_UserAuth {

    private static final String JSON_NOT_FOUND = "/jsons/investmentJsons/authDataInvalidCustomerId.json";
    private static final String JSON_UNAUTHORIZED = "/jsons/investmentJsons/authDataUnauthorized.json";

    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @ParameterizedTest
    @MethodSource("authDataProvider")
    @TmsLink("LIB6-1027")
    @DisplayName("Авторизация пользователя")
    @Description("Данный тест-кейс позволяет проверить переход клиента банка в сервис инвестиций")
    public void postAuthData(String accessToken, boolean expectedStatus) {
        AuthResponse actualData = postAuthRequest(accessToken).as(AuthResponse.class);
        assertEquals(expectedStatus, actualData.isUserHaveActiveBrokerAccount());
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

    static Stream<Arguments> authDataProvider() {
        return Stream.of(
                Arguments.of(ACCESS_TOKEN_INVESTMENT_SERVICE, true),
                Arguments.of(ACCESS_TOKEN_WITHOUT_ACCOUNT, false)
        );
    }
}