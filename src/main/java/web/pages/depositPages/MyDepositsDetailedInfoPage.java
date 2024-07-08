package web.pages.depositPages;

import api.model.webAndApi.DepositProductService;
import api.model.webAndApi.deposit.MyDepositMoreInfo;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import java.util.ArrayList;
import java.util.List;

import static web.helpers.Converter.*;

@Getter
@Setter
public class MyDepositsDetailedInfoPage extends BasePage {
    public DepositProductService depositProductService;

    private String nameBackEnd;
    private String depAccountNumberBackEnd;
    private java.sql.Date openDateBackEnd;
    private java.sql.Date closeDateBackEnd;
    private Integer periodMonthsBackEnd;
    private Double interestRateBackEnd;
    private Double initialAmountBackEnd;
    private String currencyCodeBackEnd;
    private String nameWeb;
    private String depAccountNumberWeb;
    private java.sql.Date openDateWeb;
    private java.sql.Date closeDateWeb;
    private Integer periodMonthsWeb;
    private Double interestRateWeb;
    private Double initialAmountWeb;
    private String currencyCodeWeb;
    private List<MyDepositMoreInfo> listXpath;
    private List<MyDepositMoreInfo> listFromBackEnd;

    public MyDepositsDetailedInfoPage() {
        depositProductService = new DepositProductService();

    }

    @FindBy(xpath = "//h2")
    private WebElement depositProductNameText;
    @FindBy(xpath = "//div/h3[@data-testid='depAccountNumber']")
    private WebElement depAccountNumberText;
    @FindBy(xpath = "//h2/following-sibling::div/p[1]")
    private WebElement statusDepositText;
    @FindBy(xpath = "//div/p[contains(text(), 'Дата открытия')]/following-sibling::p")
    private WebElement openDateDepositText;
    @FindBy(xpath = "//div/p[contains(text(), 'Дата закрытия')]/following-sibling::p")
    private WebElement closeDateDepositText;
    @FindBy(xpath = "//div/p[contains(text(), 'Сумма депозита')]/following-sibling::p")
    private WebElement initialDepositAmountText;
    @FindBy(xpath = "//div/p[contains(text(), 'Итоговая сумма')]/following-sibling::p")
    private WebElement finalDepositAmountText;
    @FindBy(xpath = "//p[@data-testid='periodMonths']")
    private WebElement periodMonthsDepositText;
    @FindBy(xpath = "//div/p[contains(text(), 'Ставка')]/following-sibling::p")
    private WebElement interestRateText;
    @FindBy(xpath = "//h3[@data-testid='depAccountNumber']/following-sibling::button")
    private WebElement copyDepositAccountNumberButton;
    @FindBy(xpath = "//*[text()='Скопировано']")
    private WebElement outputCopiedText;
    @FindBy(xpath = "//button[text()='Отозвать']")
    private WebElement revokeDepositButton;
    @FindBy(xpath = "//button[text()='Пополнить']")
    private WebElement payDepositButton;
    @FindBy(xpath = "//div[@data-testid='dots-button']")
    private WebElement dotsButton;
    @FindBy(xpath = "//div[@data-testid='dots-button']/div/button[text()='Реквизиты']")
    private WebElement requisitesButton;
    @FindBy(xpath = "//div[@data-testid='dots-button']/div/button[text()='График начисления процентов']")
    private WebElement paymentScheduleButton;
    @FindBy(xpath = "//div[@data-testid='dots-button']/div/button[text()='Информация о пополнении депозита']")
    private WebElement paymentInfoButton;
    @FindBy(xpath = "//div[@data-testid='dots-button']/div/button[text()='Пролонгация депозита']")
    private WebElement extendDepositButton;

    public List<MyDepositMoreInfo> addMyDepositDetailedInfoXpathToList() {
        listXpath = new ArrayList<>();
        listXpath.add(new MyDepositMoreInfo(
                depositProductNameText.getText(),
                convertStringToString(depAccountNumberText.getText()),
                parseDate(openDateDepositText.getText()),
                parseDate(closeDateDepositText.getText()),
                convertStringToInteger(periodMonthsDepositText.getText()),
                convertValueToDouble(interestRateText.getText())
        ));
        return listXpath;
    }


    public MyDepositMoreInfo getMyDepositProductObjectFromWeb() {
        for (MyDepositMoreInfo myDepositMoreInfo : addMyDepositDetailedInfoXpathToList()) {
            nameWeb = myDepositMoreInfo.getName();
            depAccountNumberWeb = myDepositMoreInfo.getDepAccountNumber();
            openDateWeb = myDepositMoreInfo.getOpenDate();
            closeDateWeb = myDepositMoreInfo.getCloseDate();
            periodMonthsWeb = myDepositMoreInfo.getPeriodMonths();
            interestRateWeb = myDepositMoreInfo.getInterestRate();
        }
        return new MyDepositMoreInfo(
                nameWeb,
                depAccountNumberWeb,
                openDateWeb,
                closeDateWeb,
                periodMonthsWeb,
                interestRateWeb
        );
    }

    public MyDepositMoreInfo getMyDepositProductObjectFromBackEnd() {
        depositProductService.getMoreInfoAboutMyDeposit(1559);
        listXpath = addMyDepositDetailedInfoXpathToList();
        listFromBackEnd = depositProductService.getMyDepositMoreInfoList();
        for (MyDepositMoreInfo myDepositMoreInfoBackEnd : listFromBackEnd) {
            for (MyDepositMoreInfo myDepositMoreInfoWeb : listXpath) {
                if (myDepositMoreInfoBackEnd.getName().equals(myDepositMoreInfoWeb.getName())) {
                    nameBackEnd = myDepositMoreInfoBackEnd.getName();
                }
                if (myDepositMoreInfoBackEnd.getDepAccountNumber().equals(myDepositMoreInfoWeb.getDepAccountNumber())) {
                    depAccountNumberBackEnd = myDepositMoreInfoBackEnd.getDepAccountNumber();
                }
                openDateBackEnd = parseDate(myDepositMoreInfoBackEnd.getStringDate(myDepositMoreInfoBackEnd.getOpenDate()));
                closeDateBackEnd = parseDate(myDepositMoreInfoBackEnd.getStringDate(myDepositMoreInfoBackEnd.getCloseDate()));
                if (myDepositMoreInfoBackEnd.getPeriodMonths().equals(myDepositMoreInfoWeb.getPeriodMonths())) {
                    periodMonthsBackEnd = myDepositMoreInfoBackEnd.getPeriodMonths();
                }
                if (myDepositMoreInfoBackEnd.getInterestRate().equals(myDepositMoreInfoWeb.getInterestRate())) {
                    interestRateBackEnd = myDepositMoreInfoBackEnd.getInterestRate();
                }

            }
        }
        return new MyDepositMoreInfo(
                nameBackEnd,
                depAccountNumberBackEnd,
                openDateBackEnd,
                closeDateBackEnd,
                periodMonthsBackEnd,
                interestRateBackEnd
        );
    }


    public boolean depositProductNameTextDisplayed() {
        return depositProductNameText.isDisplayed();
    }

    public boolean depAccountNumberTextDisplayed() {
        return depAccountNumberText.isDisplayed();
    }

    public boolean statusDepositTextDisplayed() {
        return statusDepositText.isDisplayed();
    }

    public boolean openDateDepositTextDisplayed() {
        return openDateDepositText.isDisplayed();
    }

    public boolean closeDateDepositTextDisplayed() {
        return closeDateDepositText.isDisplayed();
    }

    public boolean initialDepositAmountTextDisplayed() {
        return initialDepositAmountText.isDisplayed();
    }

    public boolean finalDepositAmountTexDisplayed() {
        return finalDepositAmountText.isDisplayed();
    }

    public boolean periodMonthsDepositTextDisplayed() {
        return periodMonthsDepositText.isDisplayed();
    }

    public boolean interestRateTextDisplayed() {
        return interestRateText.isDisplayed();
    }

    public boolean outputCopiedTextDisplayed() {
        return outputCopiedText.isDisplayed();
    }

    public void clickCopyDepositAccountNumberButton() {
        copyDepositAccountNumberButton.click();
    }

    public void clickRevokeDepositButton() {
        revokeDepositButton.click();
    }

    public void clickPayDepositButton() {
        payDepositButton.click();
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

    public boolean paymentInfoButtonDisplayed() {
        return paymentInfoButton.isDisplayed();
    }

    public boolean extendDepositButtonDisplayed() {
        return extendDepositButton.isDisplayed();
    }
}
