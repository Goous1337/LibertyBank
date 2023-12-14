package api.absInfoService;

import api.BaseTest;
import dataBase.requests.AbsInfoServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.absInfoService.AbsInfoServiceData;

import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.ABS_INFO_SERVICE;

@DisplayName("IS_2 Добавление нового банкомата")
public class IS_2_addingAtmTest extends BaseTest {

    {
        RestAssured.baseURI = ABS_INFO_SERVICE;
    }

    @DisplayName("[IS-2] [STATUS CODE 200] (POST) OK")
    @Description("Данный тест-кейс направлен на проверку [IS-2] [STATUS CODE 200] (POST) OK")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-945")
    @Test
    public void successfulAddingNewATM() {
        String atmNumber = "333489";
        AbsInfoServiceDataBaseRequest.deleteNewAtm(atmNumber);
        AbsInfoServiceData absInfoServiceData = new AbsInfoServiceData("Россия", "МО", "Москва", "Новая", "String", "String", "0", true,
                true, atmNumber, true, true, true, true, true, true, true, 0, 0, true);
        Response response = absInfoService.checkAddNewAtm(absInfoServiceData);

        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),

                () -> assertTrue(response.jsonPath().get("message").toString().startsWith("Банкомат c UUID ")&&
                                response.jsonPath().get("message").toString().endsWith(" добавлен"),
                        "Сообщение ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("[IS-2] [STATUS CODE 400] (POST) Добавление уже существующего филиала")
    @Description("Данный тест-кейс направлен на проверку [IS-2] [STATUS CODE 400] (POST) Добавление уже существующего филиала")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-946")
    @Test
    public void unsuccessfulAddingNewATM() {
        String atmNumber = "458998";
        AbsInfoServiceDataBaseRequest.deleteNewAtm(atmNumber);
        AbsInfoServiceData absInfoServiceData = new AbsInfoServiceData("Россия", "МО", "Москва", "Новая", "String", "String", "0", true,
                true, atmNumber, true, true, true, true, true, true, true, 0, 0, true);
        assertEquals(SC_OK, absInfoService.checkAddNewAtm(absInfoServiceData).statusCode(), "Код ответа не соответствует ожидаемому");
        assertEquals(SC_BAD_REQUEST, absInfoService.checkAddNewAtm(absInfoServiceData).statusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("[IS-2] [STATUS CODE 404] (POST) Не удалось найти запрашиваемый ресурс")
    @Description("Данный тест-кейс направлен на проверку [IS-2] [STATUS CODE 404] (POST) Не удалось найти запрашиваемый ресурс")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-948")
    @Test
    public void unsuccessfulAddingNewATMWithInvalidUrl() {
        Response response = absInfoService.checkInvalidURLAddNewAtm("Россия", "МО", "Москва", "Новая", "String", "String", "0", true,
                true, "458998", true, true, true, true, true, true, true, 0, 0, true);

        assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому");
    }
}
