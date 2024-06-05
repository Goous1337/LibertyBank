package web.pages.cardPages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import java.util.Arrays;
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
    @FindBy(xpath = "//a[@href='/cards']")
    private WebElement allCardsButton;
    @FindBy(xpath = "//p[contains(text(), 'Карточные продукты')]")
    private WebElement cardProductButton;
    @FindBy(xpath = "//div[contains(text(), 'Кредитная')]")
    private WebElement cardCreditButton;
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
    @FindBy(xpath = "//h2[text()='Liberty Сard Classic']")
    private WebElement cardClassic;
    @FindBy(xpath = "//h2[text()='Liberty Card Classic']/parent::div/following-sibling::div/a/button[text()='Оформить карту']")
    private WebElement cardClassicOrderButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Classic']/parent::div/following-sibling::div/a/button[text()='Показать больше']")
    private WebElement cardClassicInfoButton;
    @FindBy(xpath = "//h2[text()='Liberty Home Fix Card']")
    private WebElement fixHomeCardLabel;
    @FindBy(xpath = "//h2[text()='Liberty Home Fix Card']/parent::div/following-sibling::div/a/button[text()='Оформить карту']")
    private WebElement fixHomeCardOrderButton;
    @FindBy(xpath = "//h2[text()='Liberty Home Fix Card']/parent::div/following-sibling::div/a/button[text()='Показать больше']")
    private WebElement fixHomeCardInfoButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Premium']")
    private WebElement premiumCardLabel;
    @FindBy(xpath = "//h2[text()='Liberty Card Premium']/parent::div/following-sibling::div/a/button[text()='Оформить карту']")
    private WebElement premiumCardOrderButton;
    @FindBy(xpath = "//h2[text()='Liberty Card Premium']/parent::div/following-sibling::div/a/button[text()='Показать больше']")
    private WebElement premiumCardInfoButton;
    @FindBy(xpath = "//h2[@data-testid='card-product-title']")
    private WebElement creditNameSelector;
    @FindBy(xpath = "//div[@data-testid='card-cost-per-month']")
    private WebElement percentSelector;
    @FindBy(xpath = "//div[@data-testid='card-cost-per-month']")
    private WebElement limitSelector;
    @FindBy(xpath = "///div[@data-testid='card-currency']")
    private WebElement currencySelector;

    List<WebElement> creditNameElements = findCreditNameElements();
    List<WebElement> percentElements = findPercentElements();
    List<WebElement> limitElements = findLimitElements();
    List<WebElement> currencyElements = findCurrencyElements();

    // Ожидаемые значения
    List<String> expectedCreditNames = Arrays.asList("Liberty Card Classic", "Liberty Home Fix Card", "Liberty Card Premium");
    List<String> expectedPercents = Arrays.asList("15.5%", "12.5%", "11.5%");
    List<String> expectedLimits = Arrays.asList("до 300 000 ₽", "до 500 000 ₽", "до 1 000 000 ₽");
    List<String> expectedCurrencies = Arrays.asList("RUB", "RUB", "RUB");

    private List<WebElement> findElements(By xpath) {
        return null;
    }

    // Находим все элементы с помощью заданных селекторов
    public List<WebElement> findCreditNameElements() {
        return findElements(By.xpath("//h2[@data-testid='card-product-title']"));
    }

    public List<WebElement> findPercentElements() {
        return findElements(By.xpath("//div[@data-testid='card-cost-per-month']"));
    }

    public List<WebElement> findLimitElements() {
        return findElements(By.xpath("//div[@data-testid='card-cost-per-month']"));
    }

    public List<WebElement> findCurrencyElements() {
        return findElements(By.xpath("//div[@data-testid='card-currency']"));
    }

    public boolean findElements() {
        // Проверка соответствия фактических и ожидаемых значений для каждой карты
        for (WebElement creditNameElement : creditNameElements) {
            String actualCreditName = creditNameElement.getText();
            int index = creditNameElements.indexOf(creditNameElement); // Индекс текущего элемента в списке
            String actualPercent = percentElements.get(index).getText();
            String actualLimit = limitElements.get(index).getText();
            String actualCurrency = currencyElements.get(index).getText();
            // Проверка названия карты
            if (actualCreditName.equals(expectedCreditNames.get(index))) {
                System.out.println("Название кредита " + actualCreditName + " соответствует ожидаемому");
            } else {
                System.out.println("Название кредита " + actualCreditName + " не соответствует ожидаемому");
            }
            // Проверка процентной ставки
            if (actualPercent.equals(expectedPercents.get(index))) {
                System.out.println("Процент " + actualPercent + " соответствует ожидаемому");
            } else {
                System.out.println("Процент " + actualPercent + " не соответствует ожидаемому");
            }
            // Проверка лимита
            if (actualLimit.equals(expectedLimits.get(index))) {
                System.out.println("Лимит " + actualLimit + " соответствует ожидаемому");
            } else {
                System.out.println("Лимит " + actualLimit + " не соответствует ожидаемому");
            }
            // Проверка валюты
            if (actualCurrency.equals(expectedCurrencies.get(index))) {
                System.out.println("Валюта " + actualCurrency + " соответствует ожидаемой");
            } else {
                System.out.println("Валюта " + actualCurrency + " не соответствует ожидаемой");
            }
        }
        return false;
    }

    public void clickAllCardsButton() {
        allCardsButton.click();
    }

    public void clickCardProductButton() {
        cardProductButton.click();
    }

    public void clickCardCreditButton() {
        cardCreditButton.click();
    }

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

    public boolean isCardClassicDisplayed() {
        try {
            return cardClassic.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isFixHomeCardDisplayed() {
        try {
            return fixHomeCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPremiumCardLabelDisplayed() {
        try {
            return premiumCardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
