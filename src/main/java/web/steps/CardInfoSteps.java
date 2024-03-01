package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import web.helpers.TestListener;
import web.pages.CardInfoPage;

import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;

public class CardInfoSteps {

    protected CardInfoPage cardInfoPage;

    public CardInfoSteps() {
        cardInfoPage = new CardInfoPage();
    }

    @Step("Выбрать 'Сделать карту основной'")
    public void setMainCard() {
        cardInfoPage.clickSetMainSwitchButton();
    }

    @Step("Не отображается функция 'Сделать карту основной'")
    public void assertSetMainCardIsNotDisplayed() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            cardInfoPage.isSetMainSwitchButtonDisplayed();
        });
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус карты 'Основная'")
    public void assertMainCardStatusDisplayed() {
        Assertions.assertTrue(cardInfoPage.isMainCardStatusDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус 'Основная карта'"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается статус карты 'Основная'")
    public void assertMainCardStatusNotDisplayed() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            cardInfoPage.isMainCardStatusDisplayed();
        });
        TestListener.takeScreenshot();
    }

    @Step("Вернутся назад к списку карт")
    public void goBack() {
        cardInfoPage.clickBackButton();
    }

    @Step("Заблокировать карту")
    public void blockCard() {
        cardInfoPage.clickBlockCardButton();
    }

    @Step("Закрыть карту")
    public void closeCard() {
        cardInfoPage.clickCloseCardButton();
    }
}
