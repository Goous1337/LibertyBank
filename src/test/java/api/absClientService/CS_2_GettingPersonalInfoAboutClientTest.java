package api.absClientService;

import api.BaseTest;
import dataBase.requests.AbsClientServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ABS_CLIENT_SERVICE;

public class CS_2_GettingPersonalInfoAboutClientTest extends BaseTest {
    {
        RestAssured.baseURI = ABS_CLIENT_SERVICE;
    }

    @DisplayName("[CS-2] [STATUS CODE 200] (GET) Успешное получение личных данных клиента")
    @Description("Получение личных данных клиента для просмотра сотрудником карточки клиента")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1118")
    @Test

    public void checkGettingPersonalInfoAboutClient() {
        String jsonSchemaPath = "schemas/clientService.CS_2/checkGettingPersonalInfoAboutClient.json";
        String customer_uuid = AbsClientServiceDataBaseRequest.getCustomerUuid();
        Response response = absClientService.checkGetPersonalInfoClients(customer_uuid);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[CS-2] [STATUS CODE 404] (GET) Клиент с данным uuid не найден")
    @Description("Получение личных данных клиента для просмотра сотрудником карточки клиента с несуществующим uuid")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1119")
    @Test

    public void checkGettingPersonalInfoAboutClientWithIncorrectUuid() {
        String jsonSchemaPath = "schemas/clientService.CS_2/checkGettingInfoAboutClientWithIncorrectUuid.json";
        Response response = absClientService.checkGetPersonalInfoClientsWithIncorrectUuid();
        assertAll(
                () -> assertEquals(HttpStatus.SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @Disabled("Ошибка в документации или баг")
    @DisplayName("[CS-2] [STATUS CODE 400] (GET) Невалидный формат uuid")
    @Description("Получение личных данных клиента для просмотра сотрудником карточки клиента с невалидным uuid")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1120")
    @Test

    public void checkGettingPersonalInfoAboutClientWithInvalidUuid() {
        String jsonSchemaPath = "schemas/clientService.CS_2/checkGettingPersonalInfoAboutClientWithInvalidUuid400.json";
        Response response = absClientService.checkGetPersonalInfoClientsWithInvalidUuid();
        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
