package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;

@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.6 [web] Выход из аккаунта")
public class LogoutFromAccountTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @DisplayName("Основной сценарий. Выход из аккаунта.")
    @Description("В данном тест-кейсе проводится проверка возможности выхода из аккаунта в личном кабинете")
    @Tags({@Tag("Web"), @Tag("Smoke")})
    @TmsLink("LIB-2601")
    @Test
    public void logoutFromAccount() {
        homeSteps.clickUserMenu();
        assertTrue(homeSteps.isUserPanelDisplayed(), String.format(DISPLAYED_MESSAGE, "выпадающие меню пользователя"));
        homeSteps.clickExitFromUserAccount();
        assertTrue(homeSteps.isUnauthorizedHomeDisplayed());
    }
}
