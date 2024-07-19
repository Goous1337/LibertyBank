package web.pages.depositPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.pages.BasePage;

import java.util.List;

import static web.helpers.Waiters.waitElement;

public class DepositsFilterPage extends BasePage {
    @FindBy(xpath = "//ul[@class='_deposits_wrapper_18ebe_6']")
    private List<WebElement> depositsFilterList;
    @FindBy(xpath = "//form[@class='_container_1uog1_1']")
    private WebElement depositFilterNameText;
    @FindBy(xpath = "//p[text() = 'Фильтры']/..")
    private WebElement depositFilter;
    @FindBy(xpath = "//label[@for='Любая']")
    private WebElement any;
    @FindBy(xpath = "//label[@for='Для накопления']")
    private WebElement forAccumulation;
    @FindBy(xpath = "//label[@for='Для расчёта']")
    private WebElement forCalculation;
    @FindBy(xpath = "//label[@for='RUB']")
    private WebElement currencyRUB;
    @FindBy(xpath = "//label[@for='USD']")
    private WebElement currencyUSD;
    @FindBy(xpath = "//label[@for='EUR']")
    private WebElement currencyEUR;
    @FindBy(xpath = "//input[@name='amount']")
    private WebElement fieldAmountOfDeposit;
    @FindBy(xpath = "//input[@name='term']")
    private WebElement fieldTermOfDeposit;
    @FindBy(xpath = "//input[@placeholder='от 1 000 до 10 000 000']")
    private WebElement fieldMeaningSumRubOfDeposit;
    @FindBy(xpath = "//input[@placeholder='от 1 до 60']")
    private WebElement fieldMeaningTermOfDeposit;
    @FindBy(xpath = "//input[@placeholder='от 3 000 до 1 000 000']")
    private WebElement fieldMeaningSumCurrencyOfDeposit;
    @FindBy(xpath = "//h3[text()='Liberty+ Детский']")
    private WebElement depositLibertyChild;
    @FindBy(xpath = "//p[contains(text(), 'Отсутствуют депозитные продукты по выбранным фильтрам')]")
    private WebElement depositEmpty;

    public void fillSumOfDepositField(String amountDeposit) {
        waitElement(fieldAmountOfDeposit);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.click(fieldAmountOfDeposit)
                .sendKeys(amountDeposit)
                .perform();
    }

    public void fillTermOfDepositField(String periodMonths) {
        waitElement(fieldTermOfDeposit);
        fieldTermOfDeposit.sendKeys(periodMonths);
    }

    public void clickDepositFilter() {
        depositFilter.click();
    }

    public boolean anyButtonDisplayed() {
        return any.isDisplayed();
    }

    public void clickAnyButton() {
        any.click();
    }

    public void clickForAccumulationButton() {
        forAccumulation.click();
    }

    public void clickForCalculationButton() {
        forCalculation.click();
    }

    public void clickCurrencyRUBButton() {
        currencyRUB.click();
    }

    public void clickCurrencyUSDButton() {
        currencyUSD.click();
    }

    public void clickCurrencyEURButton() {
        currencyEUR.click();
    }

    public boolean forAccumulationButtonDisplayed() {
        return forAccumulation.isDisplayed();
    }

    public boolean forCalculationButtonDisplayed() {
        return forCalculation.isDisplayed();
    }

    public boolean currencyRUBButtonDisplayed() {
        return currencyRUB.isDisplayed();
    }

    public boolean currencyUSDButtonDisplayed() {
        return currencyUSD.isDisplayed();
    }

    public boolean currencyEURButtonDisplayed() {
        return currencyEUR.isDisplayed();
    }

    public boolean fieldAmountOfDepositDisplayed() {
        return fieldAmountOfDeposit.isDisplayed();
    }

    public boolean fieldTermOfDepositDisplayed() {
        return fieldTermOfDeposit.isDisplayed();
    }

    public boolean fieldMeaningSumRubOfDepositDisplayed() {
        return fieldMeaningSumRubOfDeposit.isDisplayed();
    }

    public boolean fieldMeaningTermOfDepositDisplayed() {
        return fieldMeaningTermOfDeposit.isDisplayed();
    }

    public boolean fieldMeaningSumCurrencyOfDepositDisplayed() {
        return fieldMeaningSumCurrencyOfDeposit.isDisplayed();
    }

    public boolean isNameOfLibertyChildDeposit() {
        waitElement(depositLibertyChild);
        return depositLibertyChild.isDisplayed();
    }

    public boolean isEmptyDeposit() {
        waitElement(depositEmpty);
        return depositEmpty.isDisplayed();
    }
}
