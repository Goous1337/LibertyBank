package api.userAccountService;

import api.BaseTest;
import dataBase.DataBaseConnector;
import dataBase.requests.UserAccountServiceDataBaseRequests;
import io.qameta.allure.Description;
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
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static constant.CustomerServiceConstants.REGISTERED_PHONE_NUMBER;
import static constant.DataBaseConstants.USER_ACCOUNT_SERVICE_DB;
import static constant.DataBaseConstants.USER_SERVICE_DB;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.URL_USER_ACCOUNT_SERVICE;

public class EP_3_SavingVerificationCodeTest extends BaseTest {

    static {
        RestAssured.baseURI = URL_USER_ACCOUNT_SERVICE;
    }

    @DisplayName("")

    @Test
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-243")
    @Description("[US EP-3] Успешное сохранение кода верификации")
    public void verificationSuccessfulSavingVerificationCode() {
        String phoneNumber = "79958984928";

        String idCustomer = UserAccountServiceDataBaseRequests.get111(phoneNumber);
        Response response = userAccountService.check111(phoneNumber);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(
                        response.body().jsonPath().get("blockSeconds")));
        assertNotNull(UserAccountServiceDataBaseRequests.get222(phoneNumber, idCustomer),
                "Верификационный код необнаружен");


//        JdbcTemplate jdbcTemplate1 = DataBaseConnector.getJdbcTemplate(USER_ACCOUNT_SERVICE_DB);
////       String idCustomer = "033140e9-ea0a-40c3-a738-060283531147";
////        UUID uuid = UUID.fromString(idCustomer);
//        // здесь поделючение к user_account_service_db
//        String sql2 = "SELECT customer_id, next_attempt_time, last_verification_code " +
//                "FROM user_profile WHERE customer_id=?::uuid";
////        String codeVerification = jdbcTemplate.queryForObject(sql2, String.class, idCustomer);
//        List<Map<String, Object>> rows = jdbcTemplate1.queryForList(sql2, idCustomer);
//        for (Map<String, Object> row : rows) {
//            UUID customerId = (UUID) row.get("customer_id");
//            Timestamp nextAttemptTime = (Timestamp) row.get("next_attempt_time");
//            String lastVerificationCode = (String) row.get("last_verification_code");
//            System.out.println("customerId: " + customerId + ", nextAttemptTime: "
//                    + nextAttemptTime + ", lastVerificationCode: " + lastVerificationCode);
//
//        }
    }
}
//    @Test
//    @Tag("API")
//    @TmsLink("https://jira.astondevs.ru/browse/LIB-245")
//    @Description("[US EP-3] Повторный запрос кода, если время блокировки не истекло")
//    public void requestCodeLockTimeNotExpired() {
//        assertEquals(HttpStatus.SC_OK, verificationService.verificationService(REGISTERED_PHONE_NUMBER).statusCode(),
//                "Код ответа не соответствует ожидаемому");
//        assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, verificationService.verificationService(REGISTERED_PHONE_NUMBER).statusCode(),
//                "Код ответа не соответствует ожидаемому");
//    }
//
//    @ParameterizedTest
//    @ValueSource(
//            strings = {"7999123", "599932555145620", "799912fdf3456", "kdkdk"}
//    )
//    @Tag("API")
//    @TmsLink("https://jira.astondevs.ru/browse/LIB-244")
//    @Description("[US EP-3] Неуспешное сохранение кода в БД")
//    public void unsuccessfulSavingCodeDatabase(String invalidPhoneNumber) {
//        Response response = verificationService.verificationService(invalidPhoneNumber);
//        String verificationCodeFromDB = "";
//        // здесь будет метод, который отправляет запрос к БД и получает верификационный код
//        assertAll(
//                () -> assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
//                        response.statusCode(),
//                        "Код ответа не соответствует ожидаемому"),
//                () -> assertEquals("", verificationCodeFromDB,
//                        "Верификационный код не соответствует ожидаемому")
//        );
//    }
//
//    @ParameterizedTest
//    @ValueSource(
//            strings = {"799912345678", "599932145620", "7999123456", "5999321456"}
//    )
//    @Tag("API")
//    @TmsLink("https://jira.astondevs.ru/browse/LIB-253")
//    @Description("[US EP-3] Отправка номера невалидной длины")
//    public void sendingNumberInvalidLength(String invalidPhoneNumber) {
//        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
//                verificationService.verificationService(invalidPhoneNumber).statusCode(),
//                "Код ответа не соответствует ожидаемому");
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "POST, 77777777777",
//            "PUT, 77777777777",
//            "GET, 77777777777",
//            "DELETE, 77777777777"
//    })
//    @Tag("API")
//    @TmsLink("https://jira.astondevs.ru/browse/LIB-312")
//    @Description("[US EP-3] Проверка кода верификации, если отправить невалидный метод")
//    public void checkingVerificationCodeSendInvalidMethod(String invalidHttpMethod, String validPhoneNumber) {
//        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
//                verificationService.checkVerificationCodeInvalidHttpMethod(invalidHttpMethod, validPhoneNumber).statusCode(),
//                "Код ответа не соответствует ожидаемому");
//    }
//
//    @ParameterizedTest
//    @ValueSource(
//            strings = {"0000000000", "111111111111", "+1234567890", "-1234567890", " ()12345678901"}
//    )
//    @Tag("API")
//    @TmsLink("https://jira.astondevs.ru/browse/LIB-314")
//    @Description("[US EP-3] Проверка отправки невалидного номера телефона")
//    public void checkingWhetherInvalidPhoneNumberWasSent(String invalidPhoneNumber) {
//        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
//                verificationService.verificationService(invalidPhoneNumber).statusCode(),
//                "Код ответа не соответствует ожидаемому");
//    }
//
//    @ParameterizedTest
//    @ValueSource(
//            strings = {"7(999)1234567", "7 999 123 45 67", "7999-123-45-67", "+79991234567"}
//    )
//    @Tag("API")
//    @TmsLink("https://jira.astondevs.ru/browse/LIB-256")
//    @Description("[US EP-3] Отправка телефона в форматированном виде")
//    public void sendingPhoneNumberFormattedForm(String invalidPhoneNumber) {
//        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
//                verificationService.verificationService(invalidPhoneNumber).statusCode(),
//                "Код ответа не соответствует ожидаемому");
//
//    }
//
//    @Test
//    @Tag("API")
//    @TmsLink("https://jira.astondevs.ru/browse/LIB-246")
//    @Description("[US EP-3] Отправка номера телефона, где пустое тело запроса")
//    public void sendingPhoneNumberRequestBodyEmpty() {
//        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
//                verificationService.checkVerificationCodeWithoutParam().statusCode(),
//                "Код ответа не соответствует ожидаемому");
//    }
//
//}