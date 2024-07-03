package web.pages.depositPages;

import api.model.webAndApi.DepositProductService;
import api.model.webAndApi.credit.MyCreditMoreInformation;
import api.model.webAndApi.deposit.MyDepositMoreInfo;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import java.util.List;

public class MyDepositsDetailedInfoPage extends BasePage {
//    public DepositProductService depositProductService;
    private String nameBackEnd;
    private String depAccountNumberBackEnd;
    private java.sql.Date openDateBackEnd;
    private java.sql.Date closeDateBackEnd;
    private Double interestRateBackEnd;
    private Double initialAmountBackEnd;
    private String currencyCodeBackEnd;
    private String nameWeb;
    private String depAccountNumberWeb;
    private java.sql.Date openDateWeb;
    private java.sql.Date closeDateWeb;
    private Double interestRateWeb;
    private Double initialAmountWeb;
    private String currencyCodeWeb;
    private List<MyDepositMoreInfo> listXpath;
    private List<MyDepositMoreInfo> listFromBackEnd;
//    public MyDepositsDetailedInfoPage(){
//        depositProductService = new DepositProductService();
//    }
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
    @FindBy(xpath = "//*[@id='copy-card']")
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
    @FindBy(xpath = "//div[@data-testid='dots-button']/div/button[text()='Отказ от пролонгации депозита']")
    private WebElement extendRefusalButton;

//    public MyDepositMoreInfo getMyDepositProductObjectFromBackEnd() {
//        depositProductService.getMoreInfoAboutMyDeposit();
//        return new MyDepositMoreInfo();
//    }

    public MyDepositMoreInfo getMyDepositProductObjectFromWeb() {
    return new MyDepositMoreInfo();
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
}
