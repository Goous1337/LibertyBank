package web.steps;

import io.qameta.allure.Step;
import web.helpers.Waiters;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.AccountPage;

import static web.constans.AccountServiceConstants.*;

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

    @Step("Отображается вкладка 'Открытые счета'")
    public void assertOpenAccountsTabIsDisplayed() {
        Assertions.assertTrue(accountPage.isOpenAccountsTabDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, OPEN_ACCOUNTS_TAB));
        TestListener.takeScreenshot();
    }

    @Step("Отображается вкладка 'Закрытые счета'")
    public void assertClosedAccountsTabIsDisplayed() {
        Assertions.assertTrue(accountPage.isCloseAccountsTabDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, CLOSED_ACCOUNTS_TAB));
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус 'Основной счет'")
    public void assertMainAccountLabelIsDisplayed() {
        Assertions.assertTrue(accountPage.isMainAccountLabelDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается статус 'Основной счет'")
    public void assertMainAccountLabelIsNotDisplayed() {
        Assertions.assertFalse(accountPage.isMainAccountLabelDisplayed(), String.format(DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }


    @Step("Отображается статус 'Основной счет' у второго счета")
    public boolean isSecondMainAccountLabelDisplayed() {
        return accountPage.isSecondMainAccountLabelDisplayed();
    }

    @Step("Отображается статус счета")
    public void assertAccountStatusIsDisplayed() {
        Assertions.assertTrue(accountPage.isAccountStatusDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус счета"));
        TestListener.takeScreenshot();
    }

    @Step("Получить статус счета")
    public String getAccountStatus() {
        return accountPage.getAccountStatusText();
    }

    @Step("Отображается значок валюты в рублях")
    public void assertRubleImageIsDisplayed() {
        Assertions.assertTrue(accountPage.isRubleImageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, RUB));
        TestListener.takeScreenshot();
    }

    @Step("Отображается значок валюты в долларах")
    public void assertDollarImageIsDisplayed() {
        Assertions.assertTrue(accountPage.isDollarImageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, USD));
        TestListener.takeScreenshot();
    }

    @Step("Отображается значок валюты в евро")
    public void assertEuroImageIsDisplayed() {
        Assertions.assertTrue(accountPage.isEuroImageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, EUR));
        TestListener.takeScreenshot();
    }

    @Step("Отображается название или номер счета")
    public void accountNameOrNumberIsDisplayed() {
        Assertions.assertTrue(accountPage.isAccountNameOrNumberDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Номер или имя счета"));
        TestListener.takeScreenshot();
    }

    @Step("Получить название или номер счета")
    public String getAccountNameOrNumber() {
        return accountPage.getAccountNameOrNumberText();
    }

    @Step("Отображается сумма счета")
    public void amountIsDisplayed() {
        Assertions.assertTrue(accountPage.isAmountDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сумма счета"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается валюта счета")
    public void currencyIsDisplayed() {
        Assertions.assertTrue(accountPage.isCurrencyDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Валюта счета"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается тип счета")
    public void accountTypeIsDisplayed() {
        Assertions.assertTrue(accountPage.isAccountTypeDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Тип счета"));
        TestListener.takeScreenshot();
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

    @Step("Статус счета соответствует статусу 'Активный'")
    public void assertAccountStatusIsOpen() {
        Assertions.assertEquals(OPEN_ACCOUNT_STATUS, getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, OPEN_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }

    @Step("Статус счета соответствует статусу 'Закрыт'")
    public void assertAccountStatusIsClosed() {
        Assertions.assertEquals(CLOSED_ACCOUNT_STATUS, getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, CLOSED_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }

    @Step("Статус счета соответствует статусу 'Заблокирован'")
    public void assertAccountStatusIsBlocked() {
        Assertions.assertEquals(BLOCKED_ACCOUNT_STATUS, getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, BLOCKED_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }
}
