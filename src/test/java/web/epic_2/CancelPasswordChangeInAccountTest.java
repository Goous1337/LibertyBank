package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;

@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.3 [web] Безопасность")
public class CancelPasswordChangeInAccountTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
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
        homeSteps
                .clickUserMenu()
                .clickSecurityBtn();
        assertTrue(securitySteps.isSecurityBarDisplayed(), String.format(DISPLAYED_MESSAGE, "Отображается раздел 'Безопасность'"));
        securitySteps.clickChangePasswordBtn();
        assertTrue(changePasswordSteps.isChangePasswordPresent(), String.format(DISPLAYED_MESSAGE, "Отображается кнопка 'Изменить пароль'"));
        changePasswordSteps.clickChangePasswordBtn();
        assertTrue(securitySteps.isSecurityBarDisplayed(), String.format(DISPLAYED_MESSAGE, "Отображается раздел 'Безопасность"));
    }
}
