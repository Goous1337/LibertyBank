package web.pages;

import api.model.webAndApi.CreditProductService;
import api.model.webAndApi.credit.CreditProduct;
import api.model.webAndApi.credit.MoreCreditProduct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;

import java.util.List;

public class CreditInfoPage extends BasePage {

    public CreditProductService creditProductService;
    private String nameOfCreditProduct;
    private String interestRateCreditProduct;

    public CreditInfoPage() {
        creditProductService = new CreditProductService();
    }

    @FindBy(xpath = "//a[@href='/credits']")
    private WebElement creditButton;
    /*Кнопки подразделов 'Кредиты'*/
    @FindBy(xpath = "//a[@data-testid='nav-link-0']")
    private WebElement myCreditButton;
    @FindBy(xpath = "//a[@data-testid='nav-link-1']")
    private WebElement creditProductsBankButton;
    @FindBy(xpath = "//a[@data-testid='nav-link-2']")
    private WebElement submittedCreditАpplicationsButton;
    /*Краткая информация 'Мои кредиты'*/
    @FindBy(xpath = "//h2[@data-testid='currencyBlockText']")
    private WebElement sumCreditText;
    @FindBy(xpath = "//div/h3")
    private WebElement nameCreditText;
    @FindBy(xpath = "//ul//li[1]/div/p[@data-testid='creditTerm']")
    private WebElement termCreditText;
    @FindBy(xpath = "//ul//li[1]//div/a")
    private WebElement showMoreAboutCreditButton;
    /*Подробная информация 'Мои кредиты'*/
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
    /*Краткая информация по Кредитным продуктам*/
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Наличными')]")
    private WebElement nameShortCreditProductPageText;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Срочный')]")
    private WebElement nameShortCreditExpressProductPageText;

