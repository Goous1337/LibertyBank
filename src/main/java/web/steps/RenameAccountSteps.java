package web.steps;

import io.qameta.allure.Step;
import web.pages.RenameAccountPage;

public class RenameAccountSteps {

    public RenameAccountPage renameAccountPage;

    public RenameAccountSteps() {
        renameAccountPage = new RenameAccountPage();
    }

    @Step("Нажать на поле ввода 'Новое название счета'")
    public void clickNewAccountNameTextField() {
        renameAccountPage.clickNewAccountNameTextField();
    }

    @Step("Ввести значение в поле ввода 'Новое название счета'")
    public void setValueInNewAccountNameTextField(String value) {
        renameAccountPage.sendKeysNewAccountNameTextField(value);
    }

    @Step("Отменить переименование")
    public void cancel() {
        renameAccountPage.clickCancelButton();
    }

    @Step("Сохранить")
    public void save() {
        renameAccountPage.clickSaveButton();
    }

    @Step("Отображается сообщение 'Поле не должно быть пустым'")
    public boolean isEmptyMessageDisplayed() {
        return renameAccountPage.isEmptyMessageDisplayed();
    }

    @Step("Отображается сообщение 'Вы не можете ввести более 30 символов'")
    public boolean isMoreThenThirtyCharactersMessageDisplayed() {
        return renameAccountPage.isMoreThenThirtyCharactersMessageDisplayed();
    }

    @Step("Отображается сообщение 'Вы ввели недопустимые символы'")
    public boolean isInvalidCharactersMessageDisplayed() {
        return renameAccountPage.isInvalidCharactersMessageDisplayed();
    }
}
