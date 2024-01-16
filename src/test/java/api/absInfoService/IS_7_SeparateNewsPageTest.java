package api.absInfoService;

import api.BaseTest;
import dataBase.requests.AbsInfoServiceDataBaseRequest;
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

import static constant.AbsInfoServiceConstants.UN_EXIST_UUID_ABS;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ABS_INFO_SERVICE;

@DisplayName("ABS IS-7 Отдельная страница новости")
public class IS_7_SeparateNewsPageTest extends BaseTest {
    {
        RestAssured.baseURI = ABS_INFO_SERVICE;
    }

    @DisplayName("[IS-7] [STATUS CODE 200] (GET) OK")
    @Description("Данный тест-кейс направлен на проверку получения информации о новостях")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-971")
    @ParameterizedTest()
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    public void checkingWhetherInformationIsReceivedFromNewsPage(int id) {
        String jsonSchemaPath = "schemas/absInfoService/successfulGetNewsByUuid.json";
        String uuid = AbsInfoServiceDataBaseRequest.getBankNewsUuid(id);
        Response response = absInfoService.checkListWhetherInformationIsReceivedFromNewsPage(uuid);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[IS-7] [STATUS CODE 404] (GET) Не удалось найти новость")
    @Description("Данный тест-кейс направлен на проверку получения информации о новостях при невалидном uuid")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-972")
    @Test
    public void checkingWhetherInformationIsReceivedFromNewsPageInvalidUuid() {
        String jsonSchemaPath = "schemas/absInfoService/unsuccessfulGetNewsByUuid.json";
        Response response = absInfoService.checkListWhetherInformationIsReceivedFromNewsPage(UN_EXIST_UUID_ABS);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[IS-7] [STATUS CODE 404] (GET) Ошибка в URL'е")
    @Description("Данный тест-кейс направлен на проверку получения информации о новостях при невалидном url")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-973")
    @ParameterizedTest()
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    public void checkingWhetherInformationIsReceivedFromNewsPageInvalidUrl(int id) {
        String jsonSchemaPath = "schemas/StatusCode404InfoService.json";
        String uuid = AbsInfoServiceDataBaseRequest.getBankNewsUuid(id);
        Response response = absInfoService.checkListWhetherInformationIsReceivedFromNewsPageInvalidUrl(uuid);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
