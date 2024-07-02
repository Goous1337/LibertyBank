package web.pages.depositPages;

import api.model.webAndApi.DepositProductService;
import api.model.webAndApi.deposit.DepositProductShortInfo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.constans.deposit.depositEnums.DepositsNameEnum;
import web.helpers.Converter;
import web.pages.BasePage;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static web.helpers.Converter.convertToDouble;
import static web.helpers.Converter.convertToInteger;

public class DepositsProductsBankPage extends BasePage {
    private DepositProductService depositProductService;
    private EnumMap<DepositsNameEnum, DepositProductShortInfo> enumMap;
    private Converter converter;
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
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement maxInterestRateOfLibertyChild;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/following-sibling::p[@data-testid = 'productDetails']")
    private WebElement productDetailOfLibertyChild;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyChild;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Детский')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyChild;
    //Liberty+ Расчетный
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]")
    private WebElement nameOfLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement maxInterestRateOfLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/following-sibling::p[@data-testid = 'productDetails']")
    private WebElement productDetailOfLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyCalculated;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Расчетный')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyCalculated;
    //Liberty + Срочный
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]")
    private WebElement nameOfLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement maxInterestRateOfLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/following-sibling::p[@data-testid = 'productDetails']")
    private WebElement productDetailOfLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Срочный')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyExpress;
    //Liberty Базовый
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]")
    private WebElement nameOfLibertyBasic;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement maxInterestRateOfLibertyBasic;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/following-sibling::p[@data-testid = 'productDetails']")
    private WebElement productDetailOfLibertyBasic;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyBasic;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Базовый')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyBasic;
    //Liberty Premium
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]")
    private WebElement nameOfLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement maxInterestRateOfLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/following-sibling::p[@data-testid = 'productDetails']")
    private WebElement productDetailOfLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyPremium;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Premium')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyPremium;
    //Liberty Стандартный Срочный
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]")
    private WebElement nameOfLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement maxInterestRateOfLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/following-sibling::p[@data-testid = 'productDetails']")
    private WebElement productDetailOfLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthOfLibertyStandardExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт Срочный')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinOfLibertyStandardExpress;
    //Liberty+ Валютный USD
