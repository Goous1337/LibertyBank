package web.pages.creditPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.pages.BasePage;

public class CreditInfoPage extends BasePage {

    @FindBy(xpath = "//a[text()='Кредиты']")
    private WebElement creditButton;
    /*Кнопки подразделов 'Кредиты'*/
    @FindBy(xpath = "//a/p[text()='Мои кредиты']")
    private WebElement myCreditButton;
    @FindBy(xpath = "//a/p[text()='Кредитные продукты']")
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
