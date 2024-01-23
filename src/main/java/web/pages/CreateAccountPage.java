package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class CreateAccountPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Выберите валюту')]")
    private WebElement chooseCurrencyText;

    @FindBy(id = "RUB")
    private WebElement currencyRubButton;

    @FindBy(id = "USD")
    private WebElement currencyUsdButton;

    @FindBy(id = "EUR")
    private WebElement currencyEurButton;

    @FindBy(xpath = "//*[contains(text(), 'Сделать счет основным')]")
    private WebElement makeAccountMainText;

    @FindBy(xpath = "//label[contains(@class, '_switch-label_1s1y7_7')]")
    private WebElement mainAccountSwitcher;

    @FindBy(xpath = "//button[contains(text(), 'Открыть счет')]")
    private WebElement createAccountButton;

    @FindBy(xpath = "//*[contains(text(), 'Мы открыли вам счет!')]")
    private WebElement createSuccessfullyMessage;

    @FindBy(xpath = "//*[contains(text(), 'Перейти к списку счетов')]")
    private WebElement navigationToBillsButton;

    public boolean isChooseCurrencyTextDisplayed() {
        return chooseCurrencyText.isDisplayed();
    }

    public boolean isCurrencyRubSelected() {
        return currencyRubButton.isSelected();
    }

    public boolean isCurrencyEurSelected() {
        return currencyEurButton.isSelected();
    }

    public boolean isCurrencyUsdSelected() {
        return currencyUsdButton.isSelected();
    }

    public boolean isMakeAccountMainTextDisplayed() {
        return makeAccountMainText.isDisplayed();
    }

    public boolean isMainAccountSwitcherSelected() {
        return mainAccountSwitcher.isSelected();
    }

    public boolean isCreateAccountButtonEnabled() {
        return createAccountButton.isEnabled();
    }

    public boolean isCreateAccountSuccessfullyMessageDisplayed() {
        return createSuccessfullyMessage.isDisplayed();
    }

    public boolean isNavigationToBillButtonDisplayed() {
        return navigationToBillsButton.isDisplayed();
    }

    public void clickCurrencyRub() {
        currencyRubButton.click();
    }

    public void clickCurrencyEur() {
        currencyEurButton.click();
    }

    public void clickCurrencyUsd() {
        currencyUsdButton.click();
    }

    public void switchMainAccount() {
        mainAccountSwitcher.click();
    }

    public void createAccountButton() {
        createAccountButton.click();
    }

    public void navigateToBillButton() {
        navigationToBillsButton.click();
    }
}
