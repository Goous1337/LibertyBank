package web.pages.creditPages;

import api.model.webAndApi.credit.CreditProduct;
import api.model.webAndApi.credit.MoreCreditProduct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.enums.CreditsEnum;
import web.pages.BasePage;

import java.util.EnumMap;
import java.util.List;

public class CreditInfoPage extends BasePage {

    public CreditInfoPage() {

    }

    @FindBy(xpath = "//a[text()='Кредиты']")
    private WebElement creditButton;
    /*Кнопки подразделов 'Кредиты'*/
    @FindBy(xpath = "//a/p[text()='Мои кредиты']")
    private WebElement myCreditButton;
    @FindBy(xpath = "//a/p[text()='Кредитные продукты банка']")
    private WebElement creditProductsBankButton;
    @FindBy(xpath = "//a/p[text()='Поданные заявки']")
    private WebElement submittedCreditАpplicationsButton;

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

}
