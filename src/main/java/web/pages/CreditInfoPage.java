package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;
public class CreditInfoPage extends BasePage {

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
    @FindBy(xpath = "//li[1]/div[3]/button/span")
    private WebElement buttonShowMoreLibertyCash;
    @FindBy(xpath = "//li[2]/div[3]/button/span")
    private WebElement buttonShowMoreLibertyExpress;
    @FindBy(xpath = "//li[1]/div[3]/button[contains(text(), 'Подать заявку')]")
    private WebElement buttonGetApplicationLibertyCash;
    @FindBy(xpath = "//li[2]/div[3]/button[contains(text(), 'Подать заявку')]")
    private WebElement buttonGetApplicationLibertyExpress;
    @FindBy(xpath = "//a[@data-testid='nav-link-1']")
    private WebElement creditProductsBankButton;

    /*
        Liberty Cash
     */
    public String getLibertyCashCreditName(){
        Waiters.waitElement(nameOfLibertyCashCredit);
        return nameOfLibertyCashCredit.getText();
    }
    public String getLibertyCashProcent(){
        Waiters.waitElement(procentOfLibertyCashCredit);
        return procentOfLibertyCashCredit.getText();
    }
    public boolean getDescriptionLibertyCash(){
        Waiters.waitElement(descriptionLibertyCash);
        return descriptionLibertyCash.isDisplayed();
    }
    public boolean isShowMoreLibertyCash(){
        Waiters.waitElement(buttonShowMoreLibertyCash);
        return buttonShowMoreLibertyCash.isDisplayed();
    }
    public boolean isGetApplicationLibertyCash(){
        Waiters.waitElement(buttonGetApplicationLibertyCash);
        return buttonGetApplicationLibertyCash.isDisplayed();
    }

    /*
        liberty Express
     */
    public String getLibertyExpressCreditName(){
        Waiters.waitElement(nameOfLibertyExpress);
        return nameOfLibertyExpress.getText();
    }
    public String getLibertyExpressProcent(){
        Waiters.waitElement(procentOfLibertyExpress);
        return procentOfLibertyExpress.getText();
    }
    public boolean getDescriptionLibertyExpress(){
        Waiters.waitElement(descriptionLibertyExpress);
        return descriptionLibertyExpress.isDisplayed();
    }
    public boolean isShowMoreLibertyExpress(){
        Waiters.waitElement(buttonShowMoreLibertyExpress);
        return buttonShowMoreLibertyExpress.isDisplayed();
    }
    public boolean isGetApplicationLibertyExpress(){
        Waiters.waitElement(buttonGetApplicationLibertyExpress);
        return buttonGetApplicationLibertyExpress.isDisplayed();
    }

}

