package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Tags({@Tag("Web"), @Tag("MVP"), @Tag("Smoke")})
@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.6 [web] Выход из аккаунта")
public class US_2_2_6_LogoutFromAccountTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @DisplayName("Основной сценарий. Выход из аккаунта.")
    @Description("В данном тест-кейсе проводится проверка возможности выхода из аккаунта в личном кабинете")
    @TmsLink("LIB-2601")
    @Test
    public void logoutFromAccount() {
        homeSteps
                .clickUserMenu()
                .assertIsUserPanelDisplayed()
                .clickExitFromUserAccount()
                .assertIsUnauthorizedHomeDisplayed();
    }
}
