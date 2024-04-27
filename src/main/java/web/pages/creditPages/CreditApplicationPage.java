package web.pages.creditPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;

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
    @FindBy(xpath = "//input[@data-testid=' input-text-monthlyIncome']")
    private WebElement inputMonthlyIncomeCredit;
    @FindBy(xpath = "//p[contains(text(), 'Введённая')]")
    private WebElement textValidateAmountCredit;
    @FindBy(xpath = "//p[contains(text(), 'Введённый')]")
    private WebElement textValidatePeriodMonthCredit;
    @FindBy(xpath = "//p[contains(text(), 'Недостаточно символов')]")
    private WebElement textValidateMessage;
    @FindBy(xpath = "//input[@type='checkbox']")//input[@type='checkbox']/following-sibling::label
    private WebElement checkBoxCreditObligationsCredit;
    @FindBy(xpath = "//button[text()='Отправить заявку']")
    private WebElement buttonSendCredit;

    public void clickSendCredit() {
        waitElement(buttonSendCredit);
        buttonSendCredit.click();
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
