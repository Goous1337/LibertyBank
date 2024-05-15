package api.infoService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.apache.hc.core5.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.asynchttpclient.util.HttpConstants.Methods.GET;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.INFO_SERVICE;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("IS-5 Получение списка городов, где находятся отделения")
public class IS_5_CheckDefaultCityListTest extends BaseTest {

    {
        RestAssured.baseURI = INFO_SERVICE;
    }

    @DisplayName("Основной сценарий. Получение информации о списке дефолтных городов.")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о списке городов из БД")
    @Tag("Smoke")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1135")
    @Test

    public void successfulGettingCityList() {
        Response response = infoService.gettingCityList(GET);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertTrue(response.jsonPath().get("totalCount")
                                instanceof Number,
                        "Поле 'totalCount' не соответствует ожидаемому"),
                () -> assertTrue(response.jsonPath().get("items")
                                instanceof List,
                        "Поле 'items' не соответствует ожидаемому"),
                () -> {
                    List<Map<String, Object>> items = response.jsonPath().getList("items");
                    for (int i = 0; i < items.size(); i++) {
                        Map<String, Object> item = items.get(i);
                        assertTrue(item.get("cityId") instanceof Number, "Поле items[" + i + "].'cityId' не соответствует ожидаемому");
                        assertTrue(item.get("cityName") instanceof String, "Поле items[" + i + "].'cityName' не соответствует ожидаемому");
                        assertTrue(item.get("isDefault") instanceof Boolean, "Поле items[" + i + "].'default' не соответствует ожидаемому");
                    }
                },
                () -> {
                    int totalCount = response.jsonPath().getInt("totalCount");
                    List<Map<String, Object>> items = response.jsonPath().getList("items");
                    assertEquals(totalCount, items.size(), "Количество элементов items[] не равно значению поля 'totalCount'");
                }
        );
    }

    @DisplayName("Проверка работы сервиса получения списка городов, где находятся отделения при невалидном URL.")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о списке городов из БД, если использован невалидный URL")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1139")
    @Test

    public void unsuccessfulGettingCityLisInvalidUrl() {
        Response response = infoService.unsuccessfulGettingCityListInvalidUrl();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка получения информации о списке городов если запрос не GET/ HEAD / OPTIONS.")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о списке городов из БД, если использован невалидный метод запроса")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1138")
    @Issue("https://jira.astondevs.ru/browse/LIB-1206")
    @ParameterizedTest(name = "Http метод: {0}")
    @CsvSource({
            "PATCH",
            "POST",
            "PUT",
            "DELETE"
    })

    public void unsuccessfulGettingCityListInvalidHttpMethod(String invalidHttpMethod) {
        Response response = infoService.gettingCityList(invalidHttpMethod);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

}