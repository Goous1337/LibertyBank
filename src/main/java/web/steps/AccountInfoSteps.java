package web.steps;

import io.qameta.allure.Step;
import web.pages.AccountInfoPage;

public class AccountInfoSteps {

    protected AccountInfoPage accountInfoPage;

    public AccountInfoSteps() {
        accountInfoPage = new AccountInfoPage();
    }

    public boolean isAccountNameDisplayed() {
        return accountInfoPage.isAccountNameDisplayed();
    }

    public boolean isAccountNumberDisplayed() {
        return accountInfoPage.isAccountNumberDisplayed();
    }

    public boolean isOpenDateDisplayed() {
        return accountInfoPage.isOpenDateDisplayed();
    }

    public boolean isCloseDateDisplayed() {
        return accountInfoPage.isCloseDateDisplayed();
    }

    public boolean isContractNumberDisplayed() {
        return accountInfoPage.isContractNumberDisplayed();
    }

    @Step("Отображается статус 'Основной счет'")
    public boolean isMainAccountLabelDisplayed() {
        return accountInfoPage.isMainAccountLabelDisplayed();
    }

    public boolean isAccountStatusDisplayed() {
        return accountInfoPage.isAccountStatusDisplayed();
    }

    public boolean isCurrencyIconDisplayed() {
        return accountInfoPage.isCurrencyIconDisplayed();
    }

    public boolean isBalanceDisplayed() {
        return accountInfoPage.isBalanceDisplayed();
    }

    public String getAccountStatus() {
        return accountInfoPage.getAccountStatus();
    }

    @Step("Вернуться к списку счетов")
    public void goBack() {
     accountInfoPage.clickGoBackButton();
    }

    @Step("Нажать на три точки")
    public void clickDotsInfoButton() {
        accountInfoPage.clickDotsInfoButton();
    }

    @Step("Выбрать 'Реквизиты'")
    public void selectRequisites() {
        accountInfoPage.clickRequisitesOpt();
    }

    @Step("Выбрать 'Выписка по счету'")
    public void selectAccountStatement() {
        accountInfoPage.clickAccountStatementOpt();
    }

    @Step("Выбрать 'Сделать счет основным'")
    public void selectSetMainAccount() {
        accountInfoPage.clickSetMainAccountOpt();
    }

    @Step("Отображется пункт 'Сделать счет основным'")
    public boolean isSetMainAccountOptDisplayed() {
        return accountInfoPage.isSetMainAccountOptDisplayed();
    }

    @Step("Выбрать 'Справка о доступном остатке'")
    public void selectInformationAboutAvailableBalance() {
        accountInfoPage.clickInformationAboutAvailableBalanceOpt();
    }

    @Step("Выбрать 'Закрыть счет'")
    public void selectCloseAccount() {
        accountInfoPage.clickCloseAccountOpt();
    }

    @Step("Выбрать 'Заблокировать счет'")
    public void selectBlockAccount() {
        accountInfoPage.clickBlockAccountOpt();
    }

    @Step("Выбрать 'Разблокировать счет'")
    public void selectUnblockAccount() {
        accountInfoPage.clickUnblockAccountOpt();
    }
}
