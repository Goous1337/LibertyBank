package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePinCardPage extends BasePage {
    @FindBy(xpath = "//*[text()='Информация по карте']")
    private WebElement infoAboutCard;
    @FindBy(xpath = "//*[@data-testid='icon-copy-card']")
    private WebElement copyText;
    @FindBy(xpath = "//*[text()='Изменить пин-код']")
    private WebElement changPinCard;
    @FindBy(xpath = "//*[@name='oldPin']")
    private WebElement inputOldPinCard;
    @FindBy(xpath = "//*[@name='newPin']")
    private WebElement inputNewPinCard;
    @FindBy(xpath = "//*[@name='approvedPin']")
    private WebElement approvedNewPinCard;
    @FindBy(xpath = "//*[text()='Подтвердить']")
    private WebElement confirmButton;
    @FindBy(xpath = "//*[text()='Операция прошла успешно']")
    private WebElement successAnswer;
    @FindBy(xpath = "//*[text()='Вы ввели недопустимые символы']")
    private WebElement unSuccessAnswer;

    public void clickInfoCard() {
        infoAboutCard.click();
    }

    public void clickCopyNumber() {
        copyText.click();
    }

    public void clickChangePinCard() {
        changPinCard.click();
    }

    public void setInputOldPinCard(String oldPINCard) {
        inputOldPinCard.sendKeys(oldPINCard);
    }

    public void setInputNewPinCard(String newPinCode) {
        inputNewPinCard.sendKeys(newPinCode);
    }

    public void setApprovedNewPinCard(String newPinCode) {
        approvedNewPinCard.sendKeys(newPinCode);
    }

    public void clickConfirmButton() {
        confirmButton.click();
    }

    public boolean successAnswer() {
        return successAnswer.isDisplayed();
    }

    public boolean unSuccessAnswer() {
        return unSuccessAnswer.isDisplayed();
    }
}
