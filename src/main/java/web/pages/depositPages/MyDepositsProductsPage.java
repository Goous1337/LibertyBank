package web.pages.depositPages;

import api.model.webAndApi.DepositProductService;
import api.model.webAndApi.deposit.MyDepositProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.pages.BasePage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static web.helpers.Converter.convertToDouble;
import static web.helpers.Converter.parseDate;


public class MyDepositsProductsPage extends BasePage {

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
    @FindBy(xpath = "//li[1]//button")
    private WebElement showMoreAboutMyDepositButton;

    public void clickDepositsButton() {
        depositsButton.click();
    }

    public void clickMyDepositsButton() {
        myDepositProductsBankButton.click();
    }
    public void clickShowMoreAboutMyDepositButton(){
        showMoreAboutMyDepositButton.click();
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
        for (MyDepositProduct myDepositProductFromBack : myDepositProducts) {
            for (MyDepositProduct myDepositProductFromWeb : mapInitialization()) {
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
}
