package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tag("Web")
@Epic("4 - Счета")
@Feature("US-4.4.2 Блокировка и разблокировка счета (клиентом)")
@DisplayName("US-4.4.2 Блокировка и разблокировка счета (клиентом)")
public class BlockAccountTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ACCOUNTS_URL);
    }

    @TmsLink("LIB2-462")
    @DisplayName("Блокировка счета клиентом")
    @Test
    public void blockOpenAccount() {
        openActiveAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectBlockAccount();
        confirmationSteps.accept();
        accountInfoSteps.assertAccountStatusIsBlocked();
    }

    @TmsLink("LIB2-666")
    @DisplayName("Блокировка основного счета")
    @Test
    public void blockMainAccount() {
        openActiveAccount();
        if (!accountInfoSteps.isMainAccountLabelDisplayed()) {
            accountInfoSteps.clickDotsInfoButton();
            accountInfoSteps.selectSetMainAccount();
            confirmationSteps.accept();
            confirmationSteps.returnToAccount();
        }
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectBlockAccount();
        confirmationSteps.accept();
        Assertions.assertEquals(BLOCKED_ACCOUNT_STATUS, accountInfoSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, BLOCKED_ACCOUNT_STATUS));
        
        accountInfoSteps.assertMainAccountLabelIsNotDisplayed();
        accountInfoSteps.assertAccountStatusIsBlocked();
    }

    @TmsLink("LIB2-463")
    @DisplayName("Разблокировка счета")
    @Test
    public void unblockAccount() {
        openBlockedAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectUnblockAccount();
        confirmationSteps.accept();
        accountInfoSteps.assertAccountStatusIsOpen();
    }

    @TmsLink("LIB2-464")
    @DisplayName("Прерывание процесса блокировки счета")
    @Test
    public void deniedBlockAccount() {
        openActiveAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectBlockAccount();
        confirmationSteps.deny();
        accountInfoSteps.assertAccountStatusIsOpen();
    }

    @TmsLink("LIB2-465")
    @DisplayName("Прерывание процесса разблокировки счета")
    @Test
    public void deniedUnblockAccount() {
        openBlockedAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectUnblockAccount();
        confirmationSteps.deny();
        accountInfoSteps.assertAccountStatusIsBlocked();
    }

    private void openBlockedAccount() {
        accountSteps.selectBlockedAccounts();
        accountSteps.clickAccount();
    }

    private void openMainAccount() {
        accountSteps.selectOpenAccounts();
        accountSteps.clickMainAccountLabel();
    }

    private void openActiveAccount() {
        accountSteps.selectOpenAccounts();
        accountSteps.clickAccount();
    }
}
