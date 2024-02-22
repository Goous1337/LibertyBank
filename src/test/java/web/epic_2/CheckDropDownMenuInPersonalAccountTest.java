package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;

@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.1 [web] Выпадающее меню")
public class CheckDropDownMenuInPersonalAccountTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @DisplayName("Проверка выпадающего меню в личном кабинете")
    @Description("""
            Данный тест-кейс проверяет работу выпадающего меню в личном кабинете
            """)
    @Tags({@Tag("Web"), @Tag("Smoke")})
    @TmsLink("LIB-2430")
    @Test
    public void checkDropDownMenuText() {
        homeSteps.clickUserMenu();
        assertTrue(homeSteps.isUserPanelDisplayed(), String.format(DISPLAYED_MESSAGE, "выпадающие меню пользователя"));
        dropDownAccountMenuSteps.checkVisibilityOfUserPanelButtons();
    }
}

