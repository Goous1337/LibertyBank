package web.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class AccountInfoPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'my-bill-page__current')]")
    private WebElement accountName;

    @FindBy(name = "pencil")
    private WebElement pencilImage;

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

    @FindBy(xpath = "//*[contains(text(), 'Назад')]")
    private WebElement goBackButton;

    @FindBy(xpath = "//*[@data-testid='dots-button']")
    private WebElement dotsInfoButton;

    @FindBy(xpath = "//button[contains(text(), 'Реквизиты')]")
    private WebElement requisitesOpt;

    @FindBy(xpath = "//button[contains(text(), 'Выписка по счету')]")
    private WebElement accountStatementOpt;

    @FindBy(xpath = "//button[contains(text(), 'Сделать счет основным')]")
    private WebElement setMainAccountOpt;

    @FindBy(xpath = "//button[contains(text(), 'Справка о доступном остатке')]")
    private WebElement informationAboutAvailableBalanceOpt;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть счет')]")
    private WebElement closeAccountOpt;

    @FindBy(xpath = "//button[contains(text(), 'Заблокировать счет')]")
    private WebElement blockAccountOpt;

    @FindBy(xpath = "//button[contains(text(), 'Разблокировать счет')]")
    private WebElement unblockAccountOpt;

    @FindBy(xpath = "//p[contains(text(), 'Закрыт')]")
    private WebElement closedLabel;

    public boolean isAccountNameDisplayed() {
        Waiters.waitElement(accountName);
        return accountName.isDisplayed();
    }

    public String getAccountName() {
        Waiters.waitElement(accountName);
        return accountName.getText();
    }

    public void clickPencilImage() {
        pencilImage.click();
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
        try {
            return mainAccountLabel.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException exception) {
            return false;
        }
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

    public void clickGoBackButton() {
        goBackButton.click();
    }

    public void clickDotsInfoButton() {
        dotsInfoButton.click();
    }

    public void clickRequisitesOpt() {
        requisitesOpt.click();
    }

    public void clickAccountStatementOpt() {
        accountStatementOpt.click();
    }

    public void clickSetMainAccountOpt() {
        Waiters.waitElement(setMainAccountOpt);
        setMainAccountOpt.click();
    }

    public boolean isSetMainAccountOptDisplayed() {
        try {
            return setMainAccountOpt.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickInformationAboutAvailableBalanceOpt() {
        informationAboutAvailableBalanceOpt.click();
    }

    public void clickCloseAccountOpt() {
        closeAccountOpt.click();
    }

    public void clickBlockAccountOpt() {
        blockAccountOpt.click();
    }

    public void clickUnblockAccountOpt() {
        Waiters.waitElement(unblockAccountOpt);
        unblockAccountOpt.click();
    }

    public boolean isClosedLabelDisplayed() {
        Waiters.waitElement(closedLabel);
        return closedLabel.isDisplayed();
    }
}
