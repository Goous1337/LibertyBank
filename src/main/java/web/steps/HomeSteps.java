package web.steps;

import io.qameta.allure.Step;
import web.pages.DropDownAccountMenuPage;
import web.pages.HomePage;

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
    public void clickExitFromUserAccount() {
        dropDownAccountMenuPage.exitFromUserAccount();
    }

    @Step("Отображается выпадающие меню пользователя")
    public boolean isUserPanelDisplayed() {
        return dropDownAccountMenuPage.isUserPanelDisplayed();
    }

    @Step("Отображается надпись 'Войдите в Liberty Bank'")
    public boolean isUnauthorizedHomeDisplayed() {
        return homePage.isUnauthorizedHomeDisplayed();
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
