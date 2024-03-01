package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.BaseTest;

import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tag("Web")
@Epic("4 - Счета")
@Feature("US-4.3 Просмотр подробной информации о счете")
@DisplayName("US-4.3 Просмотр подробной информации о счете")
public class AccountInfoTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ACCOUNTS_URL);
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации об открытом счете")
    public void checkActiveAccountInfo() {
        accountSteps.selectOpenAccounts();
        accountSteps.clickAccount();
        commonInfoChecks();
        accountInfoSteps.assertAccountStatusIsOpen();
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации о закрытом счете")
    public void checkClosedAccountInfo() {
        accountSteps.selectClosedAccounts();
        accountSteps.clickAccount();
        commonInfoChecks();
        accountInfoSteps.assertCloseDateIsDisplayed();
        accountInfoSteps.assertAccountStatusIsClosed();
    }

    @Test
    @DisplayName("Проверка отображения элементов подробной информации о заблокированном счете")
    public void checkBlockedAccountInfo() {
        accountSteps.selectBlockedAccounts();
        accountSteps.clickAccount();
        commonInfoChecks();
        accountInfoSteps.assertAccountStatusIsBlocked();
    }

    private void commonInfoChecks() {
        accountInfoSteps.assertAccountNameIsDisplayed();
        accountInfoSteps.assertAccountNumberIsDisplayed();
        accountInfoSteps.assertOpenDateDisplayed();
        accountInfoSteps.assertContractNumberIsDisplayed();
        accountInfoSteps.assertAccountStatusIsDisplayed();
        accountInfoSteps.assertCurrencyIconIsDisplayed();
        accountInfoSteps.assertBalanceIsDisplayed();
    }
}
