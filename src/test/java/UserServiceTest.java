import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import api.core.RequestParam;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;

import static api.core.RequestParamType.PARAMETER;
import static constant.UserServiceConstants.PARAMETER_MOBILE_PHONE;
import static constant.UserServiceConstants.REGISTERED_PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest extends BaseTest {

    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-239")
    @Description("Проверка регистрации, если номер есть в базе и пользователь зарегистрирован")
    public void registeredPhoneNumberRegistration() {
        params.add(new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, REGISTERED_PHONE_NUMBER));
        int statusCode = registrationService.checkRegistrationByPhone(params).statusCode();
        assertEquals(HttpStatus.SC_CONFLICT, statusCode, "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"7999888777", "799988877714", "7999888", "799fkfs"}
    )
    @TmsLink("https://jira.astondevs.ru/browse/LIB-275")
    @Description("[US EP-1 Negative] Проверка работы валидации номера телефона")
    public void regUserWithInvalidNumberPhone(String invalidNumber) {
        params.add(new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, invalidNumber));
        int statusCode = registrationService.checkRegistrationByPhone(params).statusCode();
        assertEquals(HttpStatus.SC_BAD_REQUEST, statusCode, "Код ответа не соответствует ожидаемому");
    }
}