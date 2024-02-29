package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.2 [web] Личные данные")
public class UpdateEmailInAccountTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        homeSteps
                .clickUserMenu()
                .clickPersonalDataBtn();
    }

    public String getNewEmail(String oldEmail) {
        return oldEmail.length() == 50 || oldEmail.substring(0, oldEmail.indexOf('@')).length() == 30
                ? oldEmail.substring(1)
                : 'a' + oldEmail;
    }

    @DisplayName("Основной сценарий. Успешное обновление email пользователя в личном кабинете.")
    @Description("Данный тест-кейс проверяет возможность изменения email пользователя в личном кабинете")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("LIB-2436")
    @Test
    public void successUpdateEmail() {
        String oldEmail = personalDataSteps.getOldEmailFromInput();
        personalDataSteps.clickChangePasswordBtn();
        updateEmailSteps.sendKeysToEmailInput(getNewEmail(oldEmail)).clickToSubmitBtn();
        securitySteps.assertSecurityBarIsDisplayed();
    }
}
