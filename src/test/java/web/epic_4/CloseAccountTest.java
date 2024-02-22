package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tag("Web")
@Epic("4 - Счета")
@Feature("US-4.4.1 Закрыть счет")
@DisplayName("US-4.4.1 Закрыть счет")
public class CloseAccountTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ACCOUNTS_URL);
        accountSteps.clickAccount();
    }

    @Test
    @DisplayName("Закрытие счета пользователем")
    @TmsLink("LIB2-420")
    public void checkClosingAccountByUser() {
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.clickCloseButton();
        confirmationSteps.assertCloseVerificationMessageIsDisplayed();
        confirmationSteps.accept();
        confirmationSteps.assertCloseSuccessfullyMessageDisplayed();
        confirmationSteps.clickNavigateToAccountsPageButton();
        accountSteps.assertClosedAccountsTabIsDisplayed();
        accountSteps.assertOpenAccountsTabIsDisplayed();
    }

    @Test
    @DisplayName("Отказ от закрытия счета")
    @TmsLink("LIB2-406")
    public void checkClosingAccountDeny() {
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.clickCloseButton();
        confirmationSteps.assertCloseVerificationMessageIsDisplayed();
        confirmationSteps.deny();
        accountInfoSteps.assertAccountStatusIsNotClosed();
    }
}
