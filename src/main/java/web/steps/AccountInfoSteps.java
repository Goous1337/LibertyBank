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

    @Step("Кликнуть на кнопку 'Меню действий'")
    public void clickActionsMenuButton() {
        accountInfoPage.clickActionsMenuButton();
    }

    @Step("Нажать на кнопку 'Заблокировать'")
    public void clickBlockButton() {
        accountInfoPage.clickBlockButton();
    }

    @Step("Нажать на кнопку 'Разблокировать'")
    public void clickUnblockButton() {
        accountInfoPage.clickUnblockButton();
    }
}
