package api.infoService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.asynchttpclient.util.HttpConstants.Methods.GET;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.INFO_SERVICE;

@DisplayName("IS-2 Получение информации о курсах валют в отделениях")
public class IS_2_GettingInformationCurrencyExchangeRatesTest extends BaseTest {

    {
        RestAssured.baseURI = INFO_SERVICE;
    }

    @DisplayName("Основной сценарий. Получение информации обо всех подразделениях банка в городе.")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о подразделениях банка в " +
            "конкретном городе из БД")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1155")
    @Test

    public void gettingInformationCurrencyExchangeRates() {
        Response response = infoService.checkGettingInformationCurrencyExchangeRates(GET);
        assertAll(
                () -> assertEquals(SC_OK,
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
                        assertTrue(item.get("currencyExchange") instanceof Boolean, "Поле items[" + i + "].'currencyExchange' не соответствует ожидаемому");
                        assertTrue(item.get("cityName") instanceof String, "Поле items[" + i + "].'cityName' не соответствует ожидаемому");
                        assertTrue(item.get("branchId") instanceof String, "Поле items[" + i + "].'branchId' не соответствует ожидаемому");
                        assertTrue(item.get("branchNumber") instanceof String, "Поле items[" + i + "].'branchNumber' не соответствует ожидаемому");
                        assertTrue(item.get("branchAddress") instanceof String, "Поле items[" + i + "].'branchAddress' не соответствует ожидаемому");
                    }
                },
                () -> {
                    int totalCount = response.jsonPath().getInt("totalCount");
                    List<Map<String, Object>> items = response.jsonPath().getList("items");
                    assertEquals(totalCount, items.size(), "Количество элементов items[] не равно значению поля 'totalCount'");
                }
        );
    }

    @DisplayName("Основной сценарий. Получение информации о всех курсах валют отделений выбранного города.")
    @Description("В данном тест-кейсе проводится проверка возможности получение информации о курсах валют в отделениях" +
            " конкретного города из списка.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1236")
    @ParameterizedTest(name = "ID города: {0}")
    @ValueSource(
            strings = {"1", "5", "10"}
    )

    public void gettingInformationCurrencyExchangeRateCityId(String cityId) {
        Response response = infoService.checkGettingInformationCurrencyExchangeRatesCityId(cityId);
        assertAll(
                () -> assertEquals(SC_OK,
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
                        assertTrue(item.get("currencyExchange") instanceof Boolean, "Поле items[" + i + "].'currencyExchange' не соответствует ожидаемому");
                        assertTrue(item.get("cityName") instanceof String, "Поле items[" + i + "].'cityName' не соответствует ожидаемому");
                        assertTrue(item.get("branchId") instanceof String, "Поле items[" + i + "].'branchId' не соответствует ожидаемому");
                        assertTrue(item.get("branchNumber") instanceof String, "Поле items[" + i + "].'branchNumber' не соответствует ожидаемому");
                        assertTrue(item.get("branchAddress") instanceof String, "Поле items[" + i + "].'branchAddress' не соответствует ожидаемому");
                    }
                },
                () -> {
                    int totalCount = response.jsonPath().getInt("totalCount");
                    List<Map<String, Object>> items = response.jsonPath().getList("items");
                    assertEquals(totalCount, items.size(), "Количество элементов items[] не равно значению поля 'totalCount'");
                }
        );
    }

    @DisplayName("Основной сценарий. Получение страниц с курсами валют в отделениях выбранного города, с фильтром на " +
            "количество элементов на странице.")
    @Description("В данном тест-кейсе проводится проверка возможности получения страниц с информацией о курсах валют" +
            " отделений с определенным количеством элементов на странице.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1237")
    @ParameterizedTest(name = "Номер страницы: {0}, количество элементов на странице: {1}, размер порции: {2}")
    @CsvSource({
            "1, 0, 10",
            "6, 0, 10"
    })

    public void gettingInformationCurrencyExchangeRateCityIdPageSize(String cityId, String pageNumb, String size) {
        Response response = infoService.checkGettingInformationCurrencyExchangeRateCityIdPageSize(cityId, pageNumb, size);
        assertAll(
                () -> assertEquals(SC_OK,
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
                        assertTrue(item.get("currencyExchange") instanceof Boolean, "Поле items[" + i + "].'currencyExchange' не соответствует ожидаемому");
                        assertTrue(item.get("cityName") instanceof String, "Поле items[" + i + "].'cityName' не соответствует ожидаемому");
                        assertTrue(item.get("branchId") instanceof String, "Поле items[" + i + "].'branchId' не соответствует ожидаемому");
                        assertTrue(item.get("branchNumber") instanceof String, "Поле items[" + i + "].'branchNumber' не соответствует ожидаемому");
                        assertTrue(item.get("branchAddress") instanceof String, "Поле items[" + i + "].'branchAddress' не соответствует ожидаемому");
                    }
                },
                () -> {
                    int totalCount = response.jsonPath().getInt("totalCount");
                    List<Map<String, Object>> items = response.jsonPath().getList("items");
                    assertEquals(totalCount, items.size(), "Количество элементов items[] не равно значению поля 'totalCount'");
                }
        );
    }

    @DisplayName("Проверка работы сервиса информации о курсах валют в отделениях при невалидном URL.")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о курсах валют в отделениях" +
            " из БД, если использован невалидный URL")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1239")
    @Test

    public void gettingInformationCurrencyExchangeRatesInvalidUrl() {
        Response response = infoService.checkGettingInformationCurrencyExchangeRatesInvalidUrl();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка получения информации о курсах валют в отделениях если запрос не GET/ HEAD / OPTIONS.")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации информации о курсах валют " +
            "в отделениях из БД, если использован невалидный метод запроса.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1241")
    @ParameterizedTest(name = "Method: {0}")
    @ValueSource(
            strings = {"POST", "PATCH", "DELETE", "PUT"}
    )

    public void gettingInformationCurrencyExchangeRatesInvalidMethod(String httpMethod) {
        Response response = infoService.checkGettingInformationCurrencyExchangeRates(httpMethod);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка работы сервиса информации о курсах валют в отделениях при использовании невалидных входных параметров.")
    @Description("В данном тест-кейсе проводится проверка возможности получения страниц с информацией о курсах валют " +
            "отделений с определенным количеством элементов на странице, при использовании невалидных входных параметров.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1240")
    @Issue("https://jira.astondevs.ru/browse/LIB-1440")
    @ParameterizedTest(name = "Номер страницы: {0}, количество элементов на странице: {1}, размер порции: {2}")
    @CsvSource({
            "0, -1, 0",
            "0, 0, &",
            "0, a, -1",
            "0, &, a",
            "0, 1, 0",
            "1, 1, -1",
            "1, a, a",
            "1, &, 0",
            "1, 1, 0",
            "1, -1, &",
            "a, &, 0",
            "a, 1, &",
            "a, -1, -1",
            "a, 0, a",
            "&, &, &",
            "&, 1, -1",
            "&, -1, a",
            "&, 0, 0",
            "&, a, 0",
            "-1, 1, a",
            "-1, -1, 0",
            "-1, 0, 0",
            "-1, a, &",
            "-1, &, -1"
    })

    public void gettingInformationCurrencyExchangeRateCityIdPageSizeInvalid(String cityId, String pageNumb, String size) {
        Response response = infoService.checkGettingInformationCurrencyExchangeRateCityIdPageSize(cityId, pageNumb, size);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }
}
