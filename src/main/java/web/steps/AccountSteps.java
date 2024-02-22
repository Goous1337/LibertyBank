package web.steps;

import io.qameta.allure.Step;
import web.helpers.Waiters;
import web.pages.AccountPage;

public class AccountSteps {

    protected AccountPage accountPage;

    public AccountSteps() {
        accountPage = new AccountPage();
    }

    @Step("Кликнуть на первый счет в списке")
    public void clickAccount() {
        accountPage.clickAccount();
    }

    @Step("Кликнуть на второй счет в списке")
    public void clickSecondAccount() {
        accountPage.clickSecondAccount();
    }

    @Step("Выбрать 'Открытые счета'")
    public void selectOpenAccounts() {
        accountPage.clickOpenAccountsTab();
    }

    @Step("Выбрать 'Закрытые счета'")
    public void selectClosedAccounts() {
        accountPage.clickClosedAccountsTab();
    }

    @Step("Выбрать 'Заблокированные счета'")
    public void selectBlockedAccounts() {
        accountPage.clickBlockedAccountsTab();
    }

    @Step("Кликнуть на основной счет")
    public void clickMainAccountLabel() {
        accountPage.clickMainAccountLabel();
    }

    @Step("Кликнуть на текущий счет")
    public void clickCreateCurrentAccount() {
        accountPage.clickCreateCurrentAccount();
    }

    public boolean isOpenAccountDisplayed() {
        return accountPage.isOpenAccountDisplayed();
    }

    public boolean isCloseAccountsDisplayed() {
        return accountPage.isCloseAccountDisplayed();
    }

    @Step("Отображается статус 'Основной счет'")
    public boolean isMainAccountLabelDisplayed() {
        return accountPage.isMainAccountLabelDisplayed();
    }

    @Step("Отображается статус 'Основной счет' у второго счета")
    public boolean isSecondMainAccountLabelDisplayed() {
        return accountPage.isSecondMainAccountLabelDisplayed();
    }

    @Step("Отображается статус счета")
    public boolean accountStatusIsDisplayed() {
        return accountPage.isAccountStatusDisplayed();
    }

    @Step("Получить статус счета")
    public String getAccountStatus() {
        return accountPage.getAccountStatusText();
    }

    @Step("Отображается значок валюты в рублях")
    public boolean rubleImageIsDisplayed() {
        return accountPage.isRubleImageDisplayed();
    }

    @Step("Отображается значок валюты в долларах")
    public boolean dollarImageIsDisplayed() {
        return accountPage.isDollarImageDisplayed();
    }

    @Step("Отображается значок валюты в евро")
    public boolean euroImageIsDisplayed() {
        return accountPage.isEuroImageDisplayed();
    }

    @Step("Отображается название или номер счета")
    public boolean accountNameOrNumberIsDisplayed() {
        return accountPage.isAccountNameOrNumberDisplayed();
    }

    @Step("Получить название или номер счета")
    public String getAccountNameOrNumber() {
        return accountPage.getAccountNameOrNumberText();
    }

    @Step("Отображается сумма счета")
    public boolean amountIsDisplayed() {
        return accountPage.isAmountDisplayed();
    }

    @Step("Отображается валюта счета")
    public boolean currencyIsDisplayed() {
        return accountPage.isCurrencyDisplayed();
    }

    @Step("Отображается тип счета")
    public boolean accountTypeIsDisplayed() {
        return accountPage.isAccountTypeDisplayed();
    }

    @Step("Получить тип счета")
    public String getAccountType() {
        return accountPage.getAccountTypeText();
    }

    @Step("Отфильтровать счета по всей валюте")
    public void filterByAllCurrency() {
        accountPage.clickFilterByAllCurrency();
    }

    @Step("Отфильтровать счета по валюте в рублях")
    public void filterByRubles() {
        accountPage.clickFilterByRub();
    }

    @Step("Отфильтровать счета по валюте в долларах")
    public void filterByDollars() {
        accountPage.clickFilterByUsd();
    }

    @Step("Отфильтровать счета по валюте в евро")
    public void filterByEuro() {
        accountPage.clickFilterByEur();
    }
}
