package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class AccountInfoPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'my-bill-page__current')]")
    private WebElement accountName;

    @FindBy(xpath = "//*[contains(@class, 'my-bill-card__top-number-text')]")
    private WebElement accountNumber;

    @FindBy(xpath = "//*[contains(@class, 'my-bill-card__bot-items')]/div[2]")
    private WebElement openDate;

    @FindBy(xpath = "//*[contains(@class, 'my-bill-card__bot-items')]/div[3]")
    private WebElement closeDate;

    @FindBy(xpath = "//*[contains(@class, 'my-bill-card__bot-items')]/div[1]")
    private WebElement contractNumber;

    @FindBy(xpath = "//*[contains(text(), 'Основной счет')]")
    private WebElement mainAccountLabel;

    @FindBy(css = "use[href^='#icon-currency']")
    private WebElement currencyIcon;

    @FindBy(xpath = "//*[contains(text(), 'Баланс')]")
    private WebElement balance;

    @FindBy(xpath = "//div[contains(@class, 'my-bill-card__top-desc')]/p[2]")
    private WebElement accountStatus;

    @FindBy(xpath = "//div[@data-testid='dots-button']")
    private WebElement actionsMenuButton;

    @FindBy(xpath = "//button[contains(text(), 'Заблокировать счет')]")
    private WebElement blockButton;

    @FindBy(xpath = "//button[contains(text(), 'Разблокировать счет')]")
    private WebElement unblockButton;

    public void clickActionsMenuButton() {
        Waiters.waitElement(actionsMenuButton);
        actionsMenuButton.click();
    }

    public void clickBlockButton() {
        Waiters.waitElement(blockButton);
        blockButton.click();
    }

    public void clickUnblockButton() {
        Waiters.waitElement(unblockButton);
        unblockButton.click();
    }

    public boolean isAccountNameDisplayed() {
        Waiters.waitElement(accountName);
        return accountName.isDisplayed();
    }

    public boolean isAccountNumberDisplayed() {
        return accountNumber.isDisplayed();
    }

    public boolean isOpenDateDisplayed() {
        return openDate.isDisplayed();
    }

    public boolean isCloseDateDisplayed() {
        return closeDate.isDisplayed();
    }

    public boolean isContractNumberDisplayed() {
        return contractNumber.isDisplayed();
    }

    public boolean isMainAccountLabelDisplayed() {
        return mainAccountLabel.isDisplayed();
    }

    public boolean isAccountStatusDisplayed() {
        return accountStatus.isDisplayed();
    }

    public boolean isCurrencyIconDisplayed() {
        return currencyIcon.isDisplayed();
    }

    public boolean isBalanceDisplayed() {
        return balance.isDisplayed();
    }

    public String getAccountStatus() {
        return accountStatus.getText();
    }
}
