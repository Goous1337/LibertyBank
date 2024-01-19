package web.steps;

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

    public boolean isContractNumberDisplayed() {
        return accountInfoPage.isContractNumberDisplayed();
    }

    public boolean isMainAccountLabelDisplayed() {
        return accountInfoPage.isMainAccountLabelDisplayed();
    }

    public boolean isAccountStatusDisplayed() {
        return accountInfoPage.isAccountStatusDisplayed();
    }
}
