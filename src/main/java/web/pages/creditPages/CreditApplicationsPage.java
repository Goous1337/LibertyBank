package web.pages.creditPages;

import api.model.webAndApi.ApplicationsService;
import api.model.webAndApi.credit.CreditApplications;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.constans.credit.creditEnums.CreditStatusEnum;
import web.helpers.Waiters;
import web.pages.BasePage;

import java.util.ArrayList;

import java.util.List;

import static web.helpers.Converter.*;

@Getter
@Setter
public class CreditApplicationsPage extends BasePage {

    private WebDriver driver;
    public ApplicationsService applicationsService;
    private List<CreditApplications> listXpathApp;
    private List<CreditApplications> listApplicationsFromBackend;
    private String nameApplicationFromWeb;
    private Double sumOfCreditFromWeb;
    private Integer dateOfCreditFromWeb;
    private String statusApplicationFromWeb;
    private Double percentOfCreditFromWeb;
    private String dateOfCreateApplicationFromWeb;
    private String nameApplicationFromBackend;
    private String  statusApplicationFromBackEnd;
    private Double sumOfCreditFromBackEnd;
    private Integer dateOfCreditFromBackEnd;
    private Double percentOfCreditFromBackEnd;
    private String dateOfCreateApplicationFromBackEnd;

    public CreditApplicationsPage() {
        applicationsService = new ApplicationsService();
    }

    @FindBy(xpath = "//div[@class = '_container_h0vf9_1']/child::*[1]")
    private List<WebElement> creditApplicationsList;
    @FindBy(xpath = "//div[@class = '_container_h0vf9_1']/child::*[1]//p[@data-testid='test-data-status']")
    private WebElement statusOfCreditApplication;

    @FindBy(xpath = "//span[@class='_count_1lpgb_15']")
    private WebElement amountOfCreditApplications;

  //  @FindBy(css = "$$([data-testid='icon-user-image)")


    @FindBy(xpath = "//div[@class = '_container_h0vf9_1']/child::*[1]//h2[@class='_text_h2_xv9cv_5 _name_1df24_62']")
    private WebElement nameCreditProduct;

    @FindBy(xpath = "//div[@class = '_container_h0vf9_1']/child::*[1]//h1[@class='_text_h1_xv9cv_1 _amount_1df24_46']")
    private WebElement sumOfCredit;

    @FindBy(xpath = "//div[@class = '_container_h0vf9_1']/child::*[1]//h2[@data-testid='test-data-period']")
    private WebElement periodOfCredit;

    @FindBy(xpath = "//div[@class = '_container_h0vf9_1']/child::*[1]//h2[@data-testid='test-data-interestRate']")
    private WebElement percentOfCredit;

    @FindBy(xpath = "//div[@class = '_container_h0vf9_1']/child::*[1]//h2[@data-testid='test-data-date']")
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
        return periodOfCredit.isDisplayed();
    }

    public boolean isPercentOfCredit() {
        return percentOfCredit.isDisplayed();
    }

    public boolean isDateOfCreateApplication() {
        return dateOfCreateApplication.isDisplayed();
    }


    public List<CreditApplications> addCreditApplications() {
        listXpathApp = new ArrayList<>();
        listXpathApp.add(new CreditApplications(
                nameCreditProduct.getText(),
                convertToDouble(sumOfCredit.getText()),
                changePeriodOfCredit(periodOfCredit.getText()),
                convertToDouble(percentOfCredit.getText()),
                checkCreditStatusApplication(statusOfCreditApplication),
                changeDate(dateOfCreateApplication.getText())));
        return listXpathApp;
    }

    private static String checkCreditStatusApplication(WebElement statusOfCreditApplication) {
        String status;
        if (CreditStatusEnum.CREDIT_STATUS_PENDING.toString().equals(statusOfCreditApplication.getText())) {
           return status = "PENDING";
        } else {
            return statusOfCreditApplication.getText();
        }
    }


    public CreditApplications getCreditApplicationsWeb() {
            for (CreditApplications creditApplicationsWeb : addCreditApplications()) {
                nameApplicationFromWeb = creditApplicationsWeb.getName();
                sumOfCreditFromWeb = creditApplicationsWeb.getAmount();
                dateOfCreditFromWeb = creditApplicationsWeb.getPeriodMonths();
                statusApplicationFromWeb = creditApplicationsWeb.getStatus();
                percentOfCreditFromWeb = creditApplicationsWeb.getInterestRate();
                dateOfCreateApplicationFromWeb = creditApplicationsWeb.getCreationDate();
            }
            return new CreditApplications(nameApplicationFromWeb, sumOfCreditFromWeb, dateOfCreditFromWeb, percentOfCreditFromWeb, statusApplicationFromWeb, dateOfCreateApplicationFromWeb);
    }



    public CreditApplications getCreditApplicationsBackend() {
        applicationsService.getCreditApplications();
        listXpathApp = addCreditApplications();
        listApplicationsFromBackend = applicationsService.getCreditApplicationsList();
        for (CreditApplications creditApplicationsBackEnd : listApplicationsFromBackend) {
            for (CreditApplications creditApplicationsWeb : listXpathApp) {
                if (creditApplicationsBackEnd.getName().equals(creditApplicationsWeb.getName())) {
                    nameApplicationFromBackend = creditApplicationsBackEnd.getName();
                }
                if (creditApplicationsBackEnd.getStatus().equals(creditApplicationsWeb.getStatus())) {
                    statusApplicationFromBackEnd = creditApplicationsBackEnd.getStatus();
                }
                if (creditApplicationsBackEnd.getAmount().equals(creditApplicationsWeb.getAmount())) {
                    sumOfCreditFromBackEnd = creditApplicationsBackEnd.getAmount();
                }
                if (creditApplicationsBackEnd.getPeriodMonths().equals(creditApplicationsWeb.getPeriodMonths())) {
                    dateOfCreditFromBackEnd = creditApplicationsBackEnd.getPeriodMonths();
                }
                if (creditApplicationsBackEnd.getInterestRate().equals(creditApplicationsWeb.getInterestRate())) {
                    percentOfCreditFromBackEnd = creditApplicationsBackEnd.getInterestRate();
                }
                if (creditApplicationsBackEnd.getCreationDate().equals(creditApplicationsWeb.getCreationDate())) {
                    dateOfCreateApplicationFromBackEnd = creditApplicationsBackEnd.getCreationDate();
                }
            }
        }
        return new CreditApplications(nameApplicationFromBackend, sumOfCreditFromBackEnd, dateOfCreditFromBackEnd, percentOfCreditFromBackEnd, statusApplicationFromBackEnd, dateOfCreateApplicationFromBackEnd);
    }
}


