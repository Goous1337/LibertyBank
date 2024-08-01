package web.pages.investmentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

public class InvestmentMainPage extends BasePage {

    @FindBy(xpath = "//button[text()='Открыть брокерский счет']")
    private WebElement openBrokerageAccountButton;
    @FindBy(xpath = "//button[text()='Открыть новый счет']")
    private WebElement openNewBrokerageAccountButton;
    @FindBy(xpath = "//a[text()='Портфель']")
    private WebElement briefcaseButton;
    @FindBy(xpath = "//a[text()='Каталог']")
    private WebElement catalogButton;
    @FindBy(xpath = "//a[text()='Аналитика']")
    private WebElement analyticsButton;
    @FindBy(xpath = "//a[text()='Новости']")
    private WebElement newsButton;
    @FindBy(xpath = "//a[text()='Обучение']")
    private WebElement educationButton;
    @FindBy(xpath = "//*[@data-testid='icon-info-rate']")
    private WebElement activeBrokerageAccount;
    @FindBy(xpath = "//h4[text()='Пополнить счет']")
    private WebElement topUpAccountButton;
    @FindBy(xpath = "//h4[text()='Вывести средства']")
    private WebElement withdrawFundsButton;
    @FindBy(xpath = "//h4[text()='История операций']")
    private WebElement transactionHistoryButton;
    @FindBy(xpath = "//h4[text()='Акции']")
    private WebElement stockButton;
    @FindBy(xpath = "//h4[text()='Облигации']")
    private WebElement bondsButton;
    @FindBy(xpath = "//h4[text()='Валюта']")
    private WebElement currencyButton;

    public boolean isOpenBrokerageAccountButtonDisplayed() {
        return openBrokerageAccountButton.isDisplayed();
    }

    public boolean isOpenNewBrokerageAccountButtonDisplayed() {
        return openNewBrokerageAccountButton.isDisplayed();
    }

    public boolean isBriefcaseButtonDisplayed() {
        return briefcaseButton.isDisplayed();
    }

    public boolean isCatalogButtonDisplayed() {
        return catalogButton.isDisplayed();
    }

    public boolean isAnalyticsButtonDisplayed() {
        return analyticsButton.isDisplayed();
    }

    public boolean isNewsButtonDisplayed() {
        return newsButton.isDisplayed();
    }

    public boolean isEducationButtonDisplayed() {
        return educationButton.isDisplayed();
    }

    public boolean isActiveBrokerageAccountDisplayed() {
        return activeBrokerageAccount.isDisplayed();
    }

    public boolean isTopUpAccountButtonDisplayed() {
        return topUpAccountButton.isDisplayed();
    }

    public boolean isWithdrawFundsButtonDisplayed() {
        return withdrawFundsButton.isDisplayed();
    }

    public boolean isTransactionHistoryButtonDisplayed() {
        return transactionHistoryButton.isDisplayed();
    }

    public boolean isStockButtonDisplayed() {
        return stockButton.isDisplayed();
    }

    public boolean isBondsButtonDisplayed() {
        return bondsButton.isDisplayed();
    }

    public boolean isCurrencyButtonDisplayed() {
        return currencyButton.isDisplayed();
    }
}
