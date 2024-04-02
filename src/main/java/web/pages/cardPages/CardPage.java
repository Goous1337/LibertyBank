package web.pages.cardPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import java.util.List;

public class CardPage extends BasePage {

    @FindBy(xpath = "(//button[contains(text(), 'Информация по карте')])[2]")
    private WebElement cardInfoButtonForSecondCard;

    @FindBy(xpath = "//ul[@data-testid='cards-list']//p[contains(text(), 'Основная')]")
    private WebElement mainStatus;

    @FindBy(xpath = "(//p[contains(text(), 'Активная')])[1]")
    private WebElement activeStatus;

    @FindBy(xpath = "(//p[contains(text(), 'Активная')])[1]/ancestor::li/descendant::button")
    private WebElement cardInfoButtonForActiveCard;

    @FindBy(xpath = "(//p[contains(text(), 'Закрытая')])[1]")
    private WebElement closedStatus;

    @FindBy(xpath = "(//p[contains(text(), 'Закрытая')])[1]/ancestor::li/descendant::button")
    private WebElement cardInfoButtonForClosedCard;

    @FindBy(xpath = "(//p[contains(text(), 'Заблокированная')])[1]")
    private WebElement blockedStatus;

    @FindBy(xpath = "(//p[contains(text(), 'Заблокированная')])[1]/ancestor::li/descendant::button")
    private WebElement cardInfoButtonForBlockedCard;

    @FindBy(xpath = "(//h2[contains(text(), 'Liberty Card')])[1]")
    private WebElement cardType;

    @FindBy(xpath = "(//p[contains(@class, 'card-number')])[1]")
    private WebElement cardNumber;

    @FindBy(xpath = "(//span[contains(text(), 'Срок действия')]/preceding-sibling::span)[1]")
    private WebElement cardValidityPeriod;

    @FindBy(xpath = "(//span[contains(text(), 'Баланс')]/preceding-sibling::span)[1]")
    private WebElement cardBalance;

    @FindBy(xpath = "(//span[contains(text(), 'Валюта счета')]/preceding-sibling::span)[1]")
    private WebElement cardCurrency;

    @FindBy(xpath = "(//*[contains(@class, 'payment-system')])[1]")
    private WebElement cardPaymentSystem;

    @FindBy(xpath = "//span[contains(text(), 'Валюта счета')]/preceding-sibling::span")
    private List<WebElement> currencies;

    public boolean isMainStatusDisplayed() {
        return mainStatus.isDisplayed();
    }

    public boolean isBlockedStatusDisplayed() {
        return blockedStatus.isDisplayed();
    }

    public void clickCardInfoButtonForSecondCard() {
        cardInfoButtonForSecondCard.click();
    }

    public void clickCardInfoButtonForActiveCard() {
        cardInfoButtonForActiveCard.click();
    }

    public void clickCardInfoButtonForClosedCard() {
        scrollToElement(cardInfoButtonForClosedCard);
        cardInfoButtonForClosedCard.click();
    }

    public void clickCardInfoButtonForBlockedCard() {
        scrollToElement(cardInfoButtonForBlockedCard);
        cardInfoButtonForBlockedCard.click();
    }

    public boolean isCardTypeDisplayed() {
        return cardType.isDisplayed();
    }

    public boolean isCardNumberDisplayed() {
        return cardNumber.isDisplayed();
    }

    public boolean isCardValidityPeriodDisplayed() {
        return cardValidityPeriod.isDisplayed();
    }

    public boolean isCardBalanceDisplayed() {
        return cardBalance.isDisplayed();
    }

    public boolean isCardCurrencyDisplayed() {
        return cardCurrency.isDisplayed();
    }

    public boolean isCardPaymentSystemDisplayed() {
        return cardPaymentSystem.isDisplayed();
    }

    public String getCurrencyText() {
        return cardCurrency.getText();
    }

    public boolean isCurrencyEqualsExpected() {
        boolean currencyEquals = true;
        for (WebElement currency : currencies) {
            if (!currency.getText().equals(getCurrencyText())) {
                currencyEquals = false;
                break;
            }
        }
        return currencyEquals;
    }
}
