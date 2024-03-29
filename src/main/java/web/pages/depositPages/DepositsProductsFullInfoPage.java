package web.pages.depositPages;

import api.model.webAndApi.DepositProductService;
import api.model.webAndApi.deposit.DepositProductDetails;
import api.model.webAndApi.deposit.DepositProductFullInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.enums.DepositsEnum;
import web.pages.BasePage;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static web.helpers.Converter.*;

public class DepositsProductsFullInfoPage extends BasePage {
    private DepositProductService depositProductService;
    private List<DepositProductDetails> depositProductDetailsList;
    private EnumMap<DepositsEnum, DepositProductFullInfo> enumMap;

    public DepositsProductsFullInfoPage() {
        depositProductService = new DepositProductService();
    }

    private List<WebElement> headers;
    private List<WebElement> descriptions;
    private List<String> descriptionsToString;

    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/ancestor::li//button[text() = 'Показать больше']")
    private WebElement showMoreButtonLibertyChild;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/ancestor::li//button[text() = 'Показать больше']")
    private WebElement showMoreButtonLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный USD')]/ancestor::li//button[text() = 'Показать больше']")
    private WebElement showMoreButtonLibertyCurrencyUsd;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/ancestor::li//button[text() = 'Показать больше']")
    private WebElement showMoreButtonLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/ancestor::li//button[text() = 'Показать больше']")
    private WebElement showMoreButtonLibertyBase;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/ancestor::li//button[text() = 'Показать больше']")
    private WebElement showMoreButtonLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/ancestor::li//button[text() = 'Показать больше']")
    private WebElement showMoreButtonLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]/ancestor::li//button[text() = 'Показать больше']")
    private WebElement showMoreButtonLibertyStandard;
    @FindBy(xpath = "//h2")
    private WebElement depositProductName;
    @FindBy(xpath = "//div/h4")
    private WebElement productDetails;
    @FindBy(xpath = "//ul//li//p[text() = 'Процентная ставка']/ancestor::li//span[2]")
    private WebElement maxInterestRate;
    @FindBy(xpath = "//ul//li//p[text() = 'Минимальная сумма вклада']/ancestor::li//span[2]")
    private WebElement amountMin;
    @FindBy(xpath = "//ul//li//p[text() = 'Максимальная сумма вклада']/ancestor::li//span[2]")
    private WebElement amountMax;
    @FindBy(xpath = "//ul//li//p[text() = 'Срок кредита']/ancestor::li//span[2]")
    private WebElement maxDurationMonths;
    private String nameFromWeb;
    private String nameFromBack;
    private String detailsFromWeb;
    private String detailsFromBack;
    private Double maxInterestRateFromWeb;
    private Double maxInterestRateFromBack;
    private Double amountMinFromWeb;
    private Double amountMinFromBack;
    private Double amountMaxFromWeb;
    private Double amountMaxFromBack;
    private Integer maxDurationMonthsFromWeb;
    private Integer maxDurationMonthsFromBack;
    private List<DepositProductDetails> detailsInNationalCurrencyFromWeb;
    private List<DepositProductDetails> detailsInNationalCurrencyFromBack;

    public void clickShowMoreButton() {
        showMoreButtonLibertyChild.click();
    }

    public void clickShowMoreButtonLibertyCalculated() {
        showMoreButtonLibertyCalculated.click();
    }

    public void clickShowMoreButtonLibertyCurrencyUsd() {
        showMoreButtonLibertyCurrencyUsd.click();
    }

    public void clickShowMoreButtonLibertyExpress() {
        showMoreButtonLibertyExpress.click();
    }

    public void clickShowMoreButtonLibertyBase() {
        showMoreButtonLibertyBase.click();
    }

    public void clickShowMoreButtonLibertyPremium() {
        showMoreButtonLibertyPremium.click();
    }

    public void clickShowMoreButtonLibertyStandardExpress() {
        showMoreButtonLibertyStandardExpress.click();
    }

    public void clickShowMoreButtonLibertyStandard() {
        showMoreButtonLibertyStandard.click();
    }

    public EnumMap<DepositsEnum, DepositProductFullInfo> putEnumAndXpathToMap(DepositsEnum str) {
        enumMap = new EnumMap<>(DepositsEnum.class);
        depositProductDetailsList = new ArrayList<DepositProductDetails>();
        descriptionsToString = new ArrayList<String>();
        headers = DriverManager.getDriver().findElements(By.xpath("//*[name() = 'svg']/following-sibling::span[1]"));
        descriptions = DriverManager.getDriver().findElements(By.xpath("//*[name() = 'svg']/following-sibling::span[2]"));
        for (int i = 0; i <= headers.size() - 1; i++) {
            depositProductDetailsList.add(new DepositProductDetails(headers.get(i).getText(), parseToList(descriptions.get(i).getText())));
        }
        enumMap.put(
                str,
                new DepositProductFullInfo(
                        depositProductName.getText(),
                        productDetails.getText(),
                        convertToDouble(maxInterestRate.getText()),
                        convertToDouble(amountMin.getText()),
                        convertToDouble(amountMax.getText()),
                        convertToInteger(maxDurationMonths.getText()),
                        depositProductDetailsList));
        return enumMap;
    }

    public DepositProductFullInfo getObjectFromWeb(DepositsEnum str) {
        for (Map.Entry<DepositsEnum, DepositProductFullInfo> pair : putEnumAndXpathToMap(str).entrySet()) {
            if (str.equals(pair.getKey())) {
                nameFromWeb = pair.getValue().getName();
                detailsFromWeb = pair.getValue().getProductDetails();
                maxInterestRateFromWeb = pair.getValue().getMaxInterestRate();
                amountMinFromWeb = pair.getValue().getAmountMin();
                amountMaxFromWeb = pair.getValue().getAmountMax();
                maxDurationMonthsFromWeb = pair.getValue().getMaxDurationMonths();
                detailsInNationalCurrencyFromWeb = pair.getValue().getDepositDetail();
            }
        }
        return new DepositProductFullInfo(nameFromWeb,
                detailsFromWeb,
                maxInterestRateFromWeb,
                amountMinFromWeb,
                amountMaxFromWeb,
                maxDurationMonthsFromWeb,
                detailsInNationalCurrencyFromWeb);
    }

    public DepositProductFullInfo getObjectFromBack(DepositsEnum str) {
        depositProductService.getDepositProductFullInfo();
        List<DepositProductFullInfo> depositProductFullInfoList = depositProductService.getDepositProductFullInfoList();
        putEnumAndXpathToMap(str);
        for (DepositProductFullInfo depositProductFullInfo : depositProductFullInfoList) {
            for (Map.Entry<DepositsEnum, DepositProductFullInfo> pair : putEnumAndXpathToMap(str).entrySet()) {
                if (str.equals(pair.getKey()) && depositProductFullInfo.getName().equals(pair.getValue().getName())) {
                    nameFromBack = depositProductFullInfo.getName();
                }
                if (str.equals(pair.getKey()) && depositProductFullInfo.getProductDetails().equals(pair.getValue().getProductDetails())) {
                    detailsFromBack = depositProductFullInfo.getProductDetails();
                }
                if (str.equals(pair.getKey()) && depositProductFullInfo.getMaxInterestRate().equals(pair.getValue().getMaxInterestRate())) {
                    maxInterestRateFromBack = depositProductFullInfo.getMaxInterestRate();
                }
                if (str.equals(pair.getKey()) && depositProductFullInfo.getAmountMin().equals(pair.getValue().getAmountMin())) {
                    amountMinFromBack = depositProductFullInfo.getAmountMin();
                }
                if (str.equals(pair.getKey()) && depositProductFullInfo.getAmountMax().equals(pair.getValue().getAmountMax())) {
                    amountMaxFromBack = depositProductFullInfo.getAmountMax();
                }
                if (str.equals(pair.getKey()) && depositProductFullInfo.getMaxDurationMonths().equals(pair.getValue().getMaxDurationMonths())) {
                    maxDurationMonthsFromBack = depositProductFullInfo.getMaxDurationMonths();
                }
                if (str.equals(pair.getKey()) && depositProductDetailsList.equals(depositProductFullInfo.getDepositDetail())) {
                    detailsInNationalCurrencyFromBack = depositProductFullInfo.getDepositDetail();
                }
            }
        }
        return new DepositProductFullInfo(nameFromBack, detailsFromBack, maxInterestRateFromBack, amountMinFromBack, amountMaxFromBack, maxDurationMonthsFromBack, detailsInNationalCurrencyFromBack);
    }
}
