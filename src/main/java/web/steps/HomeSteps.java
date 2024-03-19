package web.steps;

import io.qameta.allure.Step;
import web.pages.DropDownAccountMenuPage;
import web.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;

public class HomeSteps {
    protected HomePage homePage;
    protected DropDownAccountMenuPage dropDownAccountMenuPage;

    public HomeSteps() {
        homePage = new HomePage();
        dropDownAccountMenuPage = new DropDownAccountMenuPage();
    }

    @Step("Кликнуть на кнопку с именем авторизованного пользователя")
    public HomeSteps clickUserMenu() {
        homePage.clickUserMenu();
        return this;
    }

    @Step("Кликнуть на кнопку 'Выйти'")
    public HomeSteps clickExitFromUserAccount() {
        dropDownAccountMenuPage.exitFromUserAccount();
        return this;
    }

    @Step("Отображается выпадающие меню пользователя")
    public boolean isUserPanelDisplayed() {
        return dropDownAccountMenuPage.isUserPanelDisplayed();
    }

    @Step("Проверка отображения выпадающего меню пользователя")
    public HomeSteps assertIsUserPanelDisplayed() {
        assertTrue(dropDownAccountMenuPage.isUserPanelDisplayed(), String.format(DISPLAYED_MESSAGE, "выпадающие меню пользователя"));
        return this;
    }

    @Step("Проверка выхода из аккаунта авторизированным пользователем.")
    public void assertIsUnauthorizedHomeDisplayed() {
        assertTrue(homePage.isUnauthorizedHomeDisplayed());
    }

    @Step("Кликнуть на кнопку 'Безопасность'")
    public HomeSteps clickSecurityBtn() {
        dropDownAccountMenuPage.clickSecurityBtn();
        return this;
    }

    @Step("Кликнуть на кнопку 'Личные данные'")
    public HomeSteps clickPersonalDataBtn() {
        dropDownAccountMenuPage.clickPersonDataBtn();
        return this;
    }
}
