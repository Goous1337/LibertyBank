package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardPage extends BasePage{

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

    public boolean isMainStatusDisplayed() {
        return mainStatus.isDisplayed();
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
}
