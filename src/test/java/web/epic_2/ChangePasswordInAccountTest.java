package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static property.UserPropertiesReader.USER_PASSWORD;

@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.3 [web] Безопасность")
public class ChangePasswordInAccountTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        homeSteps
                .clickUserMenu()
                .clickSecurityBtn();
        securitySteps.clickChangePasswordBtn();
    }

    @DisplayName("Основной сценарий. Изменение пароля в личном кабинете пользователя")
    @Description("Проверка успешного изменения пароля пользователя в личном кабинете")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("LIB-2600")
    @Test
    public void changePasswordInAccountTest() {
        String newPassword = USER_PASSWORD + '1';
        changePasswordSteps
                .setKeysToOldPasswordInput(USER_PASSWORD)
                .setKeysToNewPasswordInput(newPassword)
                .setKeysToConfirmPasswordInput(newPassword)
                .clickSubmitPasswordChangeBtn();
        personalDataSteps.assertLastNotificationStatus();
        securitySteps.clickChangePasswordBtn();
        changePasswordSteps.resetPasswordForUser(newPassword);
    }

    @DisplayName("Проверка возможности отмены изменения пароля в личном кабинете")
    @Description("""
            Проверка возможности пользователем отменить смену пароля на странице "Изменение пароля".
            При нажатии на кнопку "Отмена"происходит возврат на предыдущую страницу.
            """)
    @Tags({@Tag("Web"), @Tag("Smoke")})
    @TmsLink("LIB-2622")
    @Test
    public void logoutFromAccount() {
        changePasswordSteps.assertChangePasswordBtnIsPresent();
        changePasswordSteps.clickCancelPasswordChangeBtn();
        securitySteps.assertSecurityBarIsDisplayed();
    }
}
