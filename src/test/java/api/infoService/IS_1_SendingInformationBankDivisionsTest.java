package api.infoService;

import api.BaseTest;
import dataBase.requests.InfoServiceDataBaseRequests;
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
import static org.asynchttpclient.util.HttpConstants.Methods.GET;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.INFO_SERVICE;

@Tags({@Tag("API"), @Tag("MVP")})
@DisplayName("IS-1 Отправка информации о подразделениях Банка")
public class IS_1_SendingInformationBankDivisionsTest extends BaseTest {

    {
        RestAssured.baseURI = INFO_SERVICE;
    }

    @DisplayName("Проверка возможности получения информации о всех подразделениях банка, находящихся в списке")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о подразделениях банка из БД")
    @Tag("Smoke")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1147")
    @Test

    public void successfulGettingBankDivisionsList() {
        assertEquals(SC_OK, infoService.checkGettingAllBankDivisions(GET).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Получение информации обо всех подразделениях банка в городе")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о подразделениях банка в " +
            "конкретном городе из БД")
    @Tag("Smoke")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1155")
    @ParameterizedTest(name = "ID города: {0}")
    @ValueSource(
            strings = {"1", "5", "10"}
    )

    public void successfulGettingBankDivisionsCurrentCity(String cityId) {
        Response response = infoService.checkGettingBankDivisionsByCity(cityId);
        String expectedCityName = InfoServiceDataBaseRequests.getCityByCityId(Integer.parseInt(cityId));
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> {
                    List<String> items = response.jsonPath().getList("cityName");
                    items.forEach(actualCityName -> assertEquals(expectedCityName, actualCityName,
                            "Запрошенный город не соответствует представленному"));

                },
                () -> {
                    List<Map<String, Object>> items = response.jsonPath().getList("$");
                    for (Map<String, Object> item : items) {
                        assertTrue(item.get("branchAddress") instanceof String,
                                "Поле 'branchAddress' не соответствует ожидаемому формату");
                        assertTrue(item.get("hasCashWithdraw") instanceof Boolean,
                                "Поле 'hasCashWithdraw' не соответствует ожидаемому формату");
                        assertTrue(item.get("isWorkingNow") instanceof Boolean,
                                "Поле 'isWorkingNow' не соответствует ожидаемому формату");
                    }
                }
        );
    }

    @DisplayName("Получение страниц с подразделениями банка, с фильтром на количество элементов на странице")
    @Description("В данном тест-кейсе проводится проверка возможности получения страниц с отделениями с определенным " +
            "количеством элементов на странице")
    @Tag("Smoke")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1157")
    @ParameterizedTest(name = "Номер страницы: {0}, количество элементов на странице: {1}")
    @CsvSource({
            "0, 2",
            "0, 3"
    })

    public void successfulGettingBankDivisionsLimitedList(String pageNumb, String pageLimit) {
        Response response = infoService.checkGettingBankDivisionsLimitedList(pageNumb, pageLimit);

        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(Integer.parseInt(pageLimit), response.jsonPath().getList("$").size(),
                        "Количество элементов на странице не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка работы сервиса информации о подразделениях Банка при невалидном URL")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о списке подразделений " +
            "банка из БД, если использован невалидный URL")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1158")
    @Issue("https://jira.astondevs.ru/browse/LIB-1336")
    @ParameterizedTest(name = "URI: {0}")
    @ValueSource(
            strings = {"bunk_branch", "bank_branch?bank_bruch_city_id=10", "bank_branch?puge=0&size=3"}
    )

    public void unsuccessfulGettingBankDivisionsListInvalidURL(String url) {
        assertEquals(SC_NOT_FOUND,
                infoService.checkGettingBankDivisionsListInvalidURL(url).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Проверка работы сервиса получения информации о подразделениях Банка при использовании невалидных " +
            "входных параметров")
    @Description("В данном тест-кейсе проводится проверка возможности получения страниц с отделениями с определенным " +
            "количеством элементов на странице из БД, при использовании невалидных входных параметров")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1159")
    @Issue("https://jira.astondevs.ru/browse/LIB-1337")
    @ParameterizedTest(name = "Номер страницы: {0}, количество элементов на странице: {1}")
    @CsvSource({
            "-1, 10",
            "0, 0",
            "A, 10",
            "X, 0"
    })

    public void unsuccessfulGettingBankDivisionsInvalidData(String pageNumb, String pageLimit) {
        assertEquals(SC_BAD_REQUEST, infoService.checkGettingBankDivisionsLimitedList(pageNumb, pageLimit).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Проверка получения информации о списке подразделений банка если запрос не GET/ HEAD / OPTIONS")
    @Description("В данном тест-кейсе проводится проверка возможности получения информации о списке городов из БД, " +
            "если использован невалидный метод запроса")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1160")
    @ParameterizedTest(name = "Method: {0}")
    @ValueSource(
            strings = {"POST", "PATCH", "DELETE", "PUT"}
    )

    public void unsuccessfulGettingBankDivisionsInvalidMethod(String httpMethod) {
        assertEquals(SC_METHOD_NOT_ALLOWED, infoService.checkGettingAllBankDivisions(httpMethod).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

}