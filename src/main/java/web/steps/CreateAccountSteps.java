package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.CreateAccountPage;

import static web.constans.AccountServiceConstants.*;

public class CreateAccountSteps {

    public CreateAccountPage createAccountPage;

    public CreateAccountSteps() {
        createAccountPage = new CreateAccountPage();
    }

    @Step("Отображается текст 'Выберите валюту'")
    public void assertChooseCurrencyTextIsDisplayed() {
        Assertions.assertTrue(createAccountPage.isChooseCurrencyTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Выберите валюту"));
        TestListener.takeScreenshot();
    }

    @Step("Выбрана валюта RUB")
    public void assertCurrencyRubIsSelected() {
        Assertions.assertTrue(createAccountPage.isCurrencyRubSelected(), String.format(NOT_SELECTED_MESSAGE, RUB));
        TestListener.takeScreenshot();
    }

    @Step("Не выбрана валюта RUB")
    public void assertCurrencyRubIsNotSelected() {
        Assertions.assertFalse(createAccountPage.isCurrencyRubSelected(), String.format(SELECTED_MESSAGE, RUB));
        TestListener.takeScreenshot();
    }

    @Step("Выбрана валюта EUR")
    public void assertCurrencyEurIsSelected() {
        Assertions.assertTrue(createAccountPage.isCurrencyEurSelected(), String.format(NOT_SELECTED_MESSAGE, EUR));
        TestListener.takeScreenshot();
    }

    @Step("Не выбрана валюта EUR")
    public void assertCurrencyEurIsNotSelected() {
        Assertions.assertFalse(createAccountPage.isCurrencyEurSelected(), String.format(SELECTED_MESSAGE, EUR));
        TestListener.takeScreenshot();
    }

    @Step("Выбрана валюта USD")
    public void assertCurrencyUsdIsSelected() {
        Assertions.assertTrue(createAccountPage.isCurrencyUsdSelected(), String.format(NOT_SELECTED_MESSAGE, USD));
        TestListener.takeScreenshot();
    }

    @Step("Не выбрана валюта USD")
    public void assertCurrencyUsdIsNotSelected() {
        Assertions.assertFalse(createAccountPage.isCurrencyUsdSelected(), String.format(SELECTED_MESSAGE, USD));
        TestListener.takeScreenshot();
    }

    @Step("Отображается текст 'Сделать основным'")
    public void assertMakeAccountMainTextIsDisplayed() {
        Assertions.assertTrue(createAccountPage.isMakeAccountMainTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сделать основным"));
        TestListener.takeScreenshot();
    }

    @Step("Выбран переключатель 'Сделать основным'")
    public void assertMainAccountSwitcherIsSelected() {
        Assertions.assertFalse(createAccountPage.isMainAccountSwitcherSelected(), String.format(NOT_SELECTED_MESSAGE, "Сделать основным"));
        TestListener.takeScreenshot();
    }

    @Step("Не выбран переключатель 'Сделать основным'")
    public void assertMainAccountSwitcherIsNotSelected() {
        Assertions.assertFalse(createAccountPage.isMainAccountSwitcherSelected(), String.format(SELECTED_MESSAGE, "Сделать основным"));
        TestListener.takeScreenshot();
    }

    @Step("Активна кнопка 'Открыть счет'")
    public void assertCreateAccountButtonIsEnabled() {
        Assertions.assertTrue(createAccountPage.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"));
        TestListener.takeScreenshot();
    }

    @Step("Не активна кнопка 'Открыть счет'")
    public void assertCreateAccountButtonIsNotEnabled() {
        Assertions.assertFalse(createAccountPage.isCreateAccountButtonEnabled(), String.format(ENABLED_MESSAGE, "Открыть счет"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается сообщение об успешном открытии счета")
    public void assertCreateAccountSuccessfullyMessageIsDisplayed() {
        Assertions.assertTrue(createAccountPage.isCreateAccountSuccessfullyMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение об открытии счета"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Перейти к списку счетов'")
    public void assertNavigationToBillButtonIsDisplayed() {
        Assertions.assertTrue(createAccountPage.isNavigationToBillButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Перейти к списку счетов"));
        TestListener.takeScreenshot();
    }

    @Step("Выбрать валюту RUB")
    public void clickCurrencyRub() {
        createAccountPage.clickCurrencyRub();
    }

    @Step("Выбрать валюту EUR")
    public void clickCurrencyEur() {
        createAccountPage.clickCurrencyEur();
    }

    @Step("Выбрать валюту USD")
    public void clickCurrencyUsd() {
        createAccountPage.clickCurrencyUsd();
    }

    @Step("Сделать счет основным")
    public void createMainAccount() {
        createAccountPage.switchMainAccount();
    }

    @Step("Нажать кнопку Открыть счет")
    public void createAccount() {
        createAccountPage.createAccountButton();
    }

    @Step("Нажать кнопку Перейти ко всем счетам")
    public void clickToNavigationToBillButton() {
        createAccountPage.navigateToBillButton();
    }
}