    /*Подробная информация по Кредитным продуктам*/
    @FindBy(xpath = "//h1[@class = '_text_h1_xv9cv_1 _text_bold_xv9cv_33']")
    private WebElement nameCreditProductPageText;
    @FindBy(xpath = "//p[text() = 'Процентная ставка']/following-sibling::div/span[2]")
    private WebElement interestRateCreditProductText;
    /*Кнопки 'Показать больше' в разделе кредитные продукты банка*/
    @FindBy(xpath = "//li[./span[@data-testid='Liberty Наличными']]//button/span[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyCash;
    @FindBy(xpath = "//li[./span[@data-testid='Liberty Срочный']]//button/span[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyExpress;
    @FindBy(xpath = "//li[./span[@data-testid='Liberty Money']]//button/span[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyMoney;
    @FindBy(xpath = "//li[./span[@data-testid='Liberty Easy']]//button/span[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyEasy;
    @FindBy(xpath = "//li[./span[@data-testid='Liberty Car']]//button/span[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyCar;
    @FindBy(xpath = "//li[./span[@data-testid='Моя квартира']]//button/span[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreMyFlat;
    /*Xpath for US3.2*/
    @FindBy(xpath = "//*[contains(@class,'_title_1oczs_12')][text()='Liberty Наличными']")
    private WebElement nameOfLibertyCashCredit;
    @FindBy(xpath = "//*[contains(@class,'_title_1oczs_12')][text()='Liberty Срочный']")
    private WebElement nameOfLibertyExpress;
    @FindBy(xpath = "//*[contains(@class, '_rate_1oczs_31')][@data-testid='Liberty Наличными']")
    private WebElement procentOfLibertyCashCredit;
    @FindBy(xpath = "//*[contains(@class, '_rate_1oczs_31')][@data-testid='Liberty Срочный']")
    private WebElement procentOfLibertyExpress;
    @FindBy(xpath = "//li[1]/div[2]/p")
    private WebElement descriptionLibertyCash;
    @FindBy(xpath = "//li[2]/div[2]/p")
    private WebElement descriptionLibertyExpress;

    @FindBy(xpath = "//li[1]/div[3]/button[contains(text(), 'Подать заявку')]")
    private WebElement buttonGetApplicationLibertyCash;
    @FindBy(xpath = "//li[2]/div[3]/button[contains(text(), 'Подать заявку')]")
    private WebElement buttonGetApplicationLibertyExpress;

    public String getActualTitleCredit() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public void clickCreditButton() {
        creditButton.click();
    }

    /*Раздел кредиты*/
    public boolean isMyCreditButtonDisplayed() {
        return myCreditButton.isDisplayed();
    }

    public boolean isCreditProductsBankButtonDisplayed() {
        return creditProductsBankButton.isDisplayed();
    }

    public boolean isSubmittedCreditRequestDisplayed() {
        return submittedCreditАpplicationsButton.isDisplayed();
    }

    /*Подраздел 'Мои кредиты'*/
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

    public void clickShowMoreMyCreditButton() {
        showMoreAboutCreditButton.click();
    }

    /*Кнопки для перехода в подразделы раздела 'Кредиты'*/
    public void clickMyCreditButton() {
        myCreditButton.click();
    }

    public void clickCreditProductsBankButton() {
        creditProductsBankButton.click();
    }

    public void clickSubmittedCreditRequestButton() {
        submittedCreditАpplicationsButton.click();
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

    /*Краткая информация по Кредитным продуктам банка*/

    public String getShortNameCreditProductPageText() {
        return nameShortCreditProductPageText.getText();
    }

    public String getShortNameCreditExpressProductPageText() {
        return nameShortCreditExpressProductPageText.getText();
    }

    public String getShortNameProductsCreditBank() {
        creditProductService.getProductCredit();
        List<CreditProduct> list = creditProductService.getCreditProductsList();
        for (CreditProduct creditProduct : list) {
            if (creditProduct.getName().equals(getShortNameCreditExpressProductPageText())) {
                nameOfCreditProduct = creditProduct.getName();
            }
        }
        return nameOfCreditProduct;
    }

    /*Подробная информация о кредитных продуктах банка*/
    public String getNameCreditProductPageText() {
        return nameCreditProductPageText.getText();
    }

    public String getInterestRateCreditProductPageText() {
        return interestRateCreditProductText.getText();
    }

    public String getNameProductsCreditBank() {
        creditProductService.getUsersFromPage();
        List<MoreCreditProduct> list = creditProductService.getMoreCreditProductList();
        for (MoreCreditProduct moreCreditProduct : list) {
            if (moreCreditProduct.getName().equals(getNameCreditProductPageText())) {
                nameOfCreditProduct = moreCreditProduct.getName();
            }
        }
        return nameOfCreditProduct;
    }

    public String getInterestRateProductCredit() {
        creditProductService.getUsersFromPage();
        List<MoreCreditProduct> list = creditProductService.getMoreCreditProductList();
        for (MoreCreditProduct moreCreditProduct : list) {
            if (moreCreditProduct.convertInterestRateToString(moreCreditProduct.getInterestRate()).equals(getInterestRateCreditProductPageText())) {
                interestRateCreditProduct = moreCreditProduct.convertInterestRateToString(moreCreditProduct.getInterestRate());
            }
        }
        return interestRateCreditProduct;
    }

    /*Клик кнопок 'Показать больше' в разделе 'Мои кредитные продукты'*/
    public void clickButtonShowMoreLibertyCash() {
        buttonShowMoreLibertyCash.click();
    }

    public void clickButtonShowMoreLibertyExpress() {
        buttonShowMoreLibertyExpress.click();
    }

    public void clickButtonShowMoreLibertyMoney() {
        buttonShowMoreLibertyMoney.click();
    }

    public void clickButtonShowMoreLibertyEasy() {
        buttonShowMoreLibertyEasy.click();
    }

    public void clickButtonShowMoreLibertyCar() {
        buttonShowMoreLibertyCar.click();
    }

    public void clickButtonShowMoreLibertyMyFlat() {
        buttonShowMoreMyFlat.click();
    }

}
