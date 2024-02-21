package api.depositService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.DEPOSIT_SERVICE;

@DisplayName("DM 9.1 Просмотр информации по депозитным продуктам банка")
public class DM_9_1_CheckInformationOnBankDepositProductsTest extends BaseTest {

    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }

    @DisplayName("Просмотр краткой информации по депозитным продуктам банка")
    @Description("Данный тест-кейс направлен на получение списка действующих депозитных продуктов банка в виде коллекции, " +
            "состоящей из Депозитных продуктов авторизованным пользователем")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-292")
    @Test

    public void checkInformationBankDepositProducts() {

        Response response = depositService.checkListCurrentDepositProducts();
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),

                () -> {
                    List<Map<String, Object>> items = response.jsonPath().getList("$");
                    for (Map<String, Object> item : items) {
                        assertEquals(item.keySet().toString(), "[id, name, maxInterestRate, productDetails, maxDurationMonths, amountMin, currencyCode]",
                                "Поля в ответе не соответствует ожидаемому");
                    }
                }
        );
    }

    @DisplayName("Просмотр информации по депозитам при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 401  при неуспешной валидации токена")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-295")
    @Test

    public void unsuccessfulInformationBankDepositProducts() {
        Response response = depositService.checkListCurrentDepositProductEmptyToken();
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(response.jsonPath().get("errorMessage"), "Unsuccessful token validation: Empty token",
                        "Сообщение в ответе не соответствует ожидаемому")
        );
    }

    @Disabled("Тест-кейс не актуальный")
    @DisplayName("Получение информации об отсутствии действующих депозитных продуктов")
    @Description("Данный тест-кейс направлен на получение информации об отсутствии действующих депозитных продуктов банка авторизованным пользователем ")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-296")
    @Test

    public void checkAbsenceInformationBankDepositProducts() {
        Response response = depositService.checkListCurrentDepositProducts();
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(response.jsonPath().get("errorMessage"), "Unsuccessful token validation: Empty token",
                        "Сообщение в ответе не соответствует ожидаемому")
        );
    }

    @Disabled("Тест-кейс не актуальный")
    @DisplayName("Получение информации о депозитах при отсутствии соединения с сервером")
    @Description("Данный тест-кейс направлен на получение 500 INTERNAL SERVER ERROR при проверке действующих депозитных продуктов банка авторизованным пользователем при неуспешном")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-300")
    @Test

    public void checkAbsenceInternetConnectionsInformationBankDepositProducts() {
        Response response = depositService.checkListCurrentDepositProducts();

        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(response.jsonPath().get("errorMessage"), "Unsuccessful token validation: Empty token",
                        "Сообщение в ответе не соответствует ожидаемому")
        );
    }

    @DisplayName("Получение информации о депозитах при неверной конфигурации запроса")
    @Description("Данный тест-кейс направлен на проверку возможности получения краткой информации о действующих депозитных " +
            "продуктах банка и получение STATUS CODE  404 авторизованным пользователем в личном кабинете при неверной конфигурации запроса")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-316")
    @Test

    public void checkInformationBankDepositProductsInvalidEndpoint() {
        Response response = depositService.checkListCurrentDepositProductsInvalidEndpoint();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(response.jsonPath().get("error"), "Not Found",
                        "Сообщение в ответе не соответствует ожидаемому")
        );
    }
}