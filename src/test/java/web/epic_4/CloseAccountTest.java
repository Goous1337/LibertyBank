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
        Assertions.assertTrue(confirmationSteps.isCloseVerificationMessageDisplayed(), String.format(DISPLAYED_MESSAGE, "Сообщение о подтверждении закрытия счета"));
        confirmationSteps.accept();
        Assertions.assertTrue(confirmationSteps.isCloseSuccessfullyMessageDisplayed(), String.format(DISPLAYED_MESSAGE, "Сообщение об успешном закрытии счета"));
        confirmationSteps.clickNavigateToAccountsPageButton();
        Assertions.assertAll(
                () -> Assertions.assertTrue(accountSteps.isCloseAccountsDisplayed(), String.format(DISPLAYED_MESSAGE, CLOSED_ACCOUNTS_TAB)),
                () -> Assertions.assertTrue(accountSteps.isOpenAccountDisplayed(), String.format(DISPLAYED_MESSAGE, OPEN_ACCOUNTS_TAB))
        );
    }

    @Test
    @DisplayName("Отказ от закрытия счета")
    @TmsLink("LIB2-406")
    public void checkClosingAccountDeny() {
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.clickCloseButton();
        Assertions.assertTrue(confirmationSteps.isCloseVerificationMessageDisplayed(), String.format(DISPLAYED_MESSAGE, "Сообщение о подтверждении закрытия счета"));
        confirmationSteps.deny();
        Assertions.assertNotEquals(CLOSED_ACCOUNT_STATUS, accountInfoSteps.getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, CLOSED_ACCOUNT_STATUS));
    }
}
