package api.absInfoService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ABS_INFO_SERVICE;

@DisplayName("IS_2 Добавление нового банкомата")
public class IS_5_ClientAccountsTest extends BaseTest {

    {
        RestAssured.baseURI = ABS_INFO_SERVICE;
    }

    @DisplayName("[IS-5] [STATUS CODE 200] (GET)")
    @Description("Данный тест-кейс направлен на проверку [IS-5] [STATUS CODE 200] (GET)")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-961")
    @Test
    public void checkClientAccountsList() {
        String jsonSchemaPath = "schemas/absInfoService/IS_5/checkClientAccountsList.json";
        Response response = absInfoService.checkClientAccountsList();
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[IS-5] [STATUS CODE 404] (GET) Не удалось найти запрашиваемый ресурс")
    @Description("Данный тест-кейс направлен на проверку [IS-5] [STATUS CODE 404] (GET) Не удалось найти запрашиваемый ресурс")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-964")
    @Test
    public void unsuccessfulCheckClientAccountsList() {
        String jsonSchemaPath = "schemas/absInfoService/IS_5/errorMessage.json";
        Response response = absInfoService.checkClientAccountsListInvalidUrl();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
