package web.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class CardInfoPage extends BasePage {

    @FindBy(xpath = "//label[contains(@class, '_switch-label')]")
    private WebElement setMainSwitchButton;

    @FindBy(xpath = "//p[contains(text(), 'Основная карта')]")
    private WebElement mainCardStatus;

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

    public void clickUnblockCardButton() {
        scrollToElement(unblockCardButton);
        unblockCardButton.click();
    }
}
