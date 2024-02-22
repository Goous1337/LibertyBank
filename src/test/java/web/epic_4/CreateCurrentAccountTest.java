package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.AccountServiceConstants.*;
import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tag("Web")
@Epic("4 - Счета")
@Feature("US-4.1 Открытие текущего счета")
@DisplayName("US-4.1 Открытие текущего счета")
public class CreateCurrentAccountTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ACCOUNTS_URL);
        accountSteps.clickCreateCurrentAccount();
    }

    @Test
    @DisplayName("Открытие счета в RUB")
    @TmsLink("LIB2-2441")
    public void checkCreateRubAccount() {
        Assertions.assertFalse(createAccountSteps.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"));
        createAccountSteps.clickCurrencyRub();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCurrencyRubSelected(), String.format(NOT_SELECTED_MESSAGE, RUB)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyEurSelected(), String.format(SELECTED_MESSAGE, EUR)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyUsdSelected(), String.format(SELECTED_MESSAGE, USD)),
                () -> Assertions.assertFalse(createAccountSteps.isMainAccountSwitcherSelected(), String.format(SELECTED_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isChooseCurrencyTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Выберите валюту")),
                () -> Assertions.assertTrue(createAccountSteps.isMakeAccountMainTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"))
        );
        createAccountSteps.createAccount();
        Assertions.assertTrue(createAccountSteps.isNavigationToBillButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Перейти к списку счетов"));

        createAccountSteps.clickToNavigationToBillButton();
        Assertions.assertTrue(accountSteps.isOpenAccountDisplayed(), String.format(STATUS_ERROR_MESSAGE, OPEN_ACCOUNT_STATUS));
    }

    @Test
    @DisplayName("Открытие счета в USD")
    @TmsLink("LIB2-2443")
    public void checkCreateUsdAccount() {
        Assertions.assertFalse(createAccountSteps.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"));
        createAccountSteps.clickCurrencyUsd();
        Assertions.assertAll(
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyRubSelected(), String.format(SELECTED_MESSAGE, RUB)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyEurSelected(), String.format(SELECTED_MESSAGE, EUR)),
                () -> Assertions.assertTrue(createAccountSteps.isCurrencyUsdSelected(), String.format(NOT_SELECTED_MESSAGE, USD)),
                () -> Assertions.assertFalse(createAccountSteps.isMainAccountSwitcherSelected(), String.format(SELECTED_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isChooseCurrencyTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Выберите валюту")),
                () -> Assertions.assertTrue(createAccountSteps.isMakeAccountMainTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"))
        );
        createAccountSteps.createAccount();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountSuccessfullyMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение об открытии счета")),
                () -> Assertions.assertTrue(createAccountSteps.isNavigationToBillButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Перейти к списку счетов"))
        );
    }

    @Test
    @DisplayName("Открытие счета в EUR")
    @TmsLink("LIB2-2444")
    public void checkCreateEurAccount() {
        Assertions.assertFalse(createAccountSteps.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"));
        createAccountSteps.clickCurrencyEur();
        Assertions.assertAll(
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyRubSelected(), String.format(SELECTED_MESSAGE, RUB)),
                () -> Assertions.assertTrue(createAccountSteps.isCurrencyEurSelected(), String.format(NOT_SELECTED_MESSAGE, EUR)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyUsdSelected(), String.format(SELECTED_MESSAGE, USD)),
                () -> Assertions.assertFalse(createAccountSteps.isMainAccountSwitcherSelected(), String.format(SELECTED_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isChooseCurrencyTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Выберите валюту")),
                () -> Assertions.assertTrue(createAccountSteps.isMakeAccountMainTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"))
        );
        createAccountSteps.createAccount();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountSuccessfullyMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение об открытии счета")),
                () -> Assertions.assertTrue(createAccountSteps.isNavigationToBillButtonDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Перейти к списку счетов"))
        );
    }

    @Test
    @DisplayName("Открытие основного счета в RUB")
    @TmsLink("LIB2-2445")
    public void checkCreateMainAccount() {
        Assertions.assertFalse(createAccountSteps.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"));
        createAccountSteps.clickCurrencyRub();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCurrencyRubSelected(), String.format(NOT_SELECTED_MESSAGE, RUB)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyEurSelected(), String.format(SELECTED_MESSAGE, EUR)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyUsdSelected(), String.format(SELECTED_MESSAGE, USD)),
                () -> Assertions.assertFalse(createAccountSteps.isMainAccountSwitcherSelected(), String.format(SELECTED_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isChooseCurrencyTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Выберите валюту")),
                () -> Assertions.assertTrue(createAccountSteps.isMakeAccountMainTextDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountButtonEnabled(), String.format(NOT_ENABLED_MESSAGE, "Открыть счет"))
        );
        createAccountSteps.createMainAccount();
        createAccountSteps.createAccount();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountSuccessfullyMessageDisplayed(), String.format(DISPLAYED_MESSAGE, "Сообщение об открытии счета")),
                () -> Assertions.assertTrue(createAccountSteps.isNavigationToBillButtonDisplayed(), String.format(DISPLAYED_MESSAGE, "Перейти к списку счетов"))
        );
        createAccountSteps.clickToNavigationToBillButton();
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountSteps.isOpenAccountDisplayed(), String.format(STATUS_ERROR_MESSAGE, OPEN_ACCOUNT_STATUS)),
                () -> Assertions.assertTrue(accountSteps.isMainAccountLabelDisplayed(), String.format(STATUS_ERROR_MESSAGE, MAIN_ACCOUNT_STATUS))
        );
    }
}
