package web.steps;

import io.qameta.allure.Step;
import web.helpers.TestListener;
import web.pages.CloseCardPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.NOT_ENABLED_MESSAGE;

public class CloseCardSteps {
    protected CloseCardPage closeCardPage;

    public CloseCardSteps() {
        closeCardPage = new CloseCardPage();
    }

    @Step("Ввести код из СМС")
    public void enterCode(String password) {
        closeCardPage.clickPlaceholderInput();
        closeCardPage.enterPlaceholderInput(password);
    }

    @Step("Подтвердить закрытие карты")
    public void clickConfirmCloseCardButton() {
        closeCardPage.clickConfirmCloseCardButton();
    }

    @Step("Активна кнопка 'Подтвердить'")
    public void assertButtonConfirmActive() {
        assertTrue(closeCardPage.confirmButtonIsDisplayed(), String.format(NOT_ENABLED_MESSAGE, "Подтвердить"));
        TestListener.takeScreenshot();
    }
}
