package web.steps;

import io.qameta.allure.Step;
import web.pages.AccountPage;

public class AccountSteps {

    protected AccountPage accountPage;

    public AccountSteps() {
        accountPage = new AccountPage();
    }

    @Step("Кликнуть на основной счет")
    public void clickMainAccountLabel() {
        accountPage.clickMainAccountLabel();
    }
}