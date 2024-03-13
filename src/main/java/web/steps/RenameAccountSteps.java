package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.RenameAccountPage;

import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;

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
    public void assertEmptyMessageIsDisplayed() {
        Assertions.assertTrue(renameAccountPage.isEmptyMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается сообщение 'Измените название поля'")
    public void assertChangeFieldNameMessageDisplayed() {
        Assertions.assertTrue(renameAccountPage.isChangeFieldNameMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение"));
    }

    @Step("Отображается сообщение 'Вы не можете ввести более 30 символов'")
    public void assertMoreThenThirtyCharactersMessageIsDisplayed() {
        Assertions.assertTrue(renameAccountPage.isMoreThenThirtyCharactersMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается сообщение 'Вы ввели недопустимые символы'")
    public void assertInvalidCharactersMessageIsDisplayed() {
        Assertions.assertTrue(renameAccountPage.isInvalidCharactersMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается сообщение 'Вы не можете ввести больше 30 символов'")
    public boolean isMoreThan30SymbolsMessageDisplayed() {
        return renameAccountPage.isMoreThenThirtyCharactersMessageDisplayed();
    }
}
