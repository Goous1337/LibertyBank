package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import property.WebPropertiesReader;
import web.BaseTest;
import web.drivers.DriverManager;

import static web.constans.AccountServiceConstants.*;
import static web.constans.UrlConfig.ACCOUNTS_URL;

@Epic("4 - Счета")
@Feature("US-4.1 Открытие текущего счета")
@DisplayName("US-4.1 Открытие текущего счета")
public class CreateCurrentAccountTest extends BaseTest {

    private static WebDriver driver = DriverManager.getDriver();

    @BeforeEach
    public void setUpTest() {
        driver.get(WebPropertiesReader.getWebBaseUrl() + ACCOUNTS_URL);
        accountSteps.clickCreateCurrentAccount();
    }

    @Test
    @DisplayName("Открытие счета в RUB")
    @TmsLink("LIB2-2441")
    public void checkCreateRubAccount() {
        Assertions.assertFalse(createAccountSteps.isCreateAccountButtonEnabled(), String.format(ELEMENT_ENABLED_VERIFICATION_MESSAGE, "Открыть счет"));
        createAccountSteps.clickCurrencyRub();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCurrencyRubSelected(), String.format(ELEMENT_NOT_SELECTED_VERIFICATION_MESSAGE, RUB)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyEurSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, EUR)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyUsdSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, USD)),
                () -> Assertions.assertFalse(createAccountSteps.isMainAccountSwitcherSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isChooseCurrencyTextDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Выберите валюту")),
                () -> Assertions.assertTrue(createAccountSteps.isMakeAccountMainTextDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountButtonEnabled(), String.format(ELEMENT_NOT_ENABLED_VERIFICATION_MESSAGE, "Открыть счет"))
        );
        createAccountSteps.createAccount();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountSuccessfullyMessageDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Сообщение об открытии счета")),
                () -> Assertions.assertTrue(createAccountSteps.isNavigationToBillButtonDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Перейти к списку счетов"))
        );
        createAccountSteps.clickToNavigationToBillButton();
        Assertions.assertTrue(accountSteps.isOpenAccountDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, OPEN_ACCOUNT_STATUS));
    }

    @Test
    @DisplayName("Открытие счета в USD")
    @TmsLink("LIB2-2443")
    public void checkCreateUsdAccount() {
        Assertions.assertFalse(createAccountSteps.isCreateAccountButtonEnabled(), String.format(ELEMENT_ENABLED_VERIFICATION_MESSAGE, "Открыть счет"));
        createAccountSteps.clickCurrencyUsd();
        Assertions.assertAll(
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyRubSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, RUB)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyEurSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, EUR)),
                () -> Assertions.assertTrue(createAccountSteps.isCurrencyUsdSelected(), String.format(ELEMENT_NOT_SELECTED_VERIFICATION_MESSAGE, USD)),
                () -> Assertions.assertFalse(createAccountSteps.isMainAccountSwitcherSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isChooseCurrencyTextDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Выберите валюту")),
                () -> Assertions.assertTrue(createAccountSteps.isMakeAccountMainTextDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountButtonEnabled(), String.format(ELEMENT_NOT_ENABLED_VERIFICATION_MESSAGE, "Открыть счет"))
        );
        createAccountSteps.createAccount();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountSuccessfullyMessageDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Сообщение об открытии счета")),
                () -> Assertions.assertTrue(createAccountSteps.isNavigationToBillButtonDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Перейти к списку счетов"))
        );
    }

    @Test
    @DisplayName("Открытие счета в EUR")
    @TmsLink("LIB2-2444")
    public void checkCreateEurAccount() {
        Assertions.assertFalse(createAccountSteps.isCreateAccountButtonEnabled(), String.format(ELEMENT_ENABLED_VERIFICATION_MESSAGE, "Открыть счет"));
        createAccountSteps.clickCurrencyEur();
        Assertions.assertAll(
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyRubSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, RUB)),
                () -> Assertions.assertTrue(createAccountSteps.isCurrencyEurSelected(), String.format(ELEMENT_NOT_SELECTED_VERIFICATION_MESSAGE, EUR)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyUsdSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, USD)),
                () -> Assertions.assertFalse(createAccountSteps.isMainAccountSwitcherSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isChooseCurrencyTextDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Выберите валюту")),
                () -> Assertions.assertTrue(createAccountSteps.isMakeAccountMainTextDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountButtonEnabled(), String.format(ELEMENT_NOT_ENABLED_VERIFICATION_MESSAGE, "Открыть счет"))
        );
        createAccountSteps.createAccount();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountSuccessfullyMessageDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Сообщение об открытии счета")),
                () -> Assertions.assertTrue(createAccountSteps.isNavigationToBillButtonDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Перейти к списку счетов"))
        );
    }

    @Test
    @DisplayName("Открытие основного счета в RUB")
    @TmsLink("LIB2-2445")
    public void checkCreateMainAccount() {
        Assertions.assertFalse(createAccountSteps.isCreateAccountButtonEnabled(), String.format(ELEMENT_ENABLED_VERIFICATION_MESSAGE, "Открыть счет"));
        createAccountSteps.clickCurrencyRub();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCurrencyRubSelected(), String.format(ELEMENT_NOT_SELECTED_VERIFICATION_MESSAGE, RUB)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyEurSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, EUR)),
                () -> Assertions.assertFalse(createAccountSteps.isCurrencyUsdSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, USD)),
                () -> Assertions.assertFalse(createAccountSteps.isMainAccountSwitcherSelected(), String.format(ELEMENT_SELECTED_VERIFICATION_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isChooseCurrencyTextDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Выберите валюту")),
                () -> Assertions.assertTrue(createAccountSteps.isMakeAccountMainTextDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Сделать основным")),
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountButtonEnabled(), String.format(ELEMENT_NOT_ENABLED_VERIFICATION_MESSAGE, "Открыть счет"))
        );
        createAccountSteps.createMainAccount();
        createAccountSteps.createAccount();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createAccountSteps.isCreateAccountSuccessfullyMessageDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Сообщение об открытии счета")),
                () -> Assertions.assertTrue(createAccountSteps.isNavigationToBillButtonDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, "Перейти к списку счетов"))
        );
        createAccountSteps.clickToNavigationToBillButton();
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountSteps.isOpenAccountDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, OPEN_ACCOUNT_STATUS)),
                () -> Assertions.assertTrue(accountSteps.isMainAccountLabelDisplayed(), String.format(ELEMENT_DISPLAYED_VERIFICATION_MESSAGE, MAIN_ACCOUNT_STATUS))
        );
    }
}
