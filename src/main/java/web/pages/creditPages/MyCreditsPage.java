package web.pages.creditPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

public class MyCreditsPage extends BasePage {
    @FindBy(xpath = "//h2[@data-testid='currencyBlockText']")
    private WebElement sumCreditText;
    @FindBy(xpath = "//div/h3")
    private WebElement nameCreditText;
    @FindBy(xpath = "//ul//li[1]/div/p[@data-testid='creditTerm']")
    private WebElement termCreditText;
    @FindBy(xpath = "//ul//li[1]//div/a")
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
        showMoreAboutCreditButton.click();
    }
}