//    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный')]")
//    private WebElement nameOfLibertyPlusCurrencyUSD;
//    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный')]/ancestor::li//h3[@data-testid = 'interestRate']")
//    private WebElement maxInterestRateOfLibertyPlusCurrencyUSD;
//    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный')]/following-sibling::p[@data-testid = 'productDetails']")
//    private WebElement productDetailOfLibertyPlusCurrencyUSD;
//    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
//    private WebElement maxDurationMonthOfLibertyPlusCurrencyUSD;
//    @FindBy(xpath = "//h3[contains(text(), 'Liberty+ Валютный')]/ancestor::li//h3[@data-testid = 'amountMin']")
//    private WebElement amountMinOfLibertyPlusCurrencyUSD;
    //Liberty Стандарт
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]")
    private WebElement nameOfLibertyStandard;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement maxInterestRateOfLibertyStandard;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Стандарт')]/following-sibling::p[@data-testid = 'productDetails']")
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

    public EnumMap<DepositsNameEnum, DepositProductShortInfo> putEnumAndXpathToMap() {
        enumMap = new EnumMap<>(DepositsNameEnum.class);
        enumMap.put(
                DepositsNameEnum.LIBERTY_CHILD,
                new DepositProductShortInfo(
                        nameOfLibertyChild.getText(),
                        convertToDouble(maxInterestRateOfLibertyChild.getText()),
                        productDetailOfLibertyChild.getText(),
                        convertToInteger(maxDurationMonthOfLibertyChild.getText()),
                        convertToDouble(amountMinOfLibertyChild.getText())));
//        enumMap.put(
//                DepositsEnum.LIBERTY_CURRENCY_EUR,
//                new DepositProductShortInfo(
//                        nameOfLibertyCurrency.getText(),
//                        convertToDouble(maxInterestRateOfLibertyCurrency.getText()),
//                        productDetailOfLibertyCurrency.getText(),
//                        convertToInteger(maxDurationMonthOfLibertyCurrency.getText()),
//                        convertToDouble(amountMinOfLibertyCurrency.getText())));
        enumMap.put(
                DepositsNameEnum.LIBERTY_CALCULATED,
                new DepositProductShortInfo(
                        nameOfLibertyCalculated.getText(),
                        convertToDouble(maxInterestRateOfLibertyCalculated.getText()),
                        productDetailOfLibertyCalculated.getText(),
                        convertToInteger(maxDurationMonthOfLibertyCalculated.getText()),
                        convertToDouble(amountMinOfLibertyCalculated.getText())));
        enumMap.put(
                DepositsNameEnum.LIBERTY_PLUS_EXPRESS,
                new DepositProductShortInfo(
                        nameOfLibertyExpress.getText(),
                        convertToDouble(maxInterestRateOfLibertyExpress.getText()),
                        productDetailOfLibertyExpress.getText(),
                        convertToInteger(maxDurationMonthOfLibertyExpress.getText()),
                        convertToDouble(amountMinOfLibertyExpress.getText())));
        enumMap.put(
                DepositsNameEnum.LIBERTY_BASIC,
                new DepositProductShortInfo(
                        nameOfLibertyBasic.getText(),
                        convertToDouble(maxInterestRateOfLibertyBasic.getText()),
                        productDetailOfLibertyBasic.getText(),
                        convertToInteger(maxDurationMonthOfLibertyBasic.getText()),
                        convertToDouble(amountMinOfLibertyBasic.getText())));
        enumMap.put(
                DepositsNameEnum.LIBERTY_PREMIUM,
                new DepositProductShortInfo(
                        nameOfLibertyPremium.getText(),
                        convertToDouble(maxInterestRateOfLibertyPremium.getText()),
                        productDetailOfLibertyPremium.getText(),
                        convertToInteger(maxDurationMonthOfLibertyPremium.getText()),
                        convertToDouble(amountMinOfLibertyPremium.getText())));
        enumMap.put(
                DepositsNameEnum.LIBERTY_STANDARD_EXPRESS,
                new DepositProductShortInfo(
                        nameOfLibertyStandardExpress.getText(),
                        convertToDouble(maxInterestRateOfLibertyStandardExpress.getText()),
                        productDetailOfLibertyStandardExpress.getText(),
                        convertToInteger(maxDurationMonthOfLibertyStandardExpress.getText()),
                        convertToDouble(amountMinOfLibertyStandardExpress.getText())));
//        enumMap.put(
//                DepositsEnum.LIBERTY_PLUS_CURRENCY_USD,
//                new DepositProductShortInfo(
//                        nameOfLibertyPlusCurrencyUSD.getText(),
//                        convertToDouble(maxInterestRateOfLibertyPlusCurrencyUSD.getText()),
//                        productDetailOfLibertyPlusCurrencyUSD.getText(),
//                        convertToInteger(maxDurationMonthOfLibertyPlusCurrencyUSD.getText()),
//                        convertToDouble(amountMinOfLibertyPlusCurrencyUSD.getText())));
        enumMap.put(
                DepositsNameEnum.LIBERTY_STANDARD,
                new DepositProductShortInfo(
                        nameOfLibertyStandard.getText(),
                        convertToDouble(maxInterestRateOfLibertyStandard.getText()),
                        productDetailOfLibertyStandard.getText(),
                        convertToInteger(maxDurationMonthOfLibertyStandard.getText()),
                        convertToDouble(amountMinOfLibertyStandard.getText())));

        return enumMap;
    }

    public DepositProductShortInfo getObjectFromWeb(Enum e) {
        for (Map.Entry<DepositsNameEnum, DepositProductShortInfo> pair : putEnumAndXpathToMap().entrySet()) {
            if (e.equals(pair.getKey())) {
                nameFromWeb = pair.getValue().getName();
                maxInterestRateFromWeb = pair.getValue().getMaxInterestRate();
                productDetailsFromWeb = pair.getValue().getProductDetails();
                maxDurationMonthLibertyChildFromWeb = pair.getValue().getMaxDurationMonths();
                amountMinLibertyChildFromWeb = pair.getValue().getAmountMin();
            }
        }

        return new DepositProductShortInfo(
                nameFromWeb,
                maxInterestRateFromWeb,
                "Подробная информация о депозите",
                maxDurationMonthLibertyChildFromWeb,
                amountMinLibertyChildFromWeb);
    }

    public DepositProductShortInfo getObjectFromBack(Enum e) {
        depositProductService.getDepositsFromPage();
        List<DepositProductShortInfo> depositProductShortInfoList = depositProductService.getDepositProductShortInfoList();
        putEnumAndXpathToMap();
        for (DepositProductShortInfo depositProductShortInfo : depositProductShortInfoList) {
            for (Map.Entry<DepositsNameEnum, DepositProductShortInfo> pair : putEnumAndXpathToMap().entrySet()) {
                if (e.equals(pair.getKey()) && depositProductShortInfo.getName().equals(pair.getValue().getName())) {
                    nameFromBackEnd = depositProductShortInfo.getName();
                }
                if (e.equals(pair.getKey()) && depositProductShortInfo.getMaxInterestRate().equals(pair.getValue().getMaxInterestRate())) {
                    maxInterestRateFromBackEnd = depositProductShortInfo.getMaxInterestRate();
                }
//                if(e.equals(pair.getKey()) && depositProductShortInfo.getProductDetails().equals(pair.getValue().getProductDetails())){
//                    productDetailsFromBackEnd = depositProductShortInfo.getProductDetails();
//                }
                if (e.equals(pair.getKey()) && depositProductShortInfo.getMaxDurationMonths().equals(pair.getValue().getMaxDurationMonths())) {
                    maxDurationMonthLibertyChildFromBackEnd = depositProductShortInfo.getMaxDurationMonths();
                }
                if (e.equals(pair.getKey()) && depositProductShortInfo.getAmountMin().equals(pair.getValue().getAmountMin())) {
                    amountMinLibertyChildFromBackEnd = depositProductShortInfo.getAmountMin();
                }
            }
        }
        return new DepositProductShortInfo(
                nameFromBackEnd,
                maxInterestRateFromBackEnd,
                "Подробная информация о депозите",
                maxDurationMonthLibertyChildFromBackEnd,
                amountMinLibertyChildFromBackEnd);
    }
}
