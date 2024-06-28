package web.pages.creditPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;
import static web.helpers.Waiters.waitElementWithColor;

public class CreditApplicationPage extends BasePage {

    /*Поля ввода*/
    @FindBy(xpath = "//input[@data-testid='input-text-amount']")
    private WebElement inputSummaCredit;
    @FindBy(xpath = "//input[@data-testid='input-text-periodMonths']")
    private WebElement inputPeriodMonthsCredit;
    @FindBy(xpath = "//input[@data-testid='input-text-identificationNumber']")
    private WebElement inputIdentificationNumberCredit;
    @FindBy(xpath = "//input[@data-testid='input-text-monthlyExpenditure']")
    private WebElement inputMonthlyExpenditureCredit;
    @FindBy(xpath = "//input[@data-testid='input-text-monthlyIncome']")
    private WebElement inputMonthlyIncomeCredit;
    @FindBy(xpath = "//p[contains(text(), 'Введённая')]")
    private WebElement textValidateAmountCredit;
    @FindBy(xpath = "//p[contains(text(), 'Введённый')]")
    private WebElement textValidatePeriodMonthCredit;
    @FindBy(xpath = "//p[contains(text(), 'Недостаточно символов')]")
    private WebElement textValidateMessage;
    @FindBy(xpath = "//p[contains(text(), 'Пожалуйста, вводите цифры')]")
    private WebElement textValidateSymbolMessage;
    @FindBy(xpath = "//p[contains(text(), 'Не может начинаться с двух нулей')]")
    private WebElement textValidateTwoZeroMessage;
    @FindBy(xpath = "//p[contains(text(), 'Сумма не может начинаться с 0')]")
    private WebElement textValidateFirstZeroMessage;
    @FindBy(xpath = "//p[contains(text(), 'Значение длины может быть либо 10, либо 12 цифр')]")
    private WebElement textValidateLongMessage;
    @FindBy(xpath = "//p[contains(text(), 'Поле обязательно для заполнения')]")
    private WebElement textValidateEmptyMessage;
    @FindBy(xpath = "//input[@type='checkbox']")//input[@type='checkbox']/following-sibling::label
    private WebElement checkBoxCreditObligationsCredit;
    @FindBy(xpath = "//button[text()='Отправить заявку']")
    private WebElement buttonSendCredit;

    @FindBy(xpath = "//input[@value='USD']")
    private WebElement usdRadioButton;
    @FindBy(xpath = "//input[@value='EUR']")
    private WebElement eurRadioButton;
    @FindBy(xpath = "//input[@data-testid='input-text-amount']/../../following-sibling::span[contains(text(), 'USD') or contains(text(), 'RUB') or contains(text(), 'EUR')]")
    private WebElement textCurrencyCredit;

    public void clickSendCredit() {
        waitElement(buttonSendCredit);
        buttonSendCredit.click();
    }

    public void clickEurRadioButton() {
        waitElement(eurRadioButton);
        eurRadioButton.click();
    }

    public void clickUsdRadioButton() {
        waitElement(usdRadioButton);
        usdRadioButton.click();
    }

    public String getCurrencyCreditText() {
        waitElement(textCurrencyCredit);
        return textCurrencyCredit.getText();
    }

    public boolean checkButtonCondition(String bgColor, String textColor, boolean isEnabled) {
        waitElementWithColor(buttonSendCredit, bgColor);
        return buttonSendCredit.isEnabled() == isEnabled
                && buttonSendCredit.getCssValue("background-color").equals(bgColor)
                && buttonSendCredit.getCssValue("color").equals(textColor);
    }

    public void clickCheckBoxCreditObligationsCredit() {
        waitElement(checkBoxCreditObligationsCredit);
        checkBoxCreditObligationsCredit.click();
    }

    public Boolean textErrorAmountCreditIsDisplayed() {
        waitElement(textValidateAmountCredit);
        return textValidateAmountCredit.isDisplayed();
    }

    public Boolean textErrorPeriodMonthCreditIsDisplayed() {
        waitElement(textValidatePeriodMonthCredit);
        return textValidatePeriodMonthCredit.isDisplayed();
    }

    public Boolean textEmployerIdentificationNumberErrorIsDisplayed(String employerIdentificationNumber) {
        if (employerIdentificationNumber.length() < 10) {
            return textValidateMessage.isDisplayed();
        } else if (employerIdentificationNumber.length() == 11) {
            return textValidateLongMessage.isDisplayed();
        } else if (employerIdentificationNumber.startsWith("00")) {
            return textValidateTwoZeroMessage.isDisplayed();
        } else if (employerIdentificationNumber.isEmpty()) {
            return textValidateEmptyMessage.isDisplayed();
        } else if (employerIdentificationNumber.length() == 10) {
            return true;
        } else if (employerIdentificationNumber.length() == 12) {
            return true;
        } else {
            return textValidateSymbolMessage.isDisplayed();
        }
    }

    public Boolean textTotalDebtLoadErrorIsDisplayed(String totalDebtLoad) {
        if (totalDebtLoad.length() < 4) {
            return textValidateMessage.isDisplayed();
        } else if (totalDebtLoad.startsWith("0")) {
            return textValidateFirstZeroMessage.isDisplayed();
        } else if (totalDebtLoad.isEmpty()) {
            return textValidateEmptyMessage.isDisplayed();
        } else {
            return true;
        }
    }


    public void enterSummaCredit(String amountCredit) {
        waitElement(inputSummaCredit);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.click(inputSummaCredit)
                .sendKeys(amountCredit)
                .perform();
    }

    public void enterPeriodMonthsCredit(String periodMonths) {
        waitElement(inputPeriodMonthsCredit);
        inputPeriodMonthsCredit.sendKeys(periodMonths);
    }

    public void enterIdentificationNumberCredit(String identificationNumber) {
        waitElement(inputIdentificationNumberCredit);
        inputIdentificationNumberCredit.sendKeys(identificationNumber);
    }

    public void enterMonthlyExpenditureCredit(String monthlyExpenditure) {
        waitElement(inputMonthlyExpenditureCredit);
        inputMonthlyExpenditureCredit.sendKeys(monthlyExpenditure);
    }

    public void enterMonthlyIncomeCredit(String monthlyIncome) {
        waitElement(inputMonthlyIncomeCredit);
        inputMonthlyIncomeCredit.sendKeys(monthlyIncome);
    }

}
