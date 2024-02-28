package web.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CardProductsPage extends BasePage {
    @FindBy(xpath = "//div[text() = 'Все']")
    private WebElement allCardTypesButton;
    @FindBy(xpath = "//span[text()='Все']")
    private WebElement allCardCurrenciesButton;
    @FindBy(xpath = "//span[text()='RUB']/parent::li")
    private WebElement rubCurrencyButton;
    @FindBy(xpath = "//span[text()='USD']/parent::li")
    private WebElement usdCurrencyButton;
    @FindBy(xpath = "//span[text()='EUR']/parent::li")
    private WebElement eurCurrencyButton;
    @FindBy(xpath = "//div[contains(@class, 'info__header')]")
    private List<WebElement> allCardsTitles;
    @FindBy(xpath = "//span[contains(text(), 'Срок')]/preceding-sibling::span")
    private List<WebElement> allCardsValidity;
    @FindBy(xpath = "//span[contains(text(), 'Обслуживание')]/preceding-sibling::span")
    private List<WebElement> allCardsServiceCost;
    @FindBy(xpath = "//span[contains(text(), 'Валюта')]/preceding-sibling::span")
    private List<WebElement> allCardsCurrencies;
    @FindBy(xpath = "//h2[text()='Liberty Card Classic']")
    private WebElement classicCardLabel;
    @FindBy(xpath = "//a[contains(@href, 'Classic')][1]")
    private WebElement classicCardOrderButton;
    @FindBy(xpath = "//a[contains(@href, 'Classic')][2]")
    private WebElement classicCardInfoButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Child']")
    private WebElement childCardLabel;
    @FindBy(xpath = "//a[contains(@href, 'Child')][1]")
    private WebElement childCardOrderButton;
    @FindBy(xpath = "//a[contains(@href, 'Child')][2]")
    private WebElement childCardInfoButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Gold']")
    private WebElement goldCardLabel;
    @FindBy(xpath = "//a[contains(@href, 'Gold')][1]")
    private WebElement goldCardOrderButton;
    @FindBy(xpath = "//a[contains(@href, 'Gold')][2]")
    private WebElement goldCardInfoButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Platinum']")
    private WebElement platinumCardLabel;
    @FindBy(xpath = "//a[contains(@href, 'Platinum')][1]")
    private WebElement platinumCardOrderButton;
    @FindBy(xpath = "//a[contains(@href, 'Platinum')][2]")
    private WebElement platinumCardInfoButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Virtual']")
    private WebElement virtualCardLabel;
    @FindBy(xpath = "//a[contains(@href, 'Virtual')][1]")
    private WebElement virtualCardOrderButton;
    @FindBy(xpath = "//a[contains(@href, 'Virtual')][2]")
    private WebElement virtualCardInfoButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Secure']")
    private WebElement secureCardLabel;
    @FindBy(xpath = "//a[contains(@href, 'Secure')][1]")
    private WebElement secureCardOrderButton;
    @FindBy(xpath = "//a[contains(@href, 'Secure')][2]")
    private WebElement secureCardInfoButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Travel']")
    private WebElement travelCardLabel;
    @FindBy(xpath = "//a[contains(@href, 'Travel')][1]")
    private WebElement travelCardOrderButton;
    @FindBy(xpath = "//a[contains(@href, 'Travel')][2]")
    private WebElement travelCardInfoButton;

    public void classicCardOrderButtonClick() {
        classicCardOrderButton.click();
    }

    public void classicCardInfoButtonClick() {
        classicCardInfoButton.click();
    }

    public void childCardOrderButtonClick() {
        childCardOrderButton.click();
    }

    public void childCardInfoButtonClick() {
        childCardInfoButton.click();
    }

    public void goldCardOrderButtonClick() {
        goldCardOrderButton.click();
    }

    public void goldCardInfoButtonClick() {
        goldCardInfoButton.click();
    }

    public void platinumCardOrderButtonClick() {
        platinumCardOrderButton.click();
    }

    public void platinumCardInfoButtonClick() {
        platinumCardInfoButton.click();
    }

    public void virtualCardOrderButtonClick() {
        virtualCardOrderButton.click();
    }

    public void virtualCardInfoButtonClick() {
        virtualCardInfoButton.click();
    }

    public void secureCardOrderButtonClick() {
        secureCardOrderButton.click();
    }

    public void secureCardInfoButtonClick() {
        secureCardInfoButton.click();
    }

    public void travelCardOrderButtonClick() {
        travelCardOrderButton.click();
    }

    public void travelCardInfoButtonClick() {
        travelCardInfoButton.click();
    }

    public void scrollToClassicCard() {
        scrollToElement(classicCardInfoButton);
    }

    public void scrollToChildCard() {
        scrollToElement(childCardInfoButton);
    }

    public void scrollToGoldCard() {
        scrollToElement(goldCardInfoButton);
    }

    public void scrollToPlatinumCard() {
        scrollToElement(platinumCardInfoButton);
    }

    public void scrollToVirtualCard() {
        scrollToElement(virtualCardInfoButton);
    }

    public void scrollToSecureCard() {
        scrollToElement(secureCardInfoButton);
    }

    public void scrollToTravelCard() {
        scrollToElement(travelCardInfoButton);
    }

    public boolean isClassicCardDisplayed() {
        try {
            return classicCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isChildCardDisplayed() {
        try {
            return childCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isGoldCardDisplayed() {
        try {
            return goldCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPlatinumCardDisplayed() {
        try {
            return platinumCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isVirtualCardDisplayed() {
        try {
            return virtualCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSecureCardDisplayed() {
        try {
            return secureCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isTravelCardDisplayed() {
        try {
            return travelCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void sortByAllCurrencies() {
        allCardCurrenciesButton.click();
    }

    public void sortByRub() {
        rubCurrencyButton.click();
    }

    public void sortByUsd() {
        usdCurrencyButton.click();
    }

    public void sortByEur() {
        eurCurrencyButton.click();
    }

    public boolean allCardsTitlesDisplayed() {
        for (WebElement title : allCardsTitles) {
            try {
                title.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    public boolean allCardsValidityDisplayed() {
        for (WebElement validity : allCardsValidity) {
            try {
                validity.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    public boolean allCardsServiceCostDisplayed() {
        for (WebElement cost : allCardsServiceCost) {
            try {
                cost.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    public boolean allCardsCurrenciesDisplayed() {
        for (WebElement currency : allCardsCurrencies) {
            try {
                currency.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }
}