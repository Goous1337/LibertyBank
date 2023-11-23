package api.customerService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        Response response = customerService.checkRegistrationByPassport(passportSeries,passportNumber);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(phoneNumber,
                        response.body().jsonPath().get("mobilePhone"),
                        "Номер телефона в ответе не соответствует ожидаемому"),
                () -> assertEquals(clientId,
                        response.body().jsonPath().get("id"),
                        "ID клиента в ответе не соответствует ожидаемому")
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

    public void checkRegistrationByPassportInvalidPassport(String passportSeries,String passportNumber) {
        Response response = customerService.checkRegistrationByPassport(passportSeries, passportNumber);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Пользователь не зарегистрирован в Приложении и не является Клиентом Банка",
                        response.body().jsonPath().get("message"), "Сообщение об ошибке не соответствует ожидаемому")
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
        Response response = customerService.checkRegistrationByPassport(passportSeries,passportNumber);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("400 BAD_REQUEST",
                        response.body().jsonPath().get("type"), "Сообщение об ошибке не соответствует ожидаемому")
        );
    }
    /* [НА УДАЛЕНИЕ]

    @DisplayName("Проверка регистрации при вводе номера документа, содержащий строчные латинские буквы")
    @Description("В данном кейсе проверяем получение номера телефона, при вводе  номера документа ,удостоверяющего" +
            " личность пользователя,  когда пользователь - клиент  банка.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-276")
    @Issue("https://jira.astondevs.ru/browse/LIB-1198")
    @Test

    public void checkRegistrationByPassportLowercaseLatinLetters() {
        String passportNumber = "bm8765432";

        assertEquals(SC_OK,
                customerService.checkRegistrationByPassport(passportNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }
*/
    @DisplayName("Проверка регистрации, если пользователь уже зарегистрирован в СДБО")
    @Description("Тест на проверку регистрации, если пользователь уже зарегистрирован в СДБО")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-280")
    @ParameterizedTest(name = "Серия:{0}, Номер паспорта: {1}")
    @CsvSource({
            "4954, 262577"
    })

    public void checkRegistrationByPassportAlreadyRegisteredUser(String passportSeries, String passportNumber) {
        Response response = customerService.checkRegistrationByPassport(passportSeries,passportNumber);
        assertAll(
                () -> assertEquals(SC_CONFLICT, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Пользователь уже зарегистрирован в СДБО, и повторно зарегистрироваться нельзя",
                        response.body().jsonPath().get("message"), "Сообщение об ошибке не соответствует ожидаемому")
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
        Response response = customerService.checkRegistrationByPassport(passportSeries,passportNumber);
        assertAll(
                () -> assertEquals(SC_FORBIDDEN, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Пользователь заблокирован", response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
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
        Response response = customerService.checkRegistrationByPassportInvalidHttpMethod(invalidHttpMethod, passportSeries, passportNumber);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Метод не разрешен. Сервер знает о запрашиваемом методе, но он был " +
                                "деактивирован и не может быть использован.", response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

}