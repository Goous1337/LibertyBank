package web.pages.depositPages;

import api.model.webAndApi.DepositProductService;
import api.model.webAndApi.deposit.MyDepositProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.constans.DepositsConstants;
import web.drivers.DriverManager;
import web.pages.BasePage;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyDepositsProductsPage extends BasePage  {

    private DepositProductService depositProductService;
    private List<WebElement> nameWebElements;
    private List<WebElement> interestRateElements;
    private List<WebElement> currentBalanceElements;
    private List<WebElement> closeDateElements;
    private List<MyDepositProduct> myDepositProductList;
    private String nameFromWeb;
    private String nameFromBack;
    private Double interestRateFromWeb;
    private Double interestRateFromBack;
    private Double currentBalanceFromWeb;
    private Double currentBalanceFromBack;
    private Date closeDateFromWeb;
    private Date closeDateFromBack;


    public MyDepositsProductsPage() {
        depositProductService = new DepositProductService();
    }

    @FindBy(xpath = "//a[@href='/deposits']")
    private WebElement depositsButton;
    @FindBy(xpath = "//a[@href='/deposits/my-deposits']")
    private WebElement myDepositProductsBankButton;

    public void clickDepositsButton() {
        depositsButton.click();
    }

    public void clickMyDepositsButton() {
        myDepositProductsBankButton.click();
    }

    public List<MyDepositProduct> mapInitialization() {
        myDepositProductList = new ArrayList<MyDepositProduct>();
        nameWebElements = DriverManager.getDriver().findElements(By.xpath("//h2[contains(text(),'Liberty')]"));
        interestRateElements = DriverManager.getDriver().findElements(By.xpath("//h2[@data-testid='interestRate']"));
        currentBalanceElements = DriverManager.getDriver().findElements(By.xpath("//h2[@data-testid='currentBalance']"));
        closeDateElements = DriverManager.getDriver().findElements(By.xpath("//h2[@data-testid='closeData']"));

        for (int i = 0; i <= nameWebElements.size() - 1; i++) {
            myDepositProductList.add(
                    new MyDepositProduct(
                            nameWebElements.get(i).getText(),
                            convertToDouble(interestRateElements.get(i).getText()),
                            convertToDouble(currentBalanceElements.get(i).getText()),
                            parseDate(closeDateElements.get(i).getText())));
        }
        return myDepositProductList;
    }

    public MyDepositProduct objectFromWeb() {
        mapInitialization();
        for (MyDepositProduct myDepositProduct : mapInitialization()) {
            nameFromWeb = myDepositProduct.getName();
            interestRateFromWeb = myDepositProduct.getInterestRate();
            currentBalanceFromWeb = myDepositProduct.getCurrentBalance();
            closeDateFromWeb = myDepositProduct.getCloseDate();
        }
        return new MyDepositProduct(nameFromWeb, interestRateFromWeb, currentBalanceFromWeb, closeDateFromWeb);
    }

    public MyDepositProduct objectFromBack() {
        depositProductService.getMyDepositsFromPage();
        List<MyDepositProduct> myDepositProducts = depositProductService.getMyDepositProductsList();
        mapInitialization();
        for (MyDepositProduct myDepositProductFromBack:myDepositProducts) {
            for (MyDepositProduct myDepositProductFromWeb:mapInitialization()) {
                if (myDepositProductFromBack.getName().equals(myDepositProductFromWeb.getName()) &&
                        myDepositProductFromBack.getInterestRate().equals(myDepositProductFromWeb.getInterestRate()) &&
                        myDepositProductFromBack.getCurrentBalance().equals(myDepositProductFromWeb.getCurrentBalance())) {
                    nameFromBack = myDepositProductFromBack.getName();
                    interestRateFromBack = myDepositProductFromBack.getInterestRate();
                    currentBalanceFromBack = myDepositProductFromBack.getCurrentBalance();
                    closeDateFromBack = parseDate(myDepositProductFromBack.getStringDate(myDepositProductFromBack.getCloseDate()));
                }
            }
        }
        return new MyDepositProduct(nameFromBack, interestRateFromBack, currentBalanceFromBack, closeDateFromBack);
    }

    private static Double convertToDouble(String str) {
        str = String.valueOf(str).replace(DepositsConstants.DELIMITER, DepositsConstants.POINT).trim();
        str = String.valueOf(str).replace(DepositsConstants.PROCENT, "").trim();
        str = String.valueOf(str).replace(DepositsConstants.SPACE, "").trim();
        str = String.valueOf(str).replace(DepositsConstants.RUB, "").trim();
        str = String.valueOf(str).replace(DepositsConstants.EUR, "").trim();
        str = String.valueOf(str).replace(DepositsConstants.USD, "").trim();
        str = String.valueOf(str).replace("RUB", "").trim();
        return Double.parseDouble(str);
    }

    public static java.sql.Date parseDate(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DepositsConstants.DATE_FORMAT);
        java.sql.Date result = null;
        try {
            result = new java.sql.Date(dateFormat.parse(s).getTime());
        } catch (ParseException e) {
            System.err.println(e);
        }
        return result;
    }
}
