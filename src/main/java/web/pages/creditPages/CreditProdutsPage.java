package web.pages.creditPages;

import api.model.webAndApi.CreditProductService;
import api.model.webAndApi.credit.CreditProduct;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.constans.credit.creditEnums.CreditNameEnum;
import web.pages.BasePage;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static web.helpers.Converter.*;

@Getter
@Setter
public class CreditProdutsPage extends BasePage {

    public CreditProductService creditProductService;
    private String nameOfCreditProductFromBackEnd;
    private Double interestRateFromBackEnd;
    private EnumMap<CreditNameEnum, CreditProduct> enumMapObject;
    private String nameFromWeb;
    private Double getInterestRateFromWeb;
    private String detailsFromWeb;
    private String detailsFromBack;
    private Double minSumFromWeb;
    private Double minSumFromBack;
    private Integer maxDurationMonthFromWeb;
    private Integer maxDurationMonthFromBack;

    public CreditProdutsPage() {
        creditProductService = new CreditProductService();
    }

    //Name of Credits from UI
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Наличными')]")
    private WebElement nameShortCreditCashProductPageText;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Срочный')]")
    private WebElement nameShortCreditExpressProductPageText;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Money')]")
    private WebElement nameShortCreditMoneyProductPageText;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Easy')]")
    private WebElement nameShortCreditEasyProductPageText;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Car')]")
    private WebElement nameShortCreditCarProductPageText;
    @FindBy(xpath = "//h3[contains(text(), 'Моя Квартира')]")
    private WebElement nameShortCreditMyFlatProductPageText;
    //Interest Rate Credits from UI
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Наличными')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement interestRateLibertyCash;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Срочный')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement interestRateLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Money')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement interestRateLibertyMoney;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Easy')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement interestRateLibertyEasy;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Car')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement interestRateLibertyCar;
    @FindBy(xpath = "//h3[contains(text(), 'Моя Квартира')]/ancestor::li//h3[@data-testid = 'interestRate']")
    private WebElement interestRateLibertyMyFlat;
    //Details of Credits from UI
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Money')]/ancestor::li//p[@data-testid = 'productDetails']")
    private WebElement detailsLibertyMoney;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Easy')]/ancestor::li//p[@data-testid = 'productDetails']")
    private WebElement detailsLibertyEasy;
    @FindBy(xpath = "//h3[contains(text(), 'Моя Квартира')]/ancestor::li//p[@data-testid = 'productDetails']")
    private WebElement detailsLibertyMyFlat;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Наличными')]/ancestor::li//p[@data-testid = 'productDetails']")
    private WebElement detailsLibertyCash;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Car')]/ancestor::li//p[@data-testid = 'productDetails']")
    private WebElement detailsLibertyCar;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Срочный')]/ancestor::li//p[@data-testid = 'productDetails']")
    private WebElement detailsLibertyExpress;
    //Min amount of Credits from UI
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Money')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinLibertyMoney;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Easy')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinLibertyEasy;
    @FindBy(xpath = "//h3[contains(text(), 'Моя Квартира')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinMyFlat;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Наличными')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinLibertyCash;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Car')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinLibertyCar;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Срочный')]/ancestor::li//h3[@data-testid = 'amountMin']")
    private WebElement amountMinLibertyExpress;
    //Max duration of Credits from UI
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Money')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthLibertyMoney;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Easy')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthLibertyEasy;
    @FindBy(xpath = "//h3[contains(text(), 'Моя Квартира')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthLibertyMyFlat;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Наличными')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthLibertyCash;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Car')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthLibertyCar;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Срочный')]/ancestor::li//h3[@data-testid = 'maxDurationMonth']")
    private WebElement maxDurationMonthLibertyExpress;

    //Кнопки 'Показать больше' в разделе кредитные продукты банка
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Наличными')]/ancestor::div/following-sibling::div/button[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyCash;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Срочный')]/ancestor::div/following-sibling::div/button[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyExpress;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Money')]/ancestor::div/following-sibling::div/button[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyMoney;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Easy')]/ancestor::div/following-sibling::div/button[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyEasy;
    @FindBy(xpath = "//h3[contains(text(), 'Liberty Car')]/ancestor::div/following-sibling::div/button[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreLibertyCar;
    @FindBy(xpath = "//h3[contains(text(), 'Моя Квартира')]/ancestor::div/following-sibling::div/button[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreMyFlat;
    @FindBy(xpath = "//li[1]/div[3]/button[contains(text(), 'Подать заявку')]")
    private WebElement buttonGetApplicationLibertyCash;
    @FindBy(xpath = "//li[2]/div[3]/button[contains(text(), 'Подать заявку')]")
    private WebElement buttonGetApplicationLibertyExpress;

    public EnumMap<CreditNameEnum, CreditProduct> putEnumAndXpathToMap() {
        enumMapObject = new EnumMap<>(CreditNameEnum.class);
        enumMapObject.put(
                CreditNameEnum.LIBERTY_CASH,
                new CreditProduct(
                        nameShortCreditCashProductPageText.getText(),
                        convertToDouble(interestRateLibertyCash.getText()),
                        detailsLibertyCash.getText(),
                        convertToDouble(amountMinLibertyCash.getText()),
                        convertToInteger(maxDurationMonthLibertyCash.getText())));
        enumMapObject.put(CreditNameEnum.LIBERTY_EXPRESS,
                new CreditProduct(
                        nameShortCreditExpressProductPageText.getText(),
                        convertToDouble(interestRateLibertyExpress.getText()),
                        detailsLibertyExpress.getText(),
                        convertToDouble(amountMinLibertyExpress.getText()),
                        convertToInteger(maxDurationMonthLibertyExpress.getText())));
        enumMapObject.put(
                CreditNameEnum.LIBERTY_MONEY,
                new CreditProduct(
                        nameShortCreditMoneyProductPageText.getText(),
                        convertToDouble(interestRateLibertyMoney.getText()),
                        detailsLibertyMoney.getText(),
                        convertCurrencyValueToDouble(amountMinLibertyMoney.getText()),
                        convertToInteger(maxDurationMonthLibertyMoney.getText())));
        enumMapObject.put(
                CreditNameEnum.LIBERTY_EASY,
                new CreditProduct(
                        nameShortCreditEasyProductPageText.getText(),
                        convertToDouble(interestRateLibertyEasy.getText()),
                        detailsLibertyEasy.getText(),
                        convertToDouble(amountMinLibertyEasy.getText()),
                        convertToInteger(maxDurationMonthLibertyEasy.getText())));
        enumMapObject.put(
                CreditNameEnum.LIBERTY_CAR,
                new CreditProduct(
                        nameShortCreditCarProductPageText.getText(),
                        convertToDouble(interestRateLibertyCar.getText()),
                        detailsLibertyCar.getText(),
                        convertToDouble(amountMinLibertyCar.getText()),
                        convertToInteger(maxDurationMonthLibertyCar.getText())));
        enumMapObject.put(
                CreditNameEnum.LIBERTY_MY_FLAT,
                new CreditProduct(
                        nameShortCreditMyFlatProductPageText.getText(),
                        convertToDouble(interestRateLibertyMyFlat.getText()),
                        detailsLibertyMyFlat.getText(),
                        convertToDouble(amountMinMyFlat.getText()),
                        convertToInteger(maxDurationMonthLibertyMyFlat.getText())));
        return enumMapObject;
    }

    public CreditProduct getObjectFromWeb(Enum e) {
        for (Map.Entry<CreditNameEnum, CreditProduct> pair : putEnumAndXpathToMap().entrySet()) {
            if (e.equals(pair.getKey())) {
                nameFromWeb = pair.getValue().getName();
                getInterestRateFromWeb = pair.getValue().getInterestRate();
                detailsFromWeb = pair.getValue().getDetails();
                minSumFromWeb = pair.getValue().getMinSum();
                maxDurationMonthFromWeb = pair.getValue().getMaxPeriodMonths();
            }
        }
        return new CreditProduct(nameFromWeb, getInterestRateFromWeb, detailsFromWeb, minSumFromWeb, maxDurationMonthFromWeb);
    }

    public CreditProduct    getObjectFromBackEnd(Enum getNameFromXpath) {
        creditProductService.getProductsCredit();
        List<CreditProduct> list = creditProductService.getCreditProductsList();
        putEnumAndXpathToMap();
        for (CreditProduct creditProduct : list) {
            for (Map.Entry<CreditNameEnum, CreditProduct> pair : putEnumAndXpathToMap().entrySet()) {
                if (getNameFromXpath.equals(pair.getKey()) && creditProduct.getName().equals(pair.getValue().getName())) {
                    nameOfCreditProductFromBackEnd = creditProduct.getName();
                }
                if (getNameFromXpath.equals(pair.getKey()) && creditProduct.getInterestRate().equals(pair.getValue().getInterestRate())) {
                    interestRateFromBackEnd = creditProduct.getInterestRate();
                }
                if (getNameFromXpath.equals(pair.getKey()) && creditProduct.getDetails().equals(pair.getValue().getDetails())) {
                    detailsFromBack = creditProduct.getDetails();
                }
                if (getNameFromXpath.equals(pair.getKey()) && creditProduct.getMinSum().equals(pair.getValue().getMinSum())) {
                    minSumFromBack = creditProduct.getMinSum();
                }
                if (getNameFromXpath.equals(pair.getKey()) && creditProduct.getMaxPeriodMonths().equals(pair.getValue().getMaxPeriodMonths())) {
                    maxDurationMonthFromBack = creditProduct.getMaxPeriodMonths();
                }
            }
        }
        return new CreditProduct(nameOfCreditProductFromBackEnd, interestRateFromBackEnd, detailsFromBack, minSumFromBack, maxDurationMonthFromBack);
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
