package api.customerService_2_0;

import api.BaseTest;
import dataBase.requests.CustomerService2_0DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("Получение информации о пользователе")
public class CRS_8_GettingUserInformationTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Получение информации о пользователе в личном кабинете")
    @Description("Данный тест-кейс проверяет возможность получение информации о пользователе при просмотре общей " +
            "информации в основном меню и личном кабинете.")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2107")
    @Test

    public void successfulGettingUserInformation() {
        String actualCustomerID = CustomerService2_0DataBaseRequest.receivingCustomerId();
        String jsonSchemaPath = "schemas/customerService_2_0/CRS-8/checkInfoUser.json";
        Response response = customerService2_0.checkGettingUserInformation(actualCustomerID);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение информации о пользователе при передачи невалидных данных")
    @Description("Данный тест-кейс проверяет возможность получение информации о пользователе при использовании " +
            "невалидных данных.")
    @Tags({@Tag("API"), @Tag("negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2113")

    @ParameterizedTest(name = "customerId: {0}")
    @ValueSource(strings = {"12321", "gfaghs", ""})

    public void unsuccessfulGettingUserInformationWithInvalidCustomerId(String customerId) {

        Response response = customerService2_0.checkGettingUserInformation(customerId);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @DisplayName("Получение информации о пользователе при не валидном методе запроса.")
    @Description("Данный тест-кейс проверяет возможность получение информации о пользователе при использовании " +
            "не валидных методов: POST, PUT, PATCH")
    @Tags({@Tag("API"), @Tag("negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2114")

    @ParameterizedTest(name = "Method: {0}")
    @ValueSource(strings = {"POST", "PUT", "PATCH"})

    public void unsuccessfulGettingUserInformationWithInvalidMethod(String method) {

        String actualCustomerID = CustomerService2_0DataBaseRequest.receivingCustomerId();
        Response response = customerService2_0.checkGettingUserInformationWithInvalidMethod(method, actualCustomerID);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Метод не разрешен. Сервер знает о запрашиваемом методе, " +
                                "но он был деактивирован и не может быть использован.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }
}
