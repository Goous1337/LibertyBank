package web.steps.cardSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import web.helpers.TestListener;
import web.pages.cardPages.CardInfoPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(cardInfoPage.isMainCardStatusDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус 'Основная карта'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус карты 'Основная'")
    public void assertStatusCardActiveDisplayed() {
        assertTrue(cardInfoPage.statusCardActive(), String.format(NOT_DISPLAYED_MESSAGE, "Статус 'Активная'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус карты 'Закрытая'")
    public void assertCardStatusClosed() {
        assertTrue(cardInfoPage.statusCardClosed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус 'Закрытая'"));
    }

    @Step("Не отображается статус карты 'Основная'")
    public void assertMainCardStatusNotDisplayed() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            cardInfoPage.isMainCardStatusDisplayed();
        });
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус карты 'Активная'")
    public void assertActiveCardStatusDisplayed() {
        Assertions.assertTrue(cardInfoPage.isActiveCardStatusDisplayed());
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус карты 'Заблокированная'")
    public void assertBlockedCardStatusDisplayed() {
        Assertions.assertTrue(cardInfoPage.isBlockedCardStatusDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус 'Заблокированная'"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается статус карты 'Заблокированная'")
    public void assertBlockedStatusNotDisplayed() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            cardInfoPage.isBlockedCardStatusDisplayed();
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

    @Step("Разблокировать карту")
    public void unblockCard() {
        cardInfoPage.clickUnblockCardButton();
    }

    @Step("Закрыть карту")
    public void closeCard() {
        cardInfoPage.clickCloseCardButton();
    }

    @Step("Отображается тип карты в подробной информации о карте пользователя")
    public void assertUserCardTypeIsDisplayed() {
        Assertions.assertTrue(cardInfoPage.isUserCardTypeDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Тип карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается номер карты при просмотре информации по карте пользователя")
    public void assertUserCardNumberIsDisplayed() {
        Assertions.assertTrue(cardInfoPage.isUserCardNumberDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Номер карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается срок действия карты при просмотре информации по карте пользователя")
    public void assertUserCardValidityPeriodIsDisplayed() {
        Assertions.assertTrue(cardInfoPage.isUserCardValidityPeriodDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Номер карты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается иконка копирования номера карты")
    public void assertCopyCardNumberIsDisplayed() {
        Assertions.assertTrue(cardInfoPage.isCopyCardNumberIconDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Иконка 'Копировать номер карты'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается иконка копирования CVV кода")
    public void assertCopyCvvCodeIsDisplayed() {
        Assertions.assertTrue(cardInfoPage.isCopyCvvCodeIconDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Иконка 'Копировать CVV-код'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается вкладка 'История'")
    public void assertCardTransactionHistoryButtonIsDisplayed() {
        Assertions.assertTrue((cardInfoPage.isCardTransactionHistoryButtonDisplayed()), String.format(NOT_DISPLAYED_MESSAGE, "Вкладка 'История'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается блок 'Информация по карте'")
    public void assertCardInformationBlocDisplayed() {
        Assertions.assertAll(
                this::assertCardInformationTitleDisplayed,
                this::assertAccountInformationButtonDisplayed,
                this::assertCardTariffButtonDisplayed,
                this::assertCardLimitsButtonDisplayed
        );
    }

    @Step("Отображается заголовок 'Информация по карте'")
    public void assertCardInformationTitleDisplayed() {
        Assertions.assertTrue(cardInfoPage.isCardInformationTitleDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Заголовок 'Информация по карте'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Детали счета'")
    public void assertAccountInformationButtonDisplayed() {
        Assertions.assertTrue(cardInfoPage.isAccountInformationButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Детали счета'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Тариф'")
    public void assertCardTariffButtonDisplayed() {
        Assertions.assertTrue(cardInfoPage.isCardTariffButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Тариф'"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Лимиты по карте'")
    public void assertCardLimitsButtonDisplayed() {
        Assertions.assertTrue(cardInfoPage.isCardLimitsDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Кнопка 'Лимиты по карте'"));
        TestListener.takeScreenshot();
    }
}
