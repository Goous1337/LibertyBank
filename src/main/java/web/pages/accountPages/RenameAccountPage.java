package web.pages.accountPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

public class RenameAccountPage extends BasePage {

    @FindBy(name = "name")
    private WebElement newAccountNameTextField;

    @FindBy(xpath = "//button[contains(text(), 'Отмена')]")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[contains(text(), 'Сохранить')]")
    private WebElement saveButton;

    @FindBy(xpath = "//p[contains(text(), 'Поле не должно быть пустым')]")
    private WebElement emptyFieldMessage;

    @FindBy(xpath = "//p[contains(text(), 'Измените название поля')]")
    private WebElement changeFieldName;

    @FindBy(xpath = "//p[contains(text(), 'Вы не можете ввести более 30 символов')]")
    private WebElement moreThanThirtyCharactersMessage;

    @FindBy(xpath = "//p[contains(text(), 'Вы ввели недопустимые символы')]")
    private WebElement invalidCharactersMessage;

    public void clickNewAccountNameTextField() {
        newAccountNameTextField.click();
    }

    public void sendKeysNewAccountNameTextField(String value) {
        newAccountNameTextField.sendKeys(value);
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public boolean isEmptyMessageDisplayed() {
        return emptyFieldMessage.isDisplayed();
    }

    public boolean isMoreThenThirtyCharactersMessageDisplayed() {
        return moreThanThirtyCharactersMessage.isDisplayed();
    }

    public boolean isInvalidCharactersMessageDisplayed() {
        return invalidCharactersMessage.isDisplayed();
    }

    public boolean isChangeFieldNameMessageDisplayed() {
        return changeFieldName.isDisplayed();
    }
}
