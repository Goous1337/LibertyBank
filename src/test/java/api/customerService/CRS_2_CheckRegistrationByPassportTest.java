package api.customerService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static dataProviders.DataUtils.getPassportWithCustomerStatus;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE;

@DisplayName("CRS-2 Проверка регистрации по документу, удостоверяющему личность")
public class CRS_2_CheckRegistrationByPassportTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }

    @DisplayName("Проверка регистрации по документу, если клиент не зарегистрирован в СДБО")
    @Description("В данном кейсе производится проверка получения номера телефона, при вводе валидного номера " +
            "документа клиента банка, не зарегистрированного в СДБО")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-247")
    @ParameterizedTest(name = "Номер серии: {0} ,Номер паспорта: {1}, clientId: {2}, номер телефона: {3}")
    @CsvSource({
            "1515, 457457, e670aafa-adfd-49e6-bdbf-b65d9e0c7d53, 79031553942"
    })

    public void checkRegistrationByPassportRegisteredClient(String passportSeries,String passportNumber, String clientId, String phoneNumber) {
        String jsonSchemaPath = "schemas/customerService/CRS_2/checkRegistrationByPassportRegisteredClient.json";
        Response response = customerService.checkRegistrationByPassport(passportSeries,passportNumber);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка регистрации при вводе невалидного номера документа,удостоверяющий личность клиента")
    @Description("В данном кейсе производится проверка получения номера телефона, при вводе невалидного номера " +
            "документа клиента банка, не зарегистрированного в СДБО")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-261")
    @ParameterizedTest(name = "Серия паспорта:{0}, Номер паспорта: {1}")
    @CsvSource({
            "151, 457457",
            "15156, 457457",
            "151ъ, 457457",
            "151q, 457457",
            "151@, ",
            "' 151', 457457",
            "'151 ', 457457"
    })

    public void checkRegistrationByPassportInvalidSeries(String passportSeries,String passportNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPassport(passportSeries, passportNumber);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка регистрации по документу, удостоверяющего личность, когда пользователь -не клиент банка")
    @Description("В данном кейсе производится проверка получения номера телефона, при вводе валидного номера " +
            "документа,удостоверяющего личность пользователя,  когда пользователь - не клиент  банка")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-274")
    @Issue("https://jira.astondevs.ru/browse/LIB-1356")
    @ParameterizedTest(name = "Серия: {0} ,Номер паспорта: {1}")
    @CsvSource({
            "9999, 897777"
    })

    public void checkRegistrationByPassportNotAClient(String passportSeries,String passportNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPassport(passportSeries,passportNumber);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
    @DisplayName("Проверка регистрации, если пользователь уже зарегистрирован в СДБО")
    @Description("Тест на проверку регистрации, если пользователь уже зарегистрирован в СДБО")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-280")
    @Test

    public void checkRegistrationByPassportAlreadyRegisteredUser() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        HashMap<String,String> passport = getPassportWithCustomerStatus(2);
        String passportSeries = passport.get("series");
        String passportNumber = passport.get("number");
        Response response = customerService.checkRegistrationByPassport(passportSeries,passportNumber);
        assertAll(
                () -> assertEquals(SC_CONFLICT, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка регистрации, если пользователь пользователь заблокирован")
    @Description("В данном кейсе производится проверка регистрации, при вводе валидного номера документа клиента " +
            "банка, пользователь заблокирован в СДБО")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-286")
    @ParameterizedTest(name = "Серия: {0}, Номер паспорта: {1}")
    @CsvSource({
            "1244, 125155",
            "5151, 346363"
    })

    public void checkRegistrationByPhoneBlockedUser(String passportSeries,String passportNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPassport(passportSeries,passportNumber);
        assertAll(
                () -> assertEquals(SC_FORBIDDEN, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка регистрации, когда метод не GET")
    @Description("Проверка получения от системы сообщения об ошибке  с кодом  405 ,при использовании метода запроса " +
            "отличного от GET ")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-306")
    @ParameterizedTest(name = "Метод: {0}, номер паспорта: {1}")
    @CsvSource({
            "POST, 1234, 567890",
            "PUT, 1234, 567890",
            "PATCH, 1234, 567890",
            "DELETE, 1234, 567890"
    })

    public void checkRegistrationByPhoneInvalidMethod(String invalidHttpMethod, String passportSeries, String passportNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPassportInvalidHttpMethod(invalidHttpMethod, passportSeries, passportNumber);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }


    @DisplayName("Валидация номера паспорта")
    @Description("В данном кейсе производится проверка валидации номера паспорта.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1355")
    @ParameterizedTest(name = "Серия: {0}, номер паспорта: {1}")
    @CsvSource({
            "1515, 45745",
            "1515, 4574578",
            "1515, 457457ъ",
            "1515, 457457q",
            "1515, @457457",
            "1515, ' 457457'",
            "1515, '457457 '"
    })

    public void checkRegistrationByPassportInvalidNumber(String passportSeries,String passportNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPassport(passportSeries, passportNumber);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }


}