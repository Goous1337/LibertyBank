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

    @FindBy(xpath = "(//*[contains(text(), 'Основной счет')])[2]")
    private WebElement secondMainAccountLabel;

    @FindBy(xpath = "//*[contains(text(), 'Откройте счет не выходя из дома')]")
    private WebElement createCurrentAccount;

    @FindBy(xpath = "//p[contains(text(), 'Текущий счет')]")
    private WebElement account;

    @FindBy(xpath = "(//p[contains(text(), 'Текущий счет')])[2]")
    private WebElement secondAccount;

    @FindBy(css = "//p[contains(@class, '_label_19f2j_1')]")
    private WebElement accountStatus;

    @FindBy(xpath = "//*[@href = '#icon-currency-ruble']")
    private WebElement rubleImage;

    @FindBy(xpath = "//*[@href = '#icon-currency-dollar']")
    private WebElement dollarImage;

    @FindBy(xpath = "//*[@href = '#icon-currency-euro']")
    private WebElement euroImage;

    @FindBy(xpath = "//p[@class='_account-name_1sf0s_36']")
    private WebElement accountNameOrNumber;

    @FindBy(xpath = "//p[@class='_amount_1sf0s_50']")
    private WebElement amount;

    @FindBy(xpath = "//p[contains(@class, 'currency_1sf0s_61')]")
    private WebElement currency;

    @FindBy(xpath = "//p[contains(@class, '_account-type_1sf0s_30')]")
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

    public boolean isCloseAccountDisplayed() {
        return closedAccountsTab.isDisplayed();
    }

    public boolean isMainAccountLabelDisplayed() {
        return mainAccountLabel.isDisplayed();
    }

    public boolean isSecondMainAccountLabelDisplayed() {
        return secondMainAccountLabel.isDisplayed();
    }

    public boolean isAccountStatusDisplayed() {
        return accountStatus.isDisplayed();
    }

    public String getAccountStatusText() {
        return accountStatus.getText();
    }

    public boolean isRubleImageDisplayed() {
        Waiters.waitElement(rubleImage);
        return rubleImage.isDisplayed();
    }

    public boolean isDollarImageDisplayed() {
        Waiters.waitElement(dollarImage);
        return dollarImage.isDisplayed();
    }

    public boolean isEuroImageDisplayed() {
        Waiters.waitElement(euroImage);
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

    public void clickSecondAccount() {
        Waiters.waitElement(secondAccount);
        secondAccount.click();
    }
}
