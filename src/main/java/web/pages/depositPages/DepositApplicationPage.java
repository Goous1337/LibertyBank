package web.pages.depositPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;
import static web.helpers.Waiters.waitElementWithColor;

public class DepositApplicationPage extends BasePage {

    //Заполнение полей и чекбоксы депозитных заявок
    @FindBy(xpath = "//input[@id='amount']")
    private WebElement sumOfDepositField;
    @FindBy(xpath = "//input[@id='term']")
    private WebElement termOfDepositField;
    @FindBy(xpath = "//label[@for = 'checkboxConditions']")
    private WebElement checkBoxKnowConditions;
    @FindBy(xpath = "//label[@for = 'checkboxProlongation']")
    private WebElement checkBoxAgreeConditions;
    @FindBy(xpath = "//button[text() = 'Отправить заявку']")
    private WebElement sendForm;
    @FindBy(xpath = "//p[contains(text(), 'Введённая сумма меньше допустимой')]")
    private WebElement textValidateLessAmountDeposit;
    @FindBy(xpath = "//p[contains(text(), 'Введённая сумма больше допустимой')]")
    private WebElement textValidateMoreAmountDeposit;
    @FindBy(xpath = "//p[contains(text(), 'Введённый срок меньше допустимого')]")
    private WebElement textValidateLessPeriodMonthDeposit;
    @FindBy(xpath = "//p[contains(text(), 'Введённый срок больше допустимого')]")
    private WebElement textValidateMorePeriodMonthDeposit;
    @FindBy(xpath = "//p[contains(text(), 'Пожалуйста, вводите цифры')]")
    private WebElement textValidateSymbolMessage;
    @FindBy(xpath = "//p[contains(text(), 'Поле обязательно для заполнения')]")
    private WebElement textValidateEmptyMessage;

    public void fillSumOfDepositField(String amountDeposit) {
        waitElement(sumOfDepositField);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.click(sumOfDepositField)
                .sendKeys(amountDeposit)
                .perform();
    }
    public void clickSumOfDepositField() {
        sumOfDepositField.click();
    }

    public void clickTermOfDepositField() {
        termOfDepositField.click();
    }

    public void fillTermOfDepositField(String periodMonths) {
        waitElement(termOfDepositField);
        termOfDepositField.sendKeys(periodMonths);
    }

    public void clickCheckBoxKnowConditions() {
        checkBoxKnowConditions.click();
    }

    public void clickSendForm() {
        sendForm.click();
    }

    public boolean checkButtonCondition(String bgColor, String textColor, boolean isEnabled) {
        waitElementWithColor(sendForm, bgColor);
        return sendForm.isEnabled() == isEnabled
                && sendForm.getCssValue("background-color").equals(bgColor)
                && sendForm.getCssValue("color").equals(textColor);
    }
    public enum ErrorMessage {
        INPUT_DIGITS("Пожалуйста, вводите цифры"),
        LITTLE_SUM( "Введённая сумма меньше допустимой"),
        BIG_SUM( "Введённая сумма больше допустимой"),
        LITTLE_TERM( "Введённый срок меньше допустимого"),
        BIG_TERM( "Введённый срок больше допустимого"),
        FILL_NECESSARY( "Поле обязательно для заполнения");

        private final String message;

        ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public Boolean textErrorLessAmountDepositIsDisplayed() {
        waitElement(textValidateLessAmountDeposit);
        return textValidateLessAmountDeposit.isDisplayed();
    }

    public Boolean textErrorMoreAmountDepositIsDisplayed() {
        waitElement(textValidateMoreAmountDeposit);
        return textValidateMoreAmountDeposit.isDisplayed();
    }

    public boolean isDepositSumLessThanMinAllowed(double depositSum) {
        double minAllowedAmount = 500000;
        return depositSum < minAllowedAmount;
    }

    public boolean isDepositSumMoreThanMaxAllowed(double depositSum) {
        double maxAllowedAmount = 1000000;
        return depositSum > maxAllowedAmount;
    }

    public Boolean textErrorLessTermDepositIsDisplayed() {
        waitElement(textValidateLessPeriodMonthDeposit);
        return textValidateLessPeriodMonthDeposit.isDisplayed();
    }

    public Boolean textErrorMoreTermDepositIsDisplayed() {
        waitElement(textValidateMorePeriodMonthDeposit);
        return textValidateMorePeriodMonthDeposit.isDisplayed();
    }
    public boolean isDepositTermLessThanMinAllowed(double depositTerm) {
        double minAllowedTerm = 6;
        return depositTerm < minAllowedTerm;
    }

