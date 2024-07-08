package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

import java.util.List;

public class ApplicationsPage extends BasePage {

    private WebDriver driver;

//    By creditApplication = By.xpath("//li[@class='_wrapper_1df24_1']");


    @FindBy(xpath = "//li[@class='_wrapper_1df24_1']")
    private List<WebElement> creditApplicationsList;
    @FindBy(xpath = "//p[@data-testid='test-data-status']")
    private WebElement statusOfCreditApplication;

    @FindBy(xpath = "//span[@class='_count_1lpgb_15']")
    private WebElement amountOfCreditApplications;

    @FindBy(xpath = "//h2[@class='_text_h2_xv9cv_5 _name_1df24_62']")
    private WebElement nameCreditProduct;

    @FindBy(xpath = "//h1[@class='_text_h1_xv9cv_1 _amount_1df24_46']")
    private WebElement sumOfCredit;

    @FindBy(xpath = "//h2[@data-testid='test-data-period']")
    private WebElement dateOfCredit;

    @FindBy(xpath = "//h2[@data-testid='test-data-interestRate']")
    private WebElement percentOfCredit;

    @FindBy(xpath = "//h2[@data-testid='test-data-date']")
    private WebElement dateOfCreateApplication;
    @FindBy(xpath = "//p[@class='_text_medium_xv9cv_27 _title_1iazs_136'][contains(text(), 'На данный момент у вас отсутствуют кредитные заявки')]")
    private WebElement messageNoApplications;


    public boolean isApplicationsDisplayed() {
        return !creditApplicationsList.isEmpty();
    }

    public boolean isNoApplications() {
        return creditApplicationsList.isEmpty() && messageNoApplications.isDisplayed();
    }

    public boolean isStatusOfCreditApplicationDisplayed() {
        return statusOfCreditApplication.isDisplayed();
    }

    public boolean isAmountOfCreditApplicationsDisplayed() {
        Waiters.waitElement(amountOfCreditApplications);
        return amountOfCreditApplications.isDisplayed();
    }

    public boolean isNameCreditProductDisplayed() {
        return nameCreditProduct.isDisplayed();
    }

    public boolean isSumOfCreditDisplayed() {
        return sumOfCredit.isDisplayed();
    }

    public boolean isDateOfCreditDisplayed() {
        return dateOfCredit.isDisplayed();
    }

    public boolean isPercentOfCredit() {
        return percentOfCredit.isDisplayed();
    }

    public boolean isDateOfCreateApplication() {
        return dateOfCreateApplication.isDisplayed();
    }
}
