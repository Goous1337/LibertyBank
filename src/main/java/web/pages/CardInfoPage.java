package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

import java.util.List;

public class CardInfoPage extends BasePage {
    @FindBy(xpath = "//h2[@data-testid]")
    private WebElement cardTitle;
    @FindBy(xpath = "//span[contains(text(), 'Срок')]/preceding-sibling::span")
    private WebElement cardValidity;
    @FindBy(xpath = "//span[contains(text(), 'Обслуживание карты')]/preceding-sibling::span")
    private WebElement cardServiceCost;
    @FindBy(xpath = "//span[contains(text(), 'Валюта счета')]/preceding-sibling::span")
    private WebElement cardCurrency;
    @FindBy(xpath = "//button[text()='Оформить карту']")
    private WebElement cardOrderButton;
    @FindBy(xpath = "//*[contains(@href, 'icon-cards-ps')]")
    private List<WebElement> cardPaymentSystems;

    public String getCardTitle() {
        Waiters.waitElement(cardTitle);
        return cardTitle.getText();
    }

    public String getValidity() {
        Waiters.waitElement(cardValidity);
        return cardValidity.getText();
    }

    public String getServiceCost() {
        Waiters.waitElement(cardServiceCost);
        return cardServiceCost.getText();
    }

    public String getCardCurrency() {
        Waiters.waitElement(cardCurrency);
        return cardCurrency.getText();
    }
}
