package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import web.helpers.TestListener;
import web.pages.CardPage;

import static web.constans.AccountServiceConstants.*;

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

    @Step("Отображается тип карты")
    public void assertCardTypeIsDisplayed() {
        Assertions.assertTrue(cardPage.isCardTypeDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Тип карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается номер карты")
    public void assertCardNumberIsDisplayed() {
        Assertions.assertTrue(cardPage.isCardNumberDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Номер карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается срок действия карты")
    public void assertCardValidityPeriodIsDisplayed() {
        Assertions.assertTrue(cardPage.isCardValidityPeriodDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Срок действия карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается баланс карты")
    public void assertCardBalanceIsDisplayed() {
        Assertions.assertTrue(cardPage.isCardBalanceDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Баланс карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается валюта карты")
    public void assertCardCurrencyIsDisplayed() {
        Assertions.assertTrue(cardPage.isCardCurrencyDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Валюта карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается платёжная система карты")
    public void assertCardPaymentSystemIsDisplayed() {
        Assertions.assertTrue(cardPage.isCardPaymentSystemDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Платёжная система карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображаются карты только с соответствующей валютой")
    public void assertCurrencyEqualsExpected() {
        Assertions.assertTrue(cardPage.isCurrencyEqualsExpected(), String.format(NOT_EQUALS_MESSAGE, "Валюта"));
        TestListener.takeScreenshot();
    }
}
