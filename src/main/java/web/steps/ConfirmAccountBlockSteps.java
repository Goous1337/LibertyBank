package web.steps;

import io.qameta.allure.Step;
import web.pages.ConfirmationPage;

public class ConfirmAccountBlockSteps {

    protected ConfirmationPage confirmationPage;

    public ConfirmAccountBlockSteps() {
        confirmationPage = new ConfirmationPage();
    }

    @Step("Нажать на кнопку 'Да', чтобы подтвердить блокировку аккаунта")
    public void clickConfirmButton() {
        confirmationPage.clickConfirmButton();
    }

    @Step("Нажать на кнопку 'Нет', чтобы отменить блокировку аккаунта")
    public void clickDenyButton() {
        confirmationPage.clickDenyButton();
    }
}
