package web.steps;

import io.qameta.allure.Step;
import web.pages.CreateAccountPage;

public class CreateAccountSteps {

    public CreateAccountPage createAccountPage;

    public CreateAccountSteps() {
        createAccountPage = new CreateAccountPage();
    }

    public boolean isChooseCurrencyTextDisplayed() {
        return createAccountPage.isChooseCurrencyTextDisplayed();
    }

    public boolean isCurrencyRubSelected() {
        return createAccountPage.isCurrencyRubSelected();
    }

    public boolean isCurrencyEurSelected() {
        return createAccountPage.isCurrencyEurSelected();
    }

    public boolean isCurrencyUsdSelected() {
        return createAccountPage.isCurrencyUsdSelected();
    }

    public boolean isMakeAccountMainTextDisplayed() {
        return createAccountPage.isMakeAccountMainTextDisplayed();
    }

    public boolean isMainAccountSwitcherSelected() {
        return createAccountPage.isMainAccountSwitcherSelected();
    }

    public boolean isCreateAccountButtonEnabled() {
        return createAccountPage.isCreateAccountButtonEnabled();
    }

    public boolean isCreateAccountSuccessfullyMessageDisplayed() {
        return createAccountPage.isCreateAccountSuccessfullyMessageDisplayed();
    }

    public boolean isNavigationToBillButtonDisplayed() {
        return createAccountPage.isNavigationToBillButtonDisplayed();
    }

    @Step("Выбрать валюту RUB")
    public void clickCurrencyRub() {
        createAccountPage.clickCurrencyRub();
    }

    @Step("Выбрать валюту EUR")
    public void clickCurrencyEur() {
        createAccountPage.clickCurrencyEur();
    }

    @Step("Выбрать валюту USD")
    public void clickCurrencyUsd() {
        createAccountPage.clickCurrencyUsd();
    }

    @Step("Сделать счет основным")
    public void createMainAccount() {
        createAccountPage.switchMainAccount();
    }

    @Step("Нажать кнопку Открыть счет")
    public void createAccount() {
        createAccountPage.createAccountButton();
    }

    @Step("Нажать кнопку Перейти ко всем счетам")
    public void clickToNavigationToBillButton() {
        createAccountPage.navigateToBillButton();
    }
}
