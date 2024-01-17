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

    @FindBy(xpath = "//*[contains(@class, 'my-bill-card__bot-items')]/div[1]")
    private WebElement contractNumber;

    @FindBy(xpath = "//*[contains(text(), 'Основной счет')]")
    private WebElement mainAccountLabel;

    @FindBy(xpath = "//div[contains(@class, 'my-bill-card__top-desc')]/p[1]")
    private WebElement accountStatus;

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

    public boolean isContractNumberDisplayed() {
        return contractNumber.isDisplayed();
    }

    public boolean isMainAccountLabelDisplayed() {
        return mainAccountLabel.isDisplayed();
    }

    public boolean isAccountStatusDisplayed() {
        return accountStatus.isDisplayed();
    }
}
