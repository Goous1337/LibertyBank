package web.epic_1;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import web.BaseTest;

import static property.UserPropertiesReader.USER_PHONE;

@Epic("Epic -1 Регистрация/Авторизация/Безопасность")
@DisplayName("US-1.2 Авторизация")
public class CheckValidationLoginPasswordTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        open("");
    }

    @DisplayName("Основной сценарий: проверка валидации полей формы авторизации")
    @Description("Данный тест-кейс проверяет валидацию полей \"Номер телефона\", \"Пароль\" при вводе валидных данных")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("LIB-2554")
    @ParameterizedTest
    @ValueSource(strings = {"A!123a", "!\"#$%&'()*+,-./:;Zz0", "<=>?@[]^_`{|}~8qA"})
    public void checkValidationLoginPasswordTest(String password) {
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.enterPassword(password);
        loginSteps.assertSubmitButtonEnabled("rgba(0, 90, 254, 1)", "rgb(117, 127, 138)");
    }
}
