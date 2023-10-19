package api.userAccountService;

import api.BaseTest;
import dataBase.requests.UserAccountServiceDataBaseRequests;
import io.qameta.allure.Description;
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
import service.UserAccountService;

import static constant.CustomerServiceConstants.REGISTERED_PHONE_NUMBER;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.URL_USER_ACCOUNT_SERVICE;

@DisplayName("EP-4 Верификация пользователя с учетом блокировки учетной записи")
public class EP_4_UserVerificationByPhoneNumber extends BaseTest {

    {
        RestAssured.baseURI = URL_USER_ACCOUNT_SERVICE;
    }

    UserAccountService verificationService = new UserAccountService();

    @DisplayName("Верификация пользователя с валидными данными")
    @Description("Позитивная проверка поведения системы при верификации пользователя с валидными данными.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-279")
    @Test

    public void userVerificationWithValidData() {
        userAccountService.checkingVerificationCodeSuccessfullySaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.receivingIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        String verificationCode = UserAccountServiceDataBaseRequests.receivingVerificationCodeByIdCustomer(idCustomer);
        Response response = verificationService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    //не проходит в целом
    @DisplayName("Верификация пользователя, когда параметр 'verificationCode' не заполнен")
    @Description("Проверка поведения системы, если обязательный параметр 'verificationCode' оставить незаполненным.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-292")
    @ParameterizedTest
    @EmptySource
    @NullSource

    public void userVerificationWithoutVerificationCode(String verificationCode) {

        String mobilPhone = "79060996597";

        userAccountService.checkingVerificationCodeSuccessfullySaved(mobilPhone);
        Response response = verificationService.verificationMobilePhone(mobilPhone, verificationCode);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Верификация пользователя, когда параметр 'verificationCode' не соответствует сгенерированному коду")
    @Description("Проверка поведения системы, если код, который ввел пользователь в поле 'verificationCode'," +
            " не соответствует коду, который был сгенерирован системой")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-294")
    @Test

    public void userVerificationWithInvalidVerificationCode() {

        String mobilPhone = "79948964168";
        String verificationCode = "111111";

        userAccountService.checkingVerificationCodeSuccessfullySaved(mobilPhone);
        assertEquals(SC_BAD_REQUEST, verificationService.verificationMobilePhoneVerificationCode(mobilPhone,
                        verificationCode).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    //выдает 500 ошибку баг
    @DisplayName("Верификация пользователя, когда параметр 'mobilePhone' не заполнен")
    @Description("Проверка поведения системы, если обязательный параметр 'mobilePhone' оставить незаполненным.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-296")
    @ParameterizedTest
    @EmptySource
    @NullSource

    public void userVerificationWithoutMobilePhone(String mobilePhone) {

        String mobilPhone = "79727824061";

        userAccountService.checkingVerificationCodeSuccessfullySaved(mobilPhone);
        String idCustomer = UserAccountServiceDataBaseRequests.receivingIdCustomerByPhoneNumber(mobilPhone);
        String verificationCode = UserAccountServiceDataBaseRequests.receivingVerificationCodeByIdCustomer(idCustomer);
        assertEquals(SC_BAD_REQUEST, verificationService.verificationMobilePhone(mobilePhone, verificationCode).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Проверка блокировки пользователя при введении не валидных параметров более 3 раз")
    @Description("Проверка блокировки системы после того, как пользователь ввел не валидный код более 3 раз.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-297")
    @Test

    public void userVerificationWithInvalidData() {

        String phoneNumber = "79581210902";
        String verificationCode = "123456";

        userAccountService.checkingVerificationCodeSuccessfullySaved(phoneNumber);
        verificationService.verificationInvalidVerificationCode(phoneNumber, verificationCode);
        verificationService.verificationInvalidVerificationCode(phoneNumber, verificationCode);
        verificationService.verificationInvalidVerificationCode(phoneNumber, verificationCode);
        Response response = verificationService.verificationInvalidVerificationCode(phoneNumber, verificationCode);
        assertEquals(SC_NOT_ACCEPTABLE, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    //500 ошибка баг
    @DisplayName("Верификация пользователя, когда в параметре 'mobilePhone' указано не валидное значение")
    @Description("Проверка, что при введении не валидных данных в поле 'mobilePhone', верификация не проходит и система выдает 400 ошибку")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-304,https://jira.astondevs.ru/browse/LIB-310")
    @ParameterizedTest
    @ValueSource(
            strings = {"79998887723",
                    "7999888777",
                    "799988877711",
                    "7999h8888д1",
                    "7999%111111",
                    "7999888777",
                    " 7999888777",
                    "7999888777 "}
    )

    public void userVerificationWithInvalidMobilePhone(String mobilePhone) {

        String phoneNumber = "79490515678";

        userAccountService.checkingVerificationCodeSuccessfullySaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.receivingIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode = UserAccountServiceDataBaseRequests.receivingVerificationCodeByIdCustomer(idCustomer);
        Response response = verificationService.verificationMobilePhoneVerificationCode(mobilePhone, verificationCode);
        assertEquals(SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    //500 ошибка баг
    @DisplayName("Верификация пользователя, когда метод не POST")
    @Description("Проверка, что при указании метода PATCH вместо POST, система выдает сообщение с 405 ошибкой")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-305")
    @Test

    public void userVerificationWithInvalidMethod() {

        String phoneNumber = "79221768263";

        userAccountService.checkingVerificationCodeSuccessfullySaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.receivingIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode = UserAccountServiceDataBaseRequests.receivingVerificationCodeByIdCustomer(idCustomer);
        Response response = verificationService.verificationWithInvalidMethod(phoneNumber, verificationCode);
        assertEquals(SC_METHOD_NOT_ALLOWED, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Проверка верификации пользователя при введении валидного кода, после блокировки")
    @Description("Проверка верификации пользователя при введении валидного кода, " +
            "после того как пользователь ввел 3 раза невалидный код и заблокировался.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-301")
    @Test

    public void userVerificationWithValidDataAfterBlocking() {

        String phoneNumber = "79579874656";
        String verificationCode = "123456";

        userAccountService.checkingVerificationCodeSuccessfullySaved(phoneNumber);
        verificationService.verificationInvalidVerificationCode(phoneNumber, verificationCode);
        verificationService.verificationInvalidVerificationCode(phoneNumber, verificationCode);
        verificationService.verificationInvalidVerificationCode(phoneNumber, verificationCode);
        userAccountService.checkingVerificationCodeSuccessfullySaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.receivingIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode1 = UserAccountServiceDataBaseRequests.receivingVerificationCodeByIdCustomer(idCustomer);
        Response response = verificationService.verificationMobilePhoneVerificationCode(phoneNumber, verificationCode1);
        assertEquals(SC_NOT_ACCEPTABLE, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Проверка верификации пользователя при введении валидного кода, после 1 попытки ввода невалидного кода")
    @Description("Позитивная проверка верификации пользователя при введении валидного кода, " +
            "после 1 попытки введения невалидного кода.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-302")
    @Test

    public void userVerification() {

        String phoneNumber = "79978020792";
        String verificationCode = "111111";


        userAccountService.checkingVerificationCodeSuccessfullySaved(phoneNumber);
        verificationService.verificationInvalidVerificationCode(phoneNumber, verificationCode);
        userAccountService.checkingVerificationCodeSuccessfullySaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.receivingIdCustomerByPhoneNumber(phoneNumber);
        String verificationCode1 = UserAccountServiceDataBaseRequests.receivingVerificationCodeByIdCustomer(idCustomer);
        Response response = verificationService.verificationMobilePhoneVerificationCode(phoneNumber, verificationCode1);
        assertEquals(SC_OK, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Верификация пользователя с невалидным 'verificationCode'")
    @Description("Тест направлен на проверку валидации значений кода верификации, отправляемых в BODY запроса.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-303")
    @ParameterizedTest
    @ValueSource(
            strings = {"30034",
                    "$$$424",
                    "234Snq",
                    "234Дбф",
                    " 00343",
                    "30034 ",
                    "3003434"
            }
    )

    public void userVerificationWithInvalidVerificationCode(String verificationCode) {

        String mobilPhone = "79031553942";

        Response response = verificationService.verificationMobilePhoneVerificationCode(mobilPhone, verificationCode);
        assertEquals(SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Верификация пользователя с невалидным 'verificationCode'")
    @Description("Тест направлен на проверку валидации значений кода верификации, отправляемых в BODY запроса.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-303")
    @ParameterizedTest
    @ValueSource(
            ints = {266439,
                    2664392
            }
    )

    public void userVerificationWithInvalidType(Integer verificationCode) {

        String mobilPhone = "79808901750";

        Response response = verificationService.verificationMobilePhoneVerificationCodeInteger(mobilPhone, verificationCode);
        assertEquals(SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }
}