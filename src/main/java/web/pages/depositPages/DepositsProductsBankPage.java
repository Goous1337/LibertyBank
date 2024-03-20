package web.pages.depositPages;

import api.model.webAndApi.DepositProductService;
import api.model.webAndApi.deposit.DepositProduct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.constans.DepositsConstants;
import web.enums.DepositsEnum;
import web.pages.BasePage;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DepositsProductsBankPage extends BasePage {
    private DepositProductService depositProductService;
    private EnumMap<DepositsEnum, DepositProduct> enumMap;
    private String nameFromWeb;
    private String nameFromBackEnd;
    private Double maxInterestRateFromWeb;
    private Double maxInterestRateFromBackEnd;
    private String productDetailsFromWeb;
    private String productDetailsFromBackEnd;
    private int maxDurationMonthLibertyChildFromWeb;
    private int maxDurationMonthLibertyChildFromBackEnd;
    private Double amountMinLibertyChildFromWeb;
    private Double amountMinLibertyChildFromBackEnd;

    public DepositsProductsBankPage() {
        depositProductService = new DepositProductService();
    }

    @FindBy(xpath = "//a[@href='/deposits']")
    private WebElement depositsButton;
    @FindBy(xpath = "//a[@href='/deposits/deposit-products']")
    private WebElement depositProductsBankButton;
    // Liberty+ Детский
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]")
    private WebElement nameOfLibertyChild;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyChild;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyChild;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyChild;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyChild;
    //Liberty+ Валютный EUR
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный EUR')]")
    private WebElement nameOfLibertyCurrency;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный EUR')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyCurrency;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный EUR')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyCurrency;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный EUR')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyCurrency;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный EUR')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyCurrency;
    //Liberty+ Расчетный
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]")
    private WebElement nameOfLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyCalculated;
    //Liberty + Срочный
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]")
    private WebElement nameOfLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyExpress;
    //Liberty Базовый
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]")
    private WebElement nameOfLibertyBasic;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyBasic;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyBasic;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyBasic;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyBasic;
    //Liberty Premium
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]")
    private WebElement nameOfLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyPremium;
    //Liberty Стандартный Срочный
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]")
    private WebElement nameOfLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyStandardExpress;
    //Liberty+ Валютный USD
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный USD')]")
    private WebElement nameOfLibertyPlusCurrencyUSD;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный USD')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyPlusCurrencyUSD;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный USD')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyPlusCurrencyUSD;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный USD')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyPlusCurrencyUSD;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный USD')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyPlusCurrencyUSD;
    //Liberty Стандарт
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]")
    private WebElement nameOfLibertyStandard;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]/ancestor::li//h3[@data-testid = 'maxInterestRate']")
    private WebElement maxInterestRateOfLibertyStandard;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]/ancestor::li//p[@data-testid = 'depositDetails']")
    private WebElement productDetailOfLibertyStandard;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyStandard;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyStandard;
    @FindBy(xpath = "//a[@href='/deposits/my-deposits']")
    private WebElement myDepositProductsBankButton;

    public void clickDepositsButton() {
        depositsButton.click();
    }

    public void clickDepositsProductsBankButton() {
        depositProductsBankButton.click();
    }

    public void clickMyDepositsButton() {
        myDepositProductsBankButton.click();
    }

    public EnumMap<DepositsEnum, DepositProduct> putEnumAndXpathToMap() {
        enumMap = new EnumMap<>(DepositsEnum.class);
        enumMap.put(
                DepositsEnum.LIBERTY_CHILD,
                new DepositProduct(
                        nameOfLibertyChild.getText(),
                        convertToDouble(maxInterestRateOfLibertyChild.getText()),
                        productDetailOfLibertyChild.getText(),
                        convertToInteger(maxDurationMonthOfLibertyChild.getText()),
                        convertToDouble(amountMinOfLibertyChild.getText())));
        enumMap.put(
                DepositsEnum.LIBERTY_CURRENCY_EUR,
                new DepositProduct(
                        nameOfLibertyCurrency.getText(),
                        convertToDouble(maxInterestRateOfLibertyCurrency.getText()),
                        productDetailOfLibertyCurrency.getText(),
                        convertToInteger(maxDurationMonthOfLibertyCurrency.getText()),
                        convertToDouble(amountMinOfLibertyCurrency.getText())));
        enumMap.put(
                DepositsEnum.LIBERTY_CALCULATED,
                new DepositProduct(
                        nameOfLibertyCalculated.getText(),
                        convertToDouble(maxInterestRateOfLibertyCalculated.getText()),
                        productDetailOfLibertyCalculated.getText(),
                        convertToInteger(maxDurationMonthOfLibertyCalculated.getText()),
                        convertToDouble(amountMinOfLibertyCalculated.getText())));
        enumMap.put(
                DepositsEnum.LIBERTY_PLUS_EXPRESS,
                new DepositProduct(
                        nameOfLibertyExpress.getText(),
                        convertToDouble(maxInterestRateOfLibertyExpress.getText()),
                        productDetailOfLibertyExpress.getText(),
                        convertToInteger(maxDurationMonthOfLibertyExpress.getText()),
                        convertToDouble(amountMinOfLibertyExpress.getText())));
        enumMap.put(
                DepositsEnum.LIBERTY_BASIC,
                new DepositProduct(
                        nameOfLibertyBasic.getText(),
                        convertToDouble(maxInterestRateOfLibertyBasic.getText()),
                        productDetailOfLibertyBasic.getText(),
                        convertToInteger(maxDurationMonthOfLibertyBasic.getText()),
                        convertToDouble(amountMinOfLibertyBasic.getText())));
        enumMap.put(
                DepositsEnum.LIBERTY_PREMIUM,
                new DepositProduct(
                        nameOfLibertyPremium.getText(),
                        convertToDouble(maxInterestRateOfLibertyPremium.getText()),
                        productDetailOfLibertyPremium.getText(),
                        convertToInteger(maxDurationMonthOfLibertyPremium.getText()),
                        convertToDouble(amountMinOfLibertyPremium.getText())));
        enumMap.put(
                DepositsEnum.LIBERTY_STANDARD_EXPRESS,
                new DepositProduct(
                        nameOfLibertyStandardExpress.getText(),
                        convertToDouble(maxInterestRateOfLibertyStandardExpress.getText()),
                        productDetailOfLibertyStandardExpress.getText(),
                        convertToInteger(maxDurationMonthOfLibertyStandardExpress.getText()),
                        convertToDouble(amountMinOfLibertyStandardExpress.getText())));
        enumMap.put(
                DepositsEnum.LIBERTY_PLUS_CURRENCY_USD,
                new DepositProduct(
                        nameOfLibertyPlusCurrencyUSD.getText(),
                        convertToDouble(maxInterestRateOfLibertyPlusCurrencyUSD.getText()),
                        productDetailOfLibertyPlusCurrencyUSD.getText(),
                        convertToInteger(maxDurationMonthOfLibertyPlusCurrencyUSD.getText()),
                        convertToDouble(amountMinOfLibertyPlusCurrencyUSD.getText())));
        enumMap.put(
                DepositsEnum.LIBERTY_STANDARD,
                new DepositProduct(
                        nameOfLibertyStandard.getText(),
                        convertToDouble(maxInterestRateOfLibertyStandard.getText()),
                        productDetailOfLibertyStandard.getText(),
                        convertToInteger(maxDurationMonthOfLibertyStandard.getText()),
                        convertToDouble(amountMinOfLibertyStandard.getText())));

        return enumMap;
    }

    public DepositProduct getObjectFromWeb(Enum e) {
        for (Map.Entry<DepositsEnum, DepositProduct> pair : putEnumAndXpathToMap().entrySet()) {
            if (e.equals(pair.getKey())) {
                nameFromWeb = pair.getValue().getName();
                maxInterestRateFromWeb = pair.getValue().getMaxInterestRate();
                productDetailsFromWeb = pair.getValue().getProductDetails();
                maxDurationMonthLibertyChildFromWeb = pair.getValue().getMaxDurationMonths();
                amountMinLibertyChildFromWeb = pair.getValue().getAmountMin();
            }
        }

        return new DepositProduct(
                nameFromWeb,
                maxInterestRateFromWeb,
                "Подробная информация о депозите",
                maxDurationMonthLibertyChildFromWeb,
                amountMinLibertyChildFromWeb);
    }

    public DepositProduct getObjectFromBack(Enum e) {
        depositProductService.getDepositsFromPage();
        List<DepositProduct> depositProductList = depositProductService.getDepositProductList();
        putEnumAndXpathToMap();
        for (DepositProduct depositProduct : depositProductList) {
            for (Map.Entry<DepositsEnum, DepositProduct> pair : putEnumAndXpathToMap().entrySet()) {
                if (e.equals(pair.getKey()) && depositProduct.getName().equals(pair.getValue().getName())) {
                    nameFromBackEnd = depositProduct.getName();
                }
                if (e.equals(pair.getKey()) && depositProduct.getMaxInterestRate().equals(pair.getValue().getMaxInterestRate())) {
                    maxInterestRateFromBackEnd = depositProduct.getMaxInterestRate();
                }
//                if(e.equals(pair.getKey()) && depositProduct.getProductDetails().equals(pair.getValue().getProductDetails())){
//                    productDetailsFromBackEnd = depositProduct.getProductDetails();
//                }
                if (e.equals(pair.getKey()) && depositProduct.getMaxDurationMonths().equals(pair.getValue().getMaxDurationMonths())) {
                    maxDurationMonthLibertyChildFromBackEnd = depositProduct.getMaxDurationMonths();
                }
                if (e.equals(pair.getKey()) && depositProduct.getAmountMin().equals(pair.getValue().getAmountMin())) {
                    amountMinLibertyChildFromBackEnd = depositProduct.getAmountMin();
                }
            }
        }
        return new DepositProduct(
                nameFromBackEnd,
                maxInterestRateFromBackEnd,
                "Подробная информация о депозите",
                maxDurationMonthLibertyChildFromBackEnd,
                amountMinLibertyChildFromBackEnd);
    }

    private static Double convertToDouble(String str) {
        str = String.valueOf(str).replace(DepositsConstants.DELIMITER, DepositsConstants.POINT).trim();
        str = String.valueOf(str).replace(DepositsConstants.PROCENT, "").trim();
        str = String.valueOf(str).replace(DepositsConstants.SPACE, "").trim();
        str = String.valueOf(str).replace(DepositsConstants.RUB, "").trim();
        str = String.valueOf(str).replace(DepositsConstants.EUR, "").trim();
        str = String.valueOf(str).replace(DepositsConstants.USD, "").trim();
        return Double.parseDouble(str);
    }

    private static int convertToInteger(String str) {
        str = String.valueOf(str).replace(DepositsConstants.MONTHS, "").trim();
        return Integer.parseInt(str);
    }
}
