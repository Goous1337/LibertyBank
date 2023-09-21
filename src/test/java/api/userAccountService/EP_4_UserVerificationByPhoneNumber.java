package api.userAccountService;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import service.VerificationService;

import static constant.UserServiceConstants.REGISTERED_PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EP_4_UserVerificationByPhoneNumber extends BaseTest {

    //    private String code;
//    @BeforeEach
//    @Test
//    public String checkUserVerificationWithValidData(String validPhoneNumber) {
//        Response response = verificationService.checkVerificationByPhone(REGISTERED_PHONE_NUMBER);
//        code = response.jsonPath().get("verificationCode");
//        assertEquals(HttpStatus.SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому");
//        return response.jsonPath().get("verificationCode");
//    }
    VerificationService verificationService = new VerificationService();

    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-279")
    @Description("Верификация пользователя с валидными данными")
    public void userVerificationWithValidData() {
        String verificationCode = verificationService.getVerificationCode(REGISTERED_PHONE_NUMBER);
        Response response = verificationService.verification(REGISTERED_PHONE_NUMBER, verificationCode);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }
}