package web.steps;

import io.qameta.allure.Step;
import web.pages.UpdateEmailPage;

public class UpdateEmailSteps {
    protected UpdateEmailPage updateEmailPage;

    public UpdateEmailSteps() {
        updateEmailPage = new UpdateEmailPage();
    }

    @Step("Заполнить поле новым email")
    public UpdateEmailPage sendKeysToEmailInput(String email) {
        return updateEmailPage.sendKeysToEmailInput(email);
    }

    @Step("Кликнуть по кнопке 'Отправить'")
    public void clickToSubmitBtn() {
        updateEmailPage.clickToSubmitBtn();
    }
}
