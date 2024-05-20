package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.ChangePinCardPage;

public class ChangePinSteps {
    protected ChangePinCardPage changePinCardPage;

    public ChangePinSteps() {
        changePinCardPage = new ChangePinCardPage();
    }

    @Step("Нажать кнопку 'Информация по карте' ")
    public void clickInfoAboutCard() {
        changePinCardPage.clickInfoCard();
    }

    @Step("Нажать 'Копировать значок номер карты' ")
    public void clickCopyNumber() {
        changePinCardPage.clickCopyNumber();
    }

    @Step("Нажать кнопку 'Изменить пин-код' ")
    public void clickChangePinCard() {
        changePinCardPage.clickChangePinCard();
    }

    @Step("Ввести старый PIN-код")
    public void setInputOldPinCard(String oldPin) {
        changePinCardPage.setInputOldPinCard(oldPin);
    }

    @Step("Ввести новый PIN-код")
    public void setKeysToNewPinInput(String newPinCode) {
        changePinCardPage.setInputNewPinCard(newPinCode);
    }

    @Step("Подтвердить новый PIN-код")
    public void setApprovedNewPinCardInput(String newPinCode) {
        changePinCardPage.setApprovedNewPinCard(newPinCode);
    }

    @Step("Нажать кнопку 'Подтвердить' ")
    public void clickConfirmButton() {
        changePinCardPage.clickConfirmButton();
    }

    @Step("Отображается надпись об успешной смене пароля ")
    public void assertSuccessAnswer() {
        Assertions.assertTrue(changePinCardPage.successAnswer(), "PIN-код не изменен");
    }

    @Step("Отображается надпись 'Вы ввели недопустимые символы' ")
    public void assertUnSuccessAnswer() {
        Assertions.assertTrue(changePinCardPage.unSuccessAnswer(), "Вы ввели допустимые символы");
    }
}
