package api.userAccountService;

import api.BaseTest;
import dataBase.requests.UserAccountServiceDataBaseRequests;
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
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.CustomerServiceConstants.REGISTERED_PHONE_NUMBER;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.URL_USER_ACCOUNT_SERVICE;

@DisplayName("UAS-4 Верификация пользователя с учетом блокировки учетной записи")
public class UAS_4_UserVerificationWithAccountBlockingTest extends BaseTest {

    {
        RestAssured.baseURI = URL_USER_ACCOUNT_SERVICE;
    }

    @DisplayName("Верификация пользователя с валидными данными")
    @Description("Позитивная проверка поведения системы при верификации пользователя с валидными данными.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-279")
    @Test

    public void userVerificationWithValidData() {
        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Верификация пользователя, когда параметр 'verificationCode' не заполнен")
    @Description("Проверка поведения системы, если обязательный параметр 'verificationCode' оставить незаполненным.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-292")
    @ParameterizedTest
    @EmptySource
    @NullSource

    public void userVerificationWithoutVerificationCode(String verificationCode) {

        String phoneNumber = "79974699104";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, verificationCode);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Верификация пользователя, когда параметр 'verificationCode' не соответствует сгенерированному коду")
    @Description("Проверка поведения системы, если код, который ввел пользователь в поле 'verificationCode', " +
            "не соответствует коду, который был сгенерирован системой.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-294")
    @Test

    public void userVerificationWithInvalidVerificationCode() {

        String phoneNumber = "79727824061";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        assertEquals(SC_BAD_REQUEST, userAccountService.verificationMobilePhoneVerificationCode(phoneNumber,
                        "111111").statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Верификация пользователя, когда параметр 'mobilePhone' не заполнен")
    @Description("Проверка поведения системы, если обязательный параметр 'mobilePhone' оставить незаполненным, " +
            "либо значение пустые кавычки.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-296")
    @Issue("https://jira.astondevs.ru/browse/LIB-1325")
    @ParameterizedTest
    @EmptySource
    @NullSource

    public void userVerificationWithoutMobilePhone(String mobilePhone) {

        String phoneNumber = "79060996597";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(mobilePhone, verificationCode);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }


    @DisplayName("Проверка блокировки пользователя при введении не валидных параметров более 3 раз")
    @Description("Проверка блокировки системы после того, как пользователь ввел не валидный код более 3 раз.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-297")
    @Test

    public void userVerificationWithInvalidData() {

        String phoneNumber = "79581210902";
        String invalidVerificationCode = "111111";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, invalidVerificationCode);
        userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, invalidVerificationCode);
        userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, invalidVerificationCode);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, invalidVerificationCode);
        assertEquals(SC_NOT_ACCEPTABLE, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Верификация пользователя, когда в параметре 'mobilePhone' указано не валидное значение")
    @Description("Проверка, что при введении не валидных данных в поле 'mobilePhone', верификация не проходит и система выдает 400 ошибку")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-304,https://jira.astondevs.ru/browse/LIB-310")
    @Issue("https://jira.astondevs.ru/browse/LIB-1326")
    @ParameterizedTest(name = "mobilePhone: {0}")
    @ValueSource(
            strings = {"79998887723",
                    "7999888777",
                    "799988877711",
                    "7999h8888д1",
                    "7999%111111",
                    "799988ЛО87",
                    " 7999888777",
                    "7999888777 "
            }
    )

    public void userVerificationWithInvalidMobilePhone(String invalidMobilePhone) {

        String phoneNumber = "79221768263";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(invalidMobilePhone, verificationCode);
        assertEquals(SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Верификация пользователя, когда метод не POST")
    @Description("Проверка, что при указании метода PATCH вместо POST, система выдает сообщение с 405 ошибкой")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-305")
    @Issue("https://jira.astondevs.ru/browse/LIB-1327")
    @Test

    public void userVerificationWithInvalidMethod() {

        String phoneNumber = "79579874656";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);

        Response response = userAccountService.verificationWithInvalidMethod(phoneNumber, verificationCode);
        assertEquals(SC_METHOD_NOT_ALLOWED, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Проверка верификации пользователя при введении валидного кода, после блокировки")
    @Description("Проверка верификации пользователя при введении валидного кода, " +
            "после того как пользователь ввел 3 раза невалидный код и заблокировался.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-301")
    @Test

    public void userVerificationWithValidDataAfterBlocking() {

        String phoneNumber = "79978020792";
        String invalidVerificationCode = "111111";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, invalidVerificationCode);
        userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, invalidVerificationCode);
        userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, invalidVerificationCode);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, verificationCode);
        assertEquals(SC_NOT_ACCEPTABLE, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Проверка верификации пользователя при введении валидного кода, после 1 попытки ввода невалидного кода")
    @Description("Позитивная проверка верификации пользователя при введении валидного кода, после 1 попытки введения невалидного кода.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-302")
    @Test

    public void userVerification() {

        String phoneNumber = "79031553942";
        String invalidVerificationCode = "111111";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, invalidVerificationCode);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, verificationCode);
        assertEquals(SC_OK, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Верификация пользователя с невалидным 'verificationCode'")
    @Description("Тест направлен на проверку валидации значений кода верификации, отправляемых в BODY запроса.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-303")
    @ParameterizedTest(name = "verificationCode: {0}")
    @ValueSource(
            strings = {"11111",
                    "$$$424",
                    "234Snq"
            }
    )

    public void userVerificationWithInvalidVerificationCode(String verificationCode) {

        String phoneNumber = "79999457736";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, verificationCode);
        assertEquals(SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Верификация пользователя с невалидным 'verificationCode'")
    @Description("Тест направлен на проверку валидации значений кода верификации, отправляемых в BODY запроса.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-303")
    @ParameterizedTest(name = "verificationCode: {0}")
    @ValueSource(
            strings = {
                    "234Дбф",
                    " 00343",
                    "3003434"
            }
    )

    public void userVerificationWithInvalidVerificationCode1(String verificationCode) {

        String phoneNumber = "79849475391";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        Response response = userAccountService.verificationMobilePhoneVerificationCode(phoneNumber, verificationCode);
        assertEquals(SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }


    @DisplayName("Верификация пользователя с невалидным 'verificationCode'")
    @Description("Тест направлен на проверку валидации значений кода верификации, отправляемых в BODY запроса.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-303")
    @ParameterizedTest(name = "verificationCode: {0}")
    @ValueSource(
            ints = {3003434,
                    30034
            }
    )

    public void userVerificationWithInvalidVerificationCode(Integer verificationCode) {

        String phoneNumber = "79808901750";

        userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response response = userAccountService.verificationMobilePhoneVerificationCodeInteger(phoneNumber, verificationCode);
        assertEquals(SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }
}