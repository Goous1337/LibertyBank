package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import pojo.customerService_2_0.ChangeUserAccountPasswordByPhone;
import preconditions.UserAuthorization;
import service.CustomerService_2_0;
import web.BaseTest;

import java.security.NoSuchAlgorithmException;

import static api.utils.PasswordEncoder.encryptToSHA256ToBase64;
import static constant.CustomerService_2_0_Constants.CUSTOMER_MOBILE_PHONE_TYPE;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;
import static property.UserPropertiesReader.USER_PASSWORD;
import static property.UserPropertiesReader.USER_PHONE;

@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.3 [web] Безопасность")
public class US_2_2_3_ChangePasswordInAccountTest extends BaseTest {
    private String newPassword = USER_PASSWORD;

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
        newPassword = USER_PASSWORD + '1';
        changePasswordSteps
                .setKeysToOldPasswordInput(USER_PASSWORD)
                .setKeysToNewPasswordInput(newPassword)
                .setKeysToConfirmPasswordInput(newPassword)
                .clickSubmitPasswordChangeBtn();
        personalDataSteps.assertLastNotificationStatus();
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

    @AfterEach
    public void tearDownTest() throws NoSuchAlgorithmException {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
        if (!newPassword.equals(USER_PASSWORD)) {
            String token = new UserAuthorization()
                    .getRefreshToken(USER_PHONE, encryptToSHA256ToBase64(newPassword), CUSTOMER_MOBILE_PHONE_TYPE);

            new CustomerService_2_0().checkListAbilityChangePasswordInPersonalAccount
                    (new ChangeUserAccountPasswordByPhone(encryptToSHA256ToBase64(newPassword),
                            encryptToSHA256ToBase64(USER_PASSWORD)), token);
            newPassword = USER_PASSWORD;
        }
    }
}
