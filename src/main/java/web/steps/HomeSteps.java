package web.steps;

import io.qameta.allure.Step;
import web.pages.HomePage;

public class HomeSteps {
    protected HomePage homePage;

    public HomeSteps() {
        homePage = new HomePage();
    }

    @Step("Кликнуть на кнопку с именем авторизованного пользователя")
    public void clickUserMenu() {
        homePage.clickUserMenu();
    }

    @Step("Кликнуть на кнопку 'Выйти'")
    public void clickExitFromUserAccount() {
        homePage.exitFromUserAccount();
    }

    @Step("Отображается выпадающие меню пользователя")
    public boolean isUserPanelDisplayed() {
        return homePage.isUserPanelDisplayed();
    }

    @Step("Отображается надпись 'Войдите в Liberty Bank'")
    public boolean isUnauthorizedHomeDisplayed() {
        return homePage.isUnauthorizedHomeDisplayed();
    }
}
