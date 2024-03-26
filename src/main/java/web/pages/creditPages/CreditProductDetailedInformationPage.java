package web.pages.creditPages;

import api.model.webAndApi.CreditProductService;
import api.model.webAndApi.credit.MoreCreditDetails;
import api.model.webAndApi.credit.MoreCreditProduct;
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
public class CreditProductDetailedInformationPage extends BasePage {
    public CreditProductService creditProductService;
    private String nameOfCreditProductFromBackEnd;
    private Double interestRateFromBackEnd;
    private Integer minSumFromBackEnd;
    private Integer maxSumFromBackEnd;
    private String headerDetailFromBackEnd;
    private String nameofCreditProductFromWeb;
    private Double interestRateCreditProductFromWeb;
    private Integer minSumFromWeb;
    private Integer maxSumFromWeb;
    private String headerDetailFromWeb;
    private List<MoreCreditProduct> listXpath;
    private List<MoreCreditProduct> listFromBackEnd;
    private List<MoreCreditDetails> listDetailsXpath;
    private List<MoreCreditDetails> listDetailsFromBackEnd;

    public CreditProductDetailedInformationPage() {
        creditProductService = new CreditProductService();
    }

    @FindBy(xpath = "//ul/following-sibling::div/h1")
    private WebElement nameCreditProductPageText;
    @FindBy(xpath = "//p[text() = 'Процентная ставка']/following-sibling::div/span")
    private WebElement interestRateCreditProductText;
    @FindBy(xpath = "//p[text() = 'Минимальная сумма кредита']/following-sibling::div/span[2]")
    private WebElement minSumCredit;
    @FindBy(xpath = "//p[text() = 'Максимальная сумма кредита']/following-sibling::div/span[2]")
    private WebElement maxSumCredit;
    @FindBy(xpath = "//p[text() = 'Срок кредита']/following-sibling::div/span[1]]")
    private WebElement minTermCredit;
    @FindBy(xpath = "//p[text() = 'Срок кредита']/following-sibling::div/span[2]")
    private WebElement maxTermCredit;
    /*Детали кредитных продуктов банка*/
    @FindBy(xpath = "//*[@data-testid='icon-atm']")
    private WebElement iconGetCash;
    @FindBy(xpath = "//span[text()='Получи наличными или картой']")
    private WebElement titleGetCash;
    @FindBy(xpath = "//*[@data-testid='icon-calendar-blue']")
    private WebElement iconСalendar;
    @FindBy(xpath = " //span[text()='Сроки рассмотрения заявки']")
    private WebElement titleСalendar;
    @FindBy(xpath = "//*[@data-testid='icon-percent']")
    private WebElement iconPercent;
    @FindBy(xpath = " //span[text()='Фиксированная ставка']")
    private WebElement titlePercent;
    @FindBy(xpath = "//*[@data-testid='icon-age']")
    private WebElement iconBailsMan;
    @FindBy(xpath = " //span[text()='Наличие поручителей']")
    private WebElement titleBailsMan;
    @FindBy(xpath = "//*[@data-testid='icon-clock']")
    private WebElement iconEarlyRepayment;
    @FindBy(xpath = " //span[text()='Досрочное погашение']")
    private WebElement titleEarlyRepayment;
    @FindBy(xpath = "//*[@data-testid='icon-calculation']")
    private WebElement iconCalculationScheme;
    @FindBy(xpath = " //span[text()='Прозрачная схема расчёта']")
    private WebElement titleCalculationScheme;
    @FindBy(xpath = "//*[@data-testid='icon-cursor']")
    private WebElement iconSimplePaperwork;
    @FindBy(xpath = "//span[text()='Простое оформление']")
    private WebElement titleSimplePaperwork;
    @FindBy(xpath = "//*[@data-testid='icon-actions-cash']")
    private WebElement iconMinimumFine;
    @FindBy(xpath = "span[text()='Минимальный штраф']")
    private WebElement titleMinimumFine;
    @FindBy(xpath = "//h1/following-sibling::div[2]//span[1]")
    private WebElement headerCreditDetails;
    @FindBy(xpath = "//div[1]/*[name() = 'svg' and @data-testid]/following-sibling::span[1]")
    private WebElement headerFirstCreditDetails;
    @FindBy(xpath = "//div[2]/*[name() = 'svg' and @data-testid]/following-sibling::span[1]")
    private WebElement headerSecondCreditDetails;
    @FindBy(xpath = "//div[3]/*[name() = 'svg' and @data-testid]/following-sibling::span[1]")
    private WebElement headerThreeCreditDetails;
    @FindBy(xpath = "//div[4]/*[name() = 'svg' and @data-testid]/following-sibling::span[1]")
    private WebElement headerFourCreditDetails;
    @FindBy(xpath = "//div[5]/*[name() = 'svg' and @data-testid]/following-sibling::span[1]")
    private WebElement headerFiveCreditDetails;
    @FindBy(xpath = "//div[6]/*[name() = 'svg' and @data-testid]/following-sibling::span[1]")
    private WebElement headerSixCreditDetails;
    @FindBy(xpath = "//div[7]/*[name() = 'svg' and @data-testid]/following-sibling::span[1]")
    private WebElement headerSevenCreditDetails;
    @FindBy(xpath = "//div//following-sibling::span[2]")
    private WebElement creditDetails;

    /*Подробная информация о кредитных продуктах банка*/
    public List<MoreCreditProduct> addMoreCreditProductXpathToList() {
        listXpath = new ArrayList<>();
        listXpath.add(new MoreCreditProduct(
                nameCreditProductPageText.getText(),
                convertToDouble(interestRateCreditProductText.getText()),
                convertStringToInteger(minSumCredit.getText()),
                convertStringToInteger(maxSumCredit.getText()),
                addMoreCreditProductDetailsXpathToList()
        ));
        return listXpath;
    }

    public List<MoreCreditDetails> addMoreCreditProductDetailsXpathToList() {
        listDetailsXpath = new ArrayList<>();
        listDetailsXpath.add(new MoreCreditDetails(headerFirstCreditDetails.getText()));
        listDetailsXpath.add(new MoreCreditDetails(headerSecondCreditDetails.getText()));
        listDetailsXpath.add(new MoreCreditDetails(headerThreeCreditDetails.getText()));
        listDetailsXpath.add(new MoreCreditDetails(headerFourCreditDetails.getText()));
        listDetailsXpath.add(new MoreCreditDetails(headerFiveCreditDetails.getText()));
        listDetailsXpath.add(new MoreCreditDetails(headerSixCreditDetails.getText()));
        listDetailsXpath.add(new MoreCreditDetails(headerSevenCreditDetails.getText()));
        return listDetailsXpath;
    }

    public MoreCreditProduct getCreditProductObjectFromWeb() {
        for (MoreCreditProduct moreCreditProductWeb : addMoreCreditProductXpathToList()) {
            nameofCreditProductFromWeb = moreCreditProductWeb.getName();
            interestRateCreditProductFromWeb = moreCreditProductWeb.getInterestRate();
            minSumFromWeb = moreCreditProductWeb.getMinSum();
            maxSumFromWeb = moreCreditProductWeb.getMaxSum();
            for (MoreCreditDetails moreCreditDetails : listDetailsXpath) {
                headerDetailFromWeb = moreCreditDetails.getHeader();
            }
        }
        return new MoreCreditProduct(
                nameofCreditProductFromWeb,
                interestRateCreditProductFromWeb,
                minSumFromWeb,
                maxSumFromWeb,
                listDetailsXpath
        );
    }

    public MoreCreditProduct getCreditProductObjectFromBackEnd() {
        creditProductService.getMoreProductCredit();
        listXpath = addMoreCreditProductXpathToList();
        listFromBackEnd = creditProductService.getMoreCreditProductList();
        listDetailsFromBackEnd = new ArrayList<>();
        for (MoreCreditProduct moreCreditProductBack : listFromBackEnd) {
            for (MoreCreditProduct moreCreditProductWeb : listXpath) {
                if (moreCreditProductBack.getName().equals(moreCreditProductWeb.getName())) {
                    nameOfCreditProductFromBackEnd = moreCreditProductBack.getName();
                }
                if (moreCreditProductBack.getInterestRate().equals(moreCreditProductWeb.getInterestRate())) {
                    interestRateFromBackEnd = moreCreditProductBack.getInterestRate();
                }
                if (moreCreditProductBack.getMinSum().equals(moreCreditProductWeb.getMinSum())) {
                    minSumFromBackEnd = moreCreditProductBack.getMinSum();
                }
                if (moreCreditProductBack.getMaxSum().equals(moreCreditProductWeb.getMaxSum())) {
                    maxSumFromBackEnd = moreCreditProductBack.getMaxSum();
                }
                if (moreCreditProductBack.getCreditDetails().equals(moreCreditProductWeb.getCreditDetails())) {
                    listDetailsFromBackEnd = moreCreditProductBack.getCreditDetails();
                }
            }
        }
        return new MoreCreditProduct(nameOfCreditProductFromBackEnd, interestRateFromBackEnd, minSumFromBackEnd, maxSumFromBackEnd, listDetailsFromBackEnd);
    }

}