    public boolean isDepositTermMoreThanMaxAllowed(double depositTerm) {
        double maxAllowedTerm = 36;
        return depositTerm > maxAllowedTerm;
    }
    public Boolean textSymbolAmountTermDepositIsDisplayed(String depositSum, String depositTerm) {
        waitElement(textValidateSymbolMessage);
        return textValidateSymbolMessage.isDisplayed();
    }
    public Boolean textFillNecessaryAmountTermDepositIsDisplayed(String depositSum, String depositTerm) {
        waitElement(textValidateEmptyMessage);
        return textValidateEmptyMessage.isDisplayed();
    }
    public boolean isDepositSumLessThanMinAllowedLibertyPlusExpress(double depositSum) {
        double minAllowedAmount = 100000;
        return depositSum < minAllowedAmount;
    }

    public boolean isDepositSumMoreThanMaxAllowedPlusExpress(double depositSum) {
        double maxAllowedAmount = 10000000;
        return depositSum > maxAllowedAmount;
    }

    public boolean isDepositTermLessThanMinAllowedPlusExpress(double depositTerm) {
        double minAllowedTerm = 1;
        return depositTerm < minAllowedTerm;
    }

    public boolean isDepositTermMoreThanMaxAllowedPlusExpress(double depositTerm) {
        double maxAllowedTerm = 36;
        return depositTerm > maxAllowedTerm;
    }
    public boolean isDepositSumLessThanMinAllowedLibertyChild(double depositSum) {
        double minAllowedAmount = 1000;
        return depositSum < minAllowedAmount;
    }

    public boolean isDepositSumMoreThanMaxAllowedLibertyChild(double depositSum) {
        double maxAllowedAmount = 100000;
        return depositSum > maxAllowedAmount;
    }

    public boolean isDepositSumLessThanMinAllowedLibertyBase(double depositSum) {
        double minAllowedAmount = 1000;
        return depositSum < minAllowedAmount;
    }

    public boolean isDepositSumMoreThanMaxAllowedLibertyBase(double depositSum) {
        double maxAllowedAmount = 500000;
        return depositSum > maxAllowedAmount;
    }

    public boolean isDepositTermLessThanMinAllowedLibertyBase(double depositTerm) {
        double minAllowedTerm = 3;
        return depositTerm < minAllowedTerm;
    }

    public boolean isDepositTermMoreThanMaxAllowedLibertyBase(double depositTerm) {
        double maxAllowedTerm = 36;
        return depositTerm > maxAllowedTerm;
    }

    public boolean isDepositSumLessThanMinAllowedLibertyPremium(double depositSum) {
        double minAllowedAmount = 1000000;
        return depositSum < minAllowedAmount;
    }

    public boolean isDepositSumMoreThanMaxAllowedLibertyPremium(double depositSum) {
        double maxAllowedAmount = 10000000;
        return depositSum > maxAllowedAmount;
    }
    public boolean isDepositSumLessThanMinAllowedLibertyCalculated(double depositSum) {
        double minAllowedAmount = 1000;
        return depositSum < minAllowedAmount;
    }

    public boolean isDepositSumMoreThanMaxAllowedLibertyCalculated(double depositSum) {
        double maxAllowedAmount = 10000000;
        return depositSum > maxAllowedAmount;
    }
    public boolean isDepositTermLessThanMinAllowedCalculated(double depositTerm) {
        double minAllowedTerm = 1;
        return depositTerm < minAllowedTerm;
    }

    public boolean isDepositTermMoreThanMaxAllowedCalculated(double depositTerm) {
        double maxAllowedTerm = 60;
        return depositTerm > maxAllowedTerm;
    }
    public boolean isDepositSumLessThanMinAllowedLibertyBaseExpress(double depositSum) {
        double minAllowedAmount = 1;
        return depositSum < minAllowedAmount;
    }

    public boolean isDepositSumMoreThanMaxAllowedLibertyBaseExpress(double depositSum) {
        double maxAllowedAmount = 500000;
        return depositSum > maxAllowedAmount;
    }
    public boolean isDepositSumLessThanMinAllowedLibertyCurrency(double depositSum) {
        double minAllowedAmount = 3000;
        return depositSum < minAllowedAmount;
    }

    public boolean isDepositSumMoreThanMaxAllowedLibertyCurrency(double depositSum) {
        double maxAllowedAmount = 1000000;
        return depositSum > maxAllowedAmount;
    }
}
