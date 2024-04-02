package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.1 [web] Выпадающее меню")
public class US_2_2_1_CheckDropDownMenuInPersonalAccountTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @DisplayName("Проверка выпадающего меню в личном кабинете")
    @Description("""
            Данный тест-кейс проверяет работу выпадающего меню в личном кабинете
            """)
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("US-2")})
    @TmsLink("LIB-2430")
    @Test
    public void checkDropDownMenuText() {
        homeSteps
                .clickUserMenu()
                .assertIsUserPanelDisplayed();
        dropDownAccountMenuSteps
                .checkVisibilityOfUserPanelButtons();
    }
}

