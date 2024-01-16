package api.absClientService;

import api.BaseTest;
import dataBase.requests.AbsClientServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.absClientService.AbsClientServiceShortInfoData;

import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ABS_CLIENT_SERVICE;

@DisplayName("ABS CS 5 Получение краткой информации о клиенте")
public class CS_5_ObtainingBriefInformationAboutClientTest extends BaseTest {
    {
        RestAssured.baseURI = ABS_CLIENT_SERVICE;
    }

    @DisplayName("[CS-5] [STATUS CODE 200] (GET) Успешное получение данных клиента (по паспорту)")
    @Description("Данный тест-кейс направлен на проверку получения данных клиента (по паспорту)")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1018")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 8})
    public void checkObtainingClientDataUsingPassport(int id) {
        AbsClientServiceShortInfoData absClientServiceShortInfoData = new AbsClientServiceShortInfoData();
        String passportSeries = AbsClientServiceDataBaseRequest.getClientPassportSeries(id);
        String passportNumber = AbsClientServiceDataBaseRequest.getClientPassportNumber(id);
        absClientServiceShortInfoData = AbsClientServiceDataBaseRequest.getClientData(id);
        Response response = absClientService.checkListObtainingClientDataUsingPassport(passportSeries, passportNumber);
        AbsClientServiceShortInfoData finalAbsClientServiceShortInfoData = absClientServiceShortInfoData;
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertEquals(finalAbsClientServiceShortInfoData,
                        response.then().extract().body().as(AbsClientServiceShortInfoData.class)
                ));
    }

    @DisplayName("[CS-5] [STATUS CODE 200] (GET) Успешное получение данных клиента (по UUID)")
    @Description("Данный тест-кейс направлен на проверку получения данных клиента (по UUID)")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1019")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 8})
    public void checkObtainingClientDataUsingUuid(int id) {
        AbsClientServiceShortInfoData absClientServiceShortInfoData = new AbsClientServiceShortInfoData();
        String clientUuid = AbsClientServiceDataBaseRequest.getClientUuid(id);
        absClientServiceShortInfoData = AbsClientServiceDataBaseRequest.getClientData(id);
        Response response = absClientService.checkListObtainingClientDataUsingUuid(clientUuid);
        AbsClientServiceShortInfoData finalAbsClientServiceShortInfoData = absClientServiceShortInfoData;
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertEquals(finalAbsClientServiceShortInfoData,
                        response.then().extract().body().as(AbsClientServiceShortInfoData.class))
        );
    }

    @DisplayName("[CS-5] [STATUS CODE 404] (GET) Клиент не найден (по паспорту)")
    @Description("""
            Данный тест-кейс направлен на проверку получения данных клиента с невалидным значением
            series и/или number
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1020")
    @ParameterizedTest()
    @CsvSource({"5555,123123", "4444,aqwerv", "saps,aq1209"})
    public void checkObtainingClientDataUsingInvalidPassportValue(String series, String number) {
        String jsonSchemaPath = "schemas/absInfoService/unsuccessfulGetNewsByUuid.json";
        Response response = absClientService.checkListObtainingClientDataUsingPassport(series, number);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[CS-5] [STATUS CODE 400] (GET) Невалидный формат серии и/или номера паспорта")
    @Description("""
            Данный тест-кейс направлен на проверку получения данных клиента с невалидный
            формат серии и/или номера паспорта
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1033")
    @ParameterizedTest()
    @CsvSource({"55555,1231235", "444aq,124aqwev", "qwsxdfs,aolfncyGsx"})
    public void checkObtainingClientDataUsingInvalidPassportFormat(String series, String number) {
        String jsonSchemaPath = "schemas/absInfoService/unsuccessfulGetNewsByUuid.json";
        Response response = absClientService.checkListObtainingClientDataUsingPassport(series, number);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[CS-5] [STATUS CODE 404] (GET) Клиент не найден (по UUID)")
    @Description("Данный тест-кейс направлен на проверку получения данных клиента с невалидным значением uuid")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1021")
    @ParameterizedTest()
    @CsvSource({"f3d6bf24-48c6-43aa-a4d2-050fc8e54cf3", "44444444-4444-4333-4422-050888866333",
            "ffddbfff-cccc-aaaa-aadd-ffffceecccff"})
    public void checkObtainingClientDataUsingInvalidUuid(String uuid) {
        String jsonSchemaPath = "schemas/absInfoService/unsuccessfulGetNewsByUuid.json";
        Response response = absClientService.checkListObtainingClientDataUsingUuid(uuid);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB4-1347")
    @DisplayName("[CS-5] [STATUS CODE 400] (GET) Запрос по трём параметрам")
    @Description("Данный тест-кейс направлен на проверку получения данных клиента с запросом по трем параметрам")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1023")
    @Issue("https://jira.astondevs.ru/browse/LIB4-1347")
    @ParameterizedTest()
    @ValueSource(ints = {1, 3, 8})
    public void checkObtainingClientDataUsingPassportAndUuid(int id) {
        String jsonSchemaPath = "schemas/absInfoService/unsuccessfulGetNewsByUuid.json";
        String uuid = AbsClientServiceDataBaseRequest.getClientUuid(id);
        String series = AbsClientServiceDataBaseRequest.getClientPassportSeries(id);
        String number = AbsClientServiceDataBaseRequest.getClientPassportNumber(id);
        Response response = absClientService.checkListObtainingClientDataUsingPassportAndUuid(uuid, series, number);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[CS-5] [STATUS CODE 400] (GET) Запрос по серии или номеру паспорта")
    @Description("""
            Данный тест-кейс направлен на проверку получения данных клиента в запросе оставив одно из
            полей series или number пустым.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1024")
    @ParameterizedTest()
    @ValueSource(ints = {1, 3, 8})
    public void checkObtainingClientDataUsingEmptyPassportValues(int id) {
        String jsonSchemaPath = "schemas/absInfoService/unsuccessfulGetNewsByUuid.json";
        String series = AbsClientServiceDataBaseRequest.getClientPassportSeries(id);
        String number = AbsClientServiceDataBaseRequest.getClientPassportNumber(id);
        Response responseEmptySeries = absClientService.checkListObtainingClientDataUsingPassport(null, number);
        Response responseEmptyNumber = absClientService.checkListObtainingClientDataUsingPassport(series, null);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, responseEmptySeries.getStatusCode()),
                () -> assertEquals(SC_BAD_REQUEST, responseEmptyNumber.getStatusCode()),
                () -> responseEmptySeries
                        .then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> responseEmptyNumber
                        .then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
