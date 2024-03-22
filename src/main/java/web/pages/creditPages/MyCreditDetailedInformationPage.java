package web.pages.creditPages;

import api.model.webAndApi.CreditProductService;
import api.model.webAndApi.credit.MyCreditMoreInformation;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static web.helpers.Converter.*;

@Getter
@Setter
public class MyCreditDetailedInformationPage extends BasePage {
    public CreditProductService creditProductService;
    private String nameBackEnd;
    private Integer creditLimitBackEnd;
    private Integer periodMonthsBackEnd;
    private Integer interestRateBackEnd;
    private Integer generalDebtBackEnd;
    private java.sql.Date paymentDateBackEnd;
    private String creditAccountNumberBackEnd;
    private String nameFromWeb;
    private Integer creditLimitFromWeb;
    private Integer periodMonthsFromWeb;
    private Integer interestRateFromWeb;
    private Integer generalDebtFromWeb;
    private java.sql.Date paymentDateFromWeb;
    private String creditAccountNumberFromWeb;
    private List<MyCreditMoreInformation> listXpath;
    private List<MyCreditMoreInformation> listFromBackEnd;
    private MyCreditMoreInformation myCreditMoreInformation;

    public MyCreditDetailedInformationPage() {
        creditProductService = new CreditProductService();
    }

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
    private WebElement creditLimitText;
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

    public List<MyCreditMoreInformation> addMoreMyCreditInformationXpathToList() {
        listXpath = new ArrayList<>();
        listXpath.add(new MyCreditMoreInformation(
                nameTitleCreditText.getText(),
                convertStringToInteger(creditLimitText.getText()),
                convertStringToInteger(periodMonthsCreditText.getText()),
                convertStringToInteger(interestRateCreditText.getText()),
                convertStringToInteger(repayCreditText.getText()),
                parseDate(paymentDateCreditText.getText()),
                convertStringToString(accountNumberCreditText.getText())
        ));
        return listXpath;
    }

    public MyCreditMoreInformation getMoreMyCreditInformationObjectFromWeb() {
        for (MyCreditMoreInformation myCreditMoreInformation : addMoreMyCreditInformationXpathToList()) {
            nameFromWeb = myCreditMoreInformation.getName();
            creditLimitFromWeb = myCreditMoreInformation.getCreditLimit();
            periodMonthsFromWeb = myCreditMoreInformation.getPeriodMonths();
            interestRateFromWeb = myCreditMoreInformation.getInterestRate();
            generalDebtFromWeb = myCreditMoreInformation.getGeneralDebt();
            paymentDateFromWeb = myCreditMoreInformation.getPaymentDate();
            creditAccountNumberFromWeb = myCreditMoreInformation.getCreditAccountNumber();
        }
        return new MyCreditMoreInformation(
                nameFromWeb,
                creditLimitFromWeb,
                periodMonthsFromWeb,
                interestRateFromWeb,
                generalDebtFromWeb,
                paymentDateFromWeb,
                creditAccountNumberFromWeb
        );
    }

    public MyCreditMoreInformation getMoreMyCreditInformationObjectFromBackEnd() {
        creditProductService.getMoreInformationAboutMyCredit();
        listXpath = addMoreMyCreditInformationXpathToList();
        listFromBackEnd = creditProductService.getMyCreditMoreInformationList();
        for (MyCreditMoreInformation myCreditMoreInformationBackEnd : listFromBackEnd) {
            for (MyCreditMoreInformation myCreditMoreInformationWeb : listXpath) {
                if (myCreditMoreInformationBackEnd.getName().equals(myCreditMoreInformationWeb.getName())) {
                    nameBackEnd = myCreditMoreInformationBackEnd.getName();
                }
                if (myCreditMoreInformationBackEnd.getCreditLimit().equals(myCreditMoreInformationWeb.getCreditLimit())) {
                    creditLimitBackEnd = myCreditMoreInformationBackEnd.getCreditLimit();
                }
                if (myCreditMoreInformationBackEnd.getPeriodMonths().equals(myCreditMoreInformationWeb.getPeriodMonths())) {
                    periodMonthsBackEnd = myCreditMoreInformationBackEnd.getPeriodMonths();
                }
                if (myCreditMoreInformationBackEnd.getInterestRate().equals(myCreditMoreInformationWeb.getInterestRate())) {
                    interestRateBackEnd = myCreditMoreInformationBackEnd.getInterestRate();
                }
                if (myCreditMoreInformationBackEnd.getGeneralDebt().equals(myCreditMoreInformationWeb.getGeneralDebt())) {
                    generalDebtFromWeb = myCreditMoreInformationBackEnd.getGeneralDebt();
                }
                paymentDateFromWeb = parseDate(myCreditMoreInformationBackEnd.getStringDate(myCreditMoreInformationBackEnd.getPaymentDate()));
                if (myCreditMoreInformationBackEnd.getCreditAccountNumber().equals(
                        myCreditMoreInformationWeb.getCreditAccountNumber())) {
                    creditAccountNumberFromWeb = myCreditMoreInformationBackEnd.getCreditAccountNumber();
                }

            }
        }
        return new MyCreditMoreInformation(
                nameBackEnd,
                creditLimitBackEnd,
                periodMonthsBackEnd,
                interestRateBackEnd,
                generalDebtFromWeb,
                paymentDateFromWeb,
                creditAccountNumberFromWeb);
    }

    /*Раздел подробной информации 'Мои кредиты'*/
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
        return creditLimitText.isDisplayed();
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

}
