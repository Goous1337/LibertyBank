package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

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
        createAccountSteps.assertCreateAccountButtonIsNotEnabled();
        createAccountSteps.clickCurrencyRub();
        createAccountSteps.assertCurrencyRubIsSelected();
        createAccountSteps.assertCurrencyEurIsNotSelected();
        createAccountSteps.assertCurrencyUsdIsNotSelected();
        createAccountSteps.assertMainAccountSwitcherIsNotSelected();
        createAccountSteps.assertChooseCurrencyTextIsDisplayed();
        createAccountSteps.assertMakeAccountMainTextIsDisplayed();
        createAccountSteps.assertCreateAccountButtonIsEnabled();
        createAccountSteps.createAccount();
        createAccountSteps.assertCreateAccountSuccessfullyMessageIsDisplayed();
        createAccountSteps.assertNavigationToBillButtonIsDisplayed();
        createAccountSteps.clickToNavigationToBillButton();
        accountSteps.assertOpenAccountsTabIsDisplayed();
    }

    @Test
    @DisplayName("Открытие счета в USD")
    @TmsLink("LIB2-2443")
    public void checkCreateUsdAccount() {
        createAccountSteps.assertCreateAccountButtonIsNotEnabled();
        createAccountSteps.clickCurrencyUsd();
        createAccountSteps.assertCurrencyRubIsNotSelected();
        createAccountSteps.assertCurrencyEurIsNotSelected();
        createAccountSteps.assertCurrencyUsdIsSelected();
        createAccountSteps.assertMainAccountSwitcherIsNotSelected();
        createAccountSteps.assertChooseCurrencyTextIsDisplayed();
        createAccountSteps.assertMakeAccountMainTextIsDisplayed();
        createAccountSteps.assertCreateAccountButtonIsEnabled();
        createAccountSteps.createAccount();
        createAccountSteps.assertCreateAccountSuccessfullyMessageIsDisplayed();
        createAccountSteps.assertNavigationToBillButtonIsDisplayed();
    }

    @Test
    @DisplayName("Открытие счета в EUR")
    @TmsLink("LIB2-2444")
    public void checkCreateEurAccount() {
        createAccountSteps.assertCreateAccountButtonIsNotEnabled();
        createAccountSteps.clickCurrencyEur();
        createAccountSteps.assertCurrencyRubIsNotSelected();
        createAccountSteps.assertCurrencyEurIsSelected();
        createAccountSteps.assertCurrencyUsdIsNotSelected();
        createAccountSteps.assertMainAccountSwitcherIsNotSelected();
        createAccountSteps.assertChooseCurrencyTextIsDisplayed();
        createAccountSteps.assertMakeAccountMainTextIsDisplayed();
        createAccountSteps.assertCreateAccountButtonIsEnabled();
        createAccountSteps.createAccount();
        createAccountSteps.assertCreateAccountSuccessfullyMessageIsDisplayed();
        createAccountSteps.assertNavigationToBillButtonIsDisplayed();
    }

    @Test
    @DisplayName("Открытие основного счета в RUB")
    @TmsLink("LIB2-2445")
    public void checkCreateMainAccount() {
        createAccountSteps.assertCreateAccountButtonIsNotEnabled();
        createAccountSteps.clickCurrencyRub();
        createAccountSteps.assertCurrencyRubIsSelected();
        createAccountSteps.assertCurrencyEurIsNotSelected();
        createAccountSteps.assertCurrencyUsdIsNotSelected();
        createAccountSteps.assertMainAccountSwitcherIsNotSelected();
        createAccountSteps.assertChooseCurrencyTextIsDisplayed();
        createAccountSteps.assertMakeAccountMainTextIsDisplayed();
        createAccountSteps.assertCreateAccountButtonIsEnabled();
        createAccountSteps.createMainAccount();
        createAccountSteps.createAccount();
        createAccountSteps.assertCreateAccountSuccessfullyMessageIsDisplayed();
        createAccountSteps.assertNavigationToBillButtonIsDisplayed();
        createAccountSteps.clickToNavigationToBillButton();
        accountSteps.assertOpenAccountsTabIsDisplayed();
        accountSteps.assertMainAccountLabelIsDisplayed();
    }
}
