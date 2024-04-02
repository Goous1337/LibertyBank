package web.pages.cardPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;
import web.pages.BasePage;

public class CardInfoPage extends BasePage {

    @FindBy(xpath = "//label[contains(@class, '_switch-label')]")
    private WebElement setMainSwitchButton;

    @FindBy(xpath = "//p[contains(text(), 'Основная карта')]")
    private WebElement mainCardStatus;

    @FindBy(xpath = "//p[text()='Активная']")
    private WebElement activeCardStatus;

    @FindBy(xpath = "//p[text()='Заблокированная']")
    private WebElement blockedCardStatus;

    @FindBy(xpath = "//span[contains(text(), 'Назад')]")
    private WebElement backButton;

    @FindBy(xpath = "//p[contains(text(), 'Заблокировать карту')]")
    private WebElement blockCardButton;

    @FindBy(xpath = "//p[text()='Разблокировать карту']")
    private WebElement unblockCardButton;

    @FindBy(xpath = "//p[contains(text(), 'Закрыть карту')]")
    private WebElement closeCardButton;
    @FindBy(xpath = "//p[text() = 'Закрытая']")
    private WebElement statusCardClosed;
    @FindBy(xpath = "//p[text() = 'Активная']")
    private WebElement statusCardActive;

    @FindBy(xpath = "//p[contains(text(), 'Номер карты')]")
    private WebElement userCardNumber;

    @FindBy(xpath = "//p[contains(text(), 'Действительна до')]")
    private WebElement userCardValidityPeriod;

    @FindBy(xpath = "(//h2[contains(text(), 'Liberty Card')])")
    private WebElement userCardType;

    @FindBy(xpath = "(//*[@data-testid='icon-copy-card'])[1]")
    private WebElement copyCardNumber;

    @FindBy(xpath = "(//*[@data-testid='icon-copy-card'])[2]")
    private WebElement copyCvvCode;

    @FindBy(xpath = "//*[@data-testid='tab_История']")
    private WebElement cardTransactionHistoryButton;

    @FindBy(xpath = "//h2[contains(text(), 'Информация по карте')]")
    private WebElement cardInformationTitle;

    @FindBy(xpath = "//p[contains(text(), 'Детали счета')]")
    private WebElement accountInformationButton;

    @FindBy(xpath = "//p[contains(text(), 'Тариф')]")
    private WebElement cardTariffButton;

    @FindBy(xpath = "//p[contains(text(), 'Лимиты по карте')]")
    private WebElement cardLimitsButton;

    public void clickSetMainSwitchButton() {
        scrollToElement(setMainSwitchButton);
        setMainSwitchButton.click();
    }

    public boolean isSetMainSwitchButtonDisplayed() {
        return setMainSwitchButton.isDisplayed();
    }

    public boolean isMainCardStatusDisplayed() {
        scrollToElement(mainCardStatus);
        return mainCardStatus.isDisplayed();
    }

    public boolean statusCardActive() {
        scrollToElement(statusCardActive);
        return statusCardActive.isDisplayed();
    }

    public boolean isActiveCardStatusDisplayed() {
        Waiters.waitElement(activeCardStatus);
        scrollToElement(activeCardStatus);
        return activeCardStatus.isDisplayed();
    }

    public boolean isBlockedCardStatusDisplayed() {
        scrollToElement(blockedCardStatus);
        return blockedCardStatus.isDisplayed();
    }

    public void clickBackButton() {
        backButton.click();
    }

    public void clickBlockCardButton() {
        scrollToElement(blockCardButton);
        blockCardButton.click();
    }

    public void clickCloseCardButton() {
        scrollToElement(closeCardButton);
        closeCardButton.click();
    }

    public boolean statusCardClosed() {
        return statusCardClosed.isDisplayed();
    }

    public void clickUnblockCardButton() {
        scrollToElement(unblockCardButton);
        unblockCardButton.click();
    }

    public boolean isUserCardNumberDisplayed() {
        return userCardNumber.isDisplayed();
    }

    public boolean isUserCardValidityPeriodDisplayed() {
        return userCardValidityPeriod.isDisplayed();
    }

    public boolean isUserCardTypeDisplayed() {
        Waiters.waitElement(userCardNumber);
        return userCardType.isDisplayed();
    }

    public boolean isCopyCardNumberIconDisplayed() {
        return copyCardNumber.isDisplayed();
    }

    public boolean isCopyCvvCodeIconDisplayed() {
        return copyCvvCode.isDisplayed();
    }

    public boolean isCardTransactionHistoryButtonDisplayed() {
        Waiters.waitElement(cardTransactionHistoryButton);
        return cardTransactionHistoryButton.isDisplayed();
    }

    public boolean isCardInformationTitleDisplayed() {
        return cardInformationTitle.isDisplayed();
    }

    public boolean isCardLimitsDisplayed() {
        return cardLimitsButton.isDisplayed();
    }

    public boolean isAccountInformationButtonDisplayed() {
        return accountInformationButton.isDisplayed();
    }

    public boolean isCardTariffButtonDisplayed() {
        return cardTariffButton.isDisplayed();
    }

}
