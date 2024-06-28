package web.pages.creditPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;
import web.pages.BasePage;

public class MyCreditsPage extends BasePage {
    @FindBy(xpath = "//li[1]//h2[@data-testid='currentBalance']")
    private WebElement sumCreditText;
    @FindBy(xpath = "//li[1]//p[contains(text(), 'Сумма кредита')]//preceding::h2[2]")
    private WebElement nameCreditText;
    @FindBy(xpath = "//li[1]//h2[@data-testid='closeData']")
    private WebElement termCreditText;
    @FindBy(xpath = "//li[1]//button")
    private WebElement showMoreAboutCreditButton;

    /*Подраздел 'Мои кредиты'*/
    public boolean isMyCreditSumTextDisplayed() {
        return sumCreditText.isDisplayed();
    }

    public boolean isNameCreditText() {
        return nameCreditText.isDisplayed();
    }

    public boolean isTermCreditText() {
        return termCreditText.isDisplayed();
    }

    public boolean isShowMoreAboutCreditButton() {
        return showMoreAboutCreditButton.isDisplayed();
    }

    public void clickShowMoreMyCreditButton() {
        Waiters.waitElement(showMoreAboutCreditButton);
        showMoreAboutCreditButton.click();
    }
}
