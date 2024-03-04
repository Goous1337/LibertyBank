package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import web.helpers.TestListener;
import web.pages.CardPage;

import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;

public class CardSteps {

    protected CardPage cardPage;

    public CardSteps() {
        cardPage = new CardPage();
    }

    @Step("Отображается статус карты 'Основная'")
    public void assertMainStatusDisplayed() {
        Assertions.assertTrue(cardPage.isMainStatusDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус 'Основная'"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается статус карты 'Основная'")
    public void assertMainStatusNotDisplayed() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            cardPage.isMainStatusDisplayed();
        });
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус карты 'Заблокированная'")
    public void assertBlockedStatusDisplayed() {
        Assertions.assertTrue(cardPage.isBlockedStatusDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статаус 'Заблокированная'"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается статус карты 'Заблокированная'")
    public void assertBlockedStatusNotDisplayed() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            cardPage.isBlockedStatusDisplayed();
        });
        TestListener.takeScreenshot();
    }

    @Step("Выбрать 'Информация по карте' для второй по счету карты")
    public void clickCardInfoForSecondCard() {
        cardPage.clickCardInfoButtonForSecondCard();
    }

    @Step("Выбрать 'Информация по карте' для активной карты")
    public void clickCardInfoForActiveCard() {
        cardPage.clickCardInfoButtonForActiveCard();
    }

    @Step("Выбрать 'Информация по карте' для закрытой карты")
    public void clickCardInfoForClosedCard() {
        cardPage.clickCardInfoButtonForClosedCard();
    }

    @Step("Выбрать 'Информация по карте' для заблокированной карты")
    public void clickCardInfoForBlockedCard() {
        cardPage.clickCardInfoButtonForBlockedCard();
    }
}
