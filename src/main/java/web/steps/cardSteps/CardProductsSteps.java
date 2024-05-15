package web.steps.cardSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import web.helpers.TestListener;
import web.pages.cardPages.CardProductsPage;

import java.util.Arrays;
import java.util.List;

import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;
import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;


public class CardProductsSteps {
    protected CardProductsPage cardProductsPage;

    public CardProductsSteps() {
        cardProductsPage = new CardProductsPage();
    }

    @Step("Оформить Liberty Card Classic")
    public void classicCardOrder() {
        cardProductsPage.classicCardOrderButtonClick();
    }

    @Step("Посмотреть информацию по Liberty Card Classic")
    public void classicCardInfo() {
        cardProductsPage.classicCardInfoButtonClick();
    }

    @Step("Оформить Liberty Card Child")
    public void childCardOrder() {
        cardProductsPage.childCardOrderButtonClick();
    }

    @Step("Посмотреть информацию по Liberty Card Child")
    public void childCardInfo() {
        cardProductsPage.childCardInfoButtonClick();
    }

    @Step("Оформить Liberty Card Gold")
    public void goldCardOrder() {
        cardProductsPage.goldCardOrderButtonClick();
    }

    @Step("Посмотреть информацию по Liberty Card Gold")
    public void goldCardInfo() {
        cardProductsPage.goldCardInfoButtonClick();
    }

    @Step("Оформить Liberty Card Platinum")
    public void platinumCardOrder() {
        cardProductsPage.platinumCardOrderButtonClick();
    }

    @Step("Посмотреть информацию по Liberty Card Platinum")
    public void platinumCardInfo() {
        cardProductsPage.platinumCardInfoButtonClick();
    }

    @Step("Оформить Liberty Card Virtual")
    public void virtualCardOrder() {
        cardProductsPage.virtualCardOrderButtonClick();
    }

    @Step("Посмотреть информацию по Liberty Card Virtual")
    public void virtualCardInfo() {
        cardProductsPage.virtualCardInfoButtonClick();
    }

    @Step("Оформить Liberty Card Secure")
    public void secureCardOrder() {
        cardProductsPage.secureCardOrderButtonClick();
    }

    @Step("Посмотреть информацию по Liberty Card Secure")
    public void secureCardInfo() {
        cardProductsPage.secureCardInfoButtonClick();
    }

    @Step("Оформить Liberty Card Travel")
    public void travelCardOrder() {
        cardProductsPage.travelCardOrderButtonClick();
    }

    @Step("Посмотреть информацию по Liberty Card Travel")
    public void travelCardInfo() {
        cardProductsPage.travelCardInfoButtonClick();
    }

    @Step("Scroll to Classic Card")
    public void scrollToClassicCard() {
        cardProductsPage.scrollToClassicCard();
    }

    @Step("Scroll to Child Card")
    public void scrollToChildCard() {
        cardProductsPage.scrollToChildCard();
    }

    @Step("Scroll to Gold Card")
    public void scrollToGoldCard() {
        cardProductsPage.scrollToGoldCard();
    }

    @Step("Scroll to Platinum Card")
    public void scrollToPlatinumCard() {
        cardProductsPage.scrollToPlatinumCard();
    }

    @Step("Scroll to Virtual Card")
    public void scrollToVirtualCard() {
        cardProductsPage.scrollToVirtualCard();
    }

    @Step("Scroll to Secure Card")
    public void scrollToSecureCard() {
        cardProductsPage.scrollToSecureCard();
    }

    @Step("Scroll to Travel Card")
    public void scrollToTravelCard() {
        cardProductsPage.scrollToTravelCard();
    }

    @Step("Отсортировать по валюте 'Все'")
    public void sortByAllCurrencies() {
        cardProductsPage.sortByAllCurrencies();
    }

    @Step("Отсортировать по валюте 'RUB'")
    public void sortByRub() {
        cardProductsPage.sortByRub();
    }

    @Step("Отсортировать по валюте 'USD'")
    public void sortByUsd() {
        cardProductsPage.sortByUsd();
    }

    @Step("Отсортировать по валюте 'EUR'")
    public void sortByEur() {
        cardProductsPage.sortByEur();
    }

    @Step("Отображаются все карты")
    public void assertAllCardsAreDisplayed() {
        Assertions.assertAll(
                this::assertClassicCardIsDisplayed,
                this::assertChildCardIsDisplayed,
                this::assertGoldCardIsDisplayed,
                this::assertPlatinumCardIsDisplayed,
                this::assertVirtualCardIsDisplayed,
                this::assertSecureCardIsDisplayed,
                this::assertTravelCardIsDisplayed
        );
    }

    @Step("Отображается Liberty Card Classic")
    public void assertClassicCardIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isClassicCardDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Classic Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается Liberty Card Child")
    public void assertChildCardIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isChildCardDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Child Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается Liberty Card Gold")
    public void assertGoldCardIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isGoldCardDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Gold Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается Liberty Card Platinum")
    public void assertPlatinumCardIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isPlatinumCardDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Platinum Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается Liberty Card Virtual")
    public void assertVirtualCardIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isVirtualCardDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Virtual Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается Liberty Card Secure")
    public void assertSecureCardIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isSecureCardDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Secure Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается Liberty Card Travel")
    public void assertTravelCardIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isTravelCardDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Travel Card"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается Liberty Card Classic")
    public void assertClassicCardIsNotDisplayed() {
        Assertions.assertFalse(cardProductsPage.isClassicCardDisplayed(), String.format(DISPLAYED_MESSAGE, "Classic Card"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается Liberty Card Child")
    public void assertChildCardIsNotDisplayed() {
        Assertions.assertFalse(cardProductsPage.isChildCardDisplayed(), String.format(DISPLAYED_MESSAGE, "Child Card"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается Liberty Card Gold")
    public void assertGoldCardIsNotDisplayed() {
        Assertions.assertFalse(cardProductsPage.isGoldCardDisplayed(), String.format(DISPLAYED_MESSAGE, "Gold Card"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается Liberty Card Platinum")
    public void assertPlatinumCardIsNotDisplayed() {
        Assertions.assertFalse(cardProductsPage.isPlatinumCardDisplayed(), String.format(DISPLAYED_MESSAGE, "Platinum Card"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается Liberty Card Virtual")
    public void assertVirtualCardIsNotDisplayed() {
        Assertions.assertFalse(cardProductsPage.isVirtualCardDisplayed(), String.format(DISPLAYED_MESSAGE, "Virtual Card"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается Liberty Card Secure")
    public void assertSecureCardIsNotDisplayed() {
        Assertions.assertFalse(cardProductsPage.isSecureCardDisplayed(), String.format(DISPLAYED_MESSAGE, "Secure Card"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается Liberty Card Travel")
    public void assertTravelCardIsNotDisplayed() {
        Assertions.assertFalse(cardProductsPage.isTravelCardDisplayed(), String.format(DISPLAYED_MESSAGE, "Travel Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображаются названия всех карт")
    public void assertAllCardTitlesAreDisplayed() {
        Assertions.assertTrue(cardProductsPage.allCardsTitlesDisplayed());
    }

//    @Step("Отображается срок действия каждой карты")
//    public void assertAllCardValidityIsDisplayed() {
//        Assertions.assertTrue(cardProductsPage.allCardsValidityDisplayed());
//    }

    @Step("Отображается стоимость обслуживания каждой карты")
    public void assertAllCardServiceCostIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.allCardsServiceCostDisplayed());
    }

    @Step("Отображается валюта каждой карты")
    public void assertAllCardCurrenciesAreDisplayed() {
        Assertions.assertTrue(cardProductsPage.allCardsCurrenciesDisplayed());
    }


    @Step("Нажатие кнопки 'Карты'")
    public void clickAllCardsButton() {
        cardProductsPage.clickAllCardsButton();
    }

    @Step("Нажатие кнопки 'Карточные продукты'")
    public void clickCardProductButton() {
        cardProductsPage.clickCardProductButton();
    }

    @Step("Нажатие кнопки 'Кредитные'")
    public void clickCardCreditButton() {
        cardProductsPage.clickCardCreditButton();
    }

    @Step("Отображается Liberty Card Classic")
    public void assertCardClassicIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isCardClassicDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Classic Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается Liberty Home Fix Card")
    public void assertFixHomeCardIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isFixHomeCardDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Home Fix Card"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается Liberty Card Premium")
    public void assertPremiumCardLabelIsDisplayed() {
        Assertions.assertTrue(cardProductsPage.isPremiumCardLabelDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Liberty Card Premium"));
        TestListener.takeScreenshot();
    }

    // Шаг для проверки соответствия фактических и ожидаемых значений для каждой карты в корзине
    @Step("Проверка соответствия фактических и ожидаемых значений для каждой карты в корзине")
    public void checkCreditCardBasket() {
        Assertions.assertTrue(cardProductsPage.findElements(), String.format(NOT_DISPLAYED_MESSAGE, "Liberty Card Premium"));
        TestListener.takeScreenshot();
    }
}

