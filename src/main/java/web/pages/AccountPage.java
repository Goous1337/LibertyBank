package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class AccountPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Открытые счета')]")
    private WebElement openAccountsTab;

    @FindBy(xpath = "//*[contains(text(), 'Закрытые счета')]")
    private WebElement closedAccountsTab;

    @FindBy(xpath = "//*[contains(text(), 'Заблокированные счета')]")
    private WebElement blockedAccountsTab;

    @FindBy(xpath = "//*[contains(text(), 'Основной счет')]")
    private WebElement mainAccountLabel;

    @FindBy(xpath = "//*[contains(text(), 'Текущий счет')]")
    private WebElement createCurrentAccount;

    @FindBy(xpath = "//p[contains(text(), 'Текущий счет')]")
    private WebElement account;

    @FindBy(css = "._label-width_adjustable_c42rp_10")
    private WebElement accountStatus;

    @FindBy(xpath = "//*[@href = '#icon-currency-ruble']")
    private WebElement rubleImage;

    @FindBy(xpath = "//*[@href = '#icon-currency-dollar']")
    private WebElement dollarImage;

    @FindBy(xpath = "//*[@href = '#icon-currency-euro']")
    private WebElement euroImage;

    @FindBy(css = "._account-name_15f10_25")
    private WebElement accountNameOrNumber;

    @FindBy(css = "._amount_15f10_36")
    private WebElement amount;

    @FindBy(css = "._currency_15f10_47")
    private WebElement currency;

    @FindBy(css = "._account-type_15f10_19")
    private WebElement accountType;

    @FindBy(xpath = "//*[contains(text(), 'Все')]")
    private WebElement filterByAllCurrency;

    @FindBy(xpath = "//*[contains(text(), 'RUB')]")
    private WebElement filterByRub;

    @FindBy(xpath = "//*[contains(text(), 'USD')]")
    private WebElement filterByUsd;

    @FindBy(xpath = "//*[contains(text(), 'EUR')]")
    private WebElement filterByEur;

    public void clickMainAccountLabel() {
        Waiters.waitElement(mainAccountLabel);
        mainAccountLabel.click();
    }

    public void clickCreateCurrentAccount() {
        Waiters.waitElement(createCurrentAccount);
        createCurrentAccount.click();
    }

    public boolean isOpenAccountDisplayed() {
        return openAccountsTab.isDisplayed();
    }

    public boolean isMainAccountLabelDisplayed() {
        return mainAccountLabel.isDisplayed();
    }

    public boolean isAccountStatusDisplayed() {
        return accountStatus.isDisplayed();
    }

    public String getAccountStatusText() {
        return accountStatus.getText();
    }

    public boolean isRubleImageDisplayed() {
        return rubleImage.isDisplayed();
    }

    public boolean isDollarImageDisplayed() {
        return dollarImage.isDisplayed();
    }

    public boolean isEuroImageDisplayed() {
        return euroImage.isDisplayed();
    }

    public boolean isAccountNameOrNumberDisplayed() {
        return accountNameOrNumber.isDisplayed();
    }

    public String getAccountNameOrNumberText() {
        return accountNameOrNumber.getText();
    }

    public boolean isAmountDisplayed() {
        return amount.isDisplayed();
    }

    public boolean isCurrencyDisplayed() {
        return currency.isDisplayed();
    }

    public boolean isAccountTypeDisplayed() {
        return accountType.isDisplayed();
    }

    public String getAccountTypeText() {
        return accountType.getText();
    }

    public void clickFilterByAllCurrency() {
        filterByAllCurrency.click();
    }

    public void clickFilterByRub() {
        Waiters.waitElement(filterByRub);
        filterByRub.click();
    }

    public void clickFilterByUsd() {
        Waiters.waitElement(filterByUsd);
        filterByUsd.click();
    }

    public void clickFilterByEur() {
        Waiters.waitElement(filterByEur);
        filterByEur.click();
    }

    public void clickOpenAccountsTab() {
        Waiters.waitElement(openAccountsTab);
        openAccountsTab.click();
    }

    public void clickClosedAccountsTab() {
        Waiters.waitElement(closedAccountsTab);
        closedAccountsTab.click();
    }

    public void clickBlockedAccountsTab() {
        Waiters.waitElement(blockedAccountsTab);
        blockedAccountsTab.click();
    }

    public void clickAccount() {
        Waiters.waitElement(account);
        account.click();
    }
}
