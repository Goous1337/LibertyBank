package web.pages;

import api.model.webAndApi.CreditProductService;
import api.model.webAndApi.credit.CreditDetails;
import api.model.webAndApi.credit.CreditProduct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import lombok.*;

import java.util.List;

public class CreditInfoPage extends BasePage {

    public CreditProductService creditProductService;

    public CreditInfoPage() {
        creditProductService = new CreditProductService();
    }

    @FindBy(xpath = "//a[@href='/credits']")
    private WebElement creditButton;
    @FindBy(xpath = "//a[@data-testid='nav-link-0']")
    private WebElement myCreditButton;
    @FindBy(xpath = "//a[@data-testid='nav-link-1']")
    private WebElement creditProductsBankButton;
    @FindBy(xpath = "//a[@data-testid='nav-link-2']")
    private WebElement submittedCreditАpplicationsButton;
    @FindBy(xpath = "//ul//li[1]//div/h2")
    private WebElement sumCreditText;
    @FindBy(xpath = "//ul//li[1]//div/h3")
    private WebElement nameCreditText;
    @FindBy(xpath = "//ul//li[1]/div/p[@data-testid='creditTerm']")
    private WebElement termCreditText;
    @FindBy(xpath = "//ul//li[1]//div/a")
    private WebElement showMoreAboutCreditButton;
    @FindBy(xpath = "//div/span[@data-testid='title']")
    private WebElement nameTitleCreditText;
    @FindBy(xpath = "//div/p[@data-testid='creditAccountNumber']")
    private WebElement accountNumberCreditText;
    @FindBy(xpath = "//div/span[@data-testid='title']/following-sibling::p")
    private WebElement statusCreditText;
    @FindBy(xpath = "//h2[@data-testid='nextPayment']")
    private WebElement nextPaymentCreditText;
    @FindBy(xpath = "//h2[@data-testid='paymentDate']")
    private WebElement paymentDateCreditText;
    @FindBy(xpath = "//h2[@data-testid='creditLimit']")
    private WebElement limitCreditText;
    @FindBy(xpath = "//span[@data-testid='repay']")
    private WebElement repayCreditText;
    @FindBy(xpath = "//h2[@data-testid='periodMonths']")
    private WebElement periodMonthsCreditText;
    @FindBy(xpath = "//h2[@data-testid='interestRate']")
    private WebElement interestRateCreditText;
    @FindBy(xpath = "//p[@data-testid='creditAccountNumber']/following-sibling::button")
    private WebElement copyAccountNumberCreditButton;
    @FindBy(xpath = "//button[text()='Погасить']")
    private WebElement repayCreditButton;
    @FindBy(xpath = "//button[text()='Оплатить']")
    private WebElement payCreditButton;
    @FindBy(xpath = "//div[@data-testid='dots-button']")
    private WebElement dotsButton;
    @FindBy(xpath = "//div[@data-testid='dots-button']/div/button[text()='Реквизиты']")
    private WebElement requisitesButton;
    @FindBy(xpath = "//div[@data-testid='dots-button']/div/button[text()='График платежей']")
    private WebElement paymentScheduleButton;
    @FindBy(xpath = "//*[text()='Скопировано']")
    private WebElement outputCopiedText;
    /*Кредитные продукты*/
    @FindBy(xpath = "//h1[text()='Liberty Наличными']")
    private WebElement nameCreditProductPageText;
    @FindBy(xpath = "//li[1]/div[3]/button/span")
    private WebElement buttonShowMoreLibertyCash;


    public String getActualTitleCredit() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public boolean isMyCreditButtonDisplayed() {
        return myCreditButton.isDisplayed();
    }

    public boolean isCreditProductsBankButtonDisplayed() {
        return creditProductsBankButton.isDisplayed();
    }

    public boolean isSubmittedCreditАpplicationsDisplayed() {
        return submittedCreditАpplicationsButton.isDisplayed();
    }

    public boolean isMyCreditSumTextDisplayed() {
        return sumCreditText.isDisplayed();
    }

    public boolean isNameCreditText() {
        return nameCreditText.isDisplayed();
    }

    public boolean isTermCreditText() {
        return termCreditText.isDisplayed();
    }

    public boolean isShowMoreAboutCreditButton() {
        return showMoreAboutCreditButton.isDisplayed();
    }

    public void clickCreditButton() {
        creditButton.click();
    }

    public void clickMyCreditButton() {
        myCreditButton.click();
    }

    public void clickCreditProductsBankButton() {
        creditProductsBankButton.click();
    }

    public void clickSubmittedCreditАpplicationsButton() {
        submittedCreditАpplicationsButton.click();
    }

    public void clickShowMoreMyCreditButton() {
        showMoreAboutCreditButton.click();
    }

    public boolean nameTitleCreditTextDisplayed() {
        return nameTitleCreditText.isDisplayed();
    }

    public boolean accountNumberCreditTextDisplayed() {
        return accountNumberCreditText.isDisplayed();
    }

    public boolean statusCreditTextDisplayed() {
        return statusCreditText.isDisplayed();
    }

    public boolean nextPaymentCreditTextDisplayed() {
        return nextPaymentCreditText.isDisplayed();
    }

    public boolean paymentDateCreditTextDisplayed() {
        return paymentDateCreditText.isDisplayed();
    }

    public boolean limitCreditTextDisplayed() {
        return limitCreditText.isDisplayed();
    }

    public boolean repayCreditTextDisplayed() {
        return repayCreditText.isDisplayed();
    }

    public boolean periodMonthsCreditTextDisplayed() {
        return periodMonthsCreditText.isDisplayed();
    }

    public boolean interestRateCreditTextDisplayed() {
        return interestRateCreditText.isDisplayed();
    }

    public boolean outputCopiedTextDisplayed() {
        return outputCopiedText.isDisplayed();
    }

    public void clickCopyAccountNumberCreditButton() {
        copyAccountNumberCreditButton.click();
    }

    public void clickRepayCreditButton() {
        repayCreditButton.click();
    }

    public void clickPayCreditButton() {
        payCreditButton.click();
    }

    public void clickDotsButton() {
        dotsButton.click();
    }

    public boolean requisitesButtonDisplayed() {
        return requisitesButton.isDisplayed();
    }

    public boolean paymentScheduleButtonDisplayed() {
        return paymentScheduleButton.isDisplayed();
    }

    /*Подробная информация о кредитных продуктах банка*/

    public String getNameCreditProductPageText() {
        return nameCreditProductPageText.getText();
    }

    public String getAllProdutsBank() {
        creditProductService.getUsersFromPage();
        String name = creditProductService.creditProduct.getNameProduct();
        return name;
    }

    public void clickButtonShowMoreLibertyCash () {
        buttonShowMoreLibertyCash.click();
    }

}
