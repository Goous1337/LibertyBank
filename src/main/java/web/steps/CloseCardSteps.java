package web.steps;

import io.qameta.allure.Step;
import web.helpers.TestListener;
import web.pages.CloseCardPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Step("Кнопка 'Подтвердить' активна")
    public void assertButtonConfirmActive() {
        assertTrue(closeCardPage.confirmButtonIsDisplayed(), String.format("Кнопка 'Подтвердить' активна"));
        TestListener.takeScreenshot();
    }
}
