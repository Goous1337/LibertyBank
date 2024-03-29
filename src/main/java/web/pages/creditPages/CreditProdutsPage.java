package web.pages.creditPages;

import api.model.webAndApi.CreditProductService;
import api.model.webAndApi.credit.CreditProduct;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.enums.CreditsEnum;
import web.pages.BasePage;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static web.helpers.Converter.convertToDouble;

@Getter
@Setter
public class CreditProdutsPage extends BasePage {

    public CreditProductService creditProductService;
    private String nameOfCreditProductFromBackEnd;
    private Double interestRateFromBackEnd;
    private EnumMap<CreditsEnum, CreditProduct> enumMapObject;
    private String nameFromWeb;
    private Double getInterestRateFromWeb;

    public CreditProdutsPage() {
        creditProductService = new CreditProductService();
    }

    /*Краткая информация по Кредитным продуктам*/
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
    @FindBy(xpath = "//span[@data-testid='Liberty Наличными']")
    private WebElement interestRateLibertyCash;
    @FindBy(xpath = "//span[@data-testid='Liberty Срочный']")
    private WebElement interestRateLibertyExpress;
    @FindBy(xpath = "//span[@data-testid='Liberty Money']")
    private WebElement interestRateLibertyMoney;
    @FindBy(xpath = "//span[@data-testid='Liberty Easy']")
    private WebElement interestRateLibertyEasy;
    @FindBy(xpath = "//span[@data-testid='Liberty Car']")
    private WebElement interestRateLibertyCar;
    @FindBy(xpath = "//span[@data-testid='Моя Квартира']")
    private WebElement interestRateLibertyMyFlat;
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
    @FindBy(xpath = "//li[./span[@data-testid='Моя Квартира']]//button/span[contains(text(), 'Показать больше')]")
    private WebElement buttonShowMoreMyFlat;
    @FindBy(xpath = "//li[1]/div[3]/button[contains(text(), 'Подать заявку')]")
    private WebElement buttonGetApplicationLibertyCash;
    @FindBy(xpath = "//li[2]/div[3]/button[contains(text(), 'Подать заявку')]")
    private WebElement buttonGetApplicationLibertyExpress;

    /*Краткая информация по Кредитным продуктам банка*/
    public EnumMap<CreditsEnum, CreditProduct> putEnumAndXpathToMap() {
        enumMapObject = new EnumMap<>(CreditsEnum.class);
        enumMapObject.put(
                CreditsEnum.LIBERTY_CASH,
                new CreditProduct(
                        nameShortCreditCashProductPageText.getText(),
                        convertToDouble(interestRateLibertyCash.getText())));
        enumMapObject.put(CreditsEnum.LIBERTY_EXPRESS,
                new CreditProduct(
                        nameShortCreditExpressProductPageText.getText(),
                        convertToDouble(interestRateLibertyExpress.getText())));
        enumMapObject.put(
                CreditsEnum.LIBERTY_MONEY,
                new CreditProduct(
                        nameShortCreditMoneyProductPageText.getText(),
                        convertToDouble(interestRateLibertyMoney.getText())));
        enumMapObject.put(
                CreditsEnum.LIBERTY_EASY,
                new CreditProduct(
                        nameShortCreditEasyProductPageText.getText(),
                        convertToDouble(interestRateLibertyEasy.getText())));
        enumMapObject.put(
                CreditsEnum.LIBERTY_CAR,
                new CreditProduct(
                        nameShortCreditCarProductPageText.getText(),
                        convertToDouble(interestRateLibertyCar.getText())));
        enumMapObject.put(
                CreditsEnum.LIBERTY_MY_FLAT,
                new CreditProduct(
                        nameShortCreditMyFlatProductPageText.getText(),
                        convertToDouble(interestRateLibertyMyFlat.getText())));
        return enumMapObject;
    }

    public CreditProduct getObjectFromWeb(Enum e) {
        for (Map.Entry<CreditsEnum, CreditProduct> pair : putEnumAndXpathToMap().entrySet()) {
            if (e.equals(pair.getKey())) {
                nameFromWeb = pair.getValue().getName();
                getInterestRateFromWeb = pair.getValue().getInterestRate();
            }
        }
        return new CreditProduct(nameFromWeb, getInterestRateFromWeb);
    }

    public CreditProduct getObjectFromBackEnd(Enum getNameFromXpath) {
        creditProductService.getProductsCredit();
        List<CreditProduct> list = creditProductService.getCreditProductsList();
        putEnumAndXpathToMap();
        for (CreditProduct creditProduct : list) {
            for (Map.Entry<CreditsEnum, CreditProduct> pair : putEnumAndXpathToMap().entrySet()) {
                if (getNameFromXpath.equals(pair.getKey()) && creditProduct.getName().equals(pair.getValue().getName())) {
                    nameOfCreditProductFromBackEnd = creditProduct.getName();
                }
                if (getNameFromXpath.equals(pair.getKey()) && creditProduct.getInterestRate().equals(pair.getValue().getInterestRate())) {
                    interestRateFromBackEnd = creditProduct.getInterestRate();
                }
            }
        }
        return new CreditProduct(nameOfCreditProductFromBackEnd, interestRateFromBackEnd);
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
