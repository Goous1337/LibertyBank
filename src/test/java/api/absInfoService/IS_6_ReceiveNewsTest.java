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


import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ABS_INFO_SERVICE;

@DisplayName("IS_6 Получение новостей")
public class IS_6_ReceiveNewsTest extends BaseTest {
    {
        RestAssured.baseURI = ABS_INFO_SERVICE;
    }

    @DisplayName("[IS-6] [STATUS CODE 200] (GET) OK")
    @Description("Данный тест-кейс направлен на проверку [IS-6] [STATUS CODE 200] (GET) OK")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-956")
    @Test
    public void successfulReceiveNews() {
        Response response = absInfoService.checkReceiveNews();
        String jsonSchemaPath = "schemas/absInfoService/successfulGetNews.json";
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[IS-6] [STATUS CODE 404] (GET) Не удалось обнаружить указанный URL")
    @Description("Данный тест-кейс направлен на проверку [IS-6] [STATUS CODE 404] (GET) Не удалось обнаружить указанный URL")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-957")
    @Test
    public void unsuccessfulReceiveNews() {
        assertEquals(SC_NOT_FOUND, absInfoService.checkReceiveNewWithInvalidEndpoint().statusCode(),
                "Код ответа не соответствует ожидаемому");
    }
}
