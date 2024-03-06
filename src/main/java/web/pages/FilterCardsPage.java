package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterCardsPage extends BasePage {

    @FindBy(xpath = "//div[text() = 'Все']")
    private WebElement allCardTypesButton;

    @FindBy(xpath = "//div[text() = 'Дебетовые']")
    private WebElement debitCardTypeButton;

    @FindBy(xpath = "//div[text() = 'Кредитные']")
    private WebElement creditCardTypeButton;

    @FindBy(xpath = "//span[text()='Все']")
    private WebElement allCardCurrenciesButton;

    @FindBy(xpath = "//span[text()='RUB']/parent::li")
    private WebElement rubCurrencyButton;

    @FindBy(xpath = "//span[text()='USD']/parent::li")
    private WebElement usdCurrencyButton;

    @FindBy(xpath = "//span[text()='EUR']/parent::li")
    private WebElement eurCurrencyButton;

    public void clickAllCardTypesButton() {
        allCardTypesButton.click();
    }

    public void clickDebitCardTypeButton() {
        debitCardTypeButton.click();
    }

    public void clickCreditCardTypeButton() {
        creditCardTypeButton.click();
    }

    public void clickAllCurrenciesButton() {
        allCardCurrenciesButton.click();
    }

    public void clickRubCurrencyButton() {
        rubCurrencyButton.click();
    }

    public void clickUsdCurrencyButton() {
        usdCurrencyButton.click();
    }

    public void clickEurCurrencyButton() {
        eurCurrencyButton.click();
    }
}
