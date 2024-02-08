package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.AccountServiceConstants.*;

import static web.constans.UrlConfig.ACCOUNTS_URL;

@Epic("Epic-4")
@Feature("US-4.4.4 Сделать счет основным")
@DisplayName("US-4.4.4 Сделать счет основным")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SetMainAccountTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ACCOUNTS_URL);
    }

    @Test
    @Order(1)
    @TmsLink("LIB2-2451")
    @DisplayName("Сделать открытый счет основным")
    public void setMainAccount() {
        accountSteps.clickAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectSetMainAccount();
        Assertions.assertTrue(confirmationSteps.isSetMainAccountDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        confirmationSteps.accept();
        Assertions.assertTrue(confirmationSteps.isSuccessDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        confirmationSteps.returnToAccount();
        Assertions.assertTrue(accountSteps.isMainAccountLabelDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
    }

    @Test
    @Order(2)
    @TmsLink("LIB2-2452")
    @Disabled("Выключен, пока фронт не исправит баг с закреплением счета в начале списка")
    @DisplayName("Перенос статуса 'Основной счет' с одного открытого счета на другой")
    public void transferringMainAccountStatusFromOneAccountToAnother() {
        accountSteps.clickSecondAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectSetMainAccount();
        Assertions.assertTrue(confirmationSteps.isSetMainAccountDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        confirmationSteps.accept();
        Assertions.assertTrue(confirmationSteps.isSuccessDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        confirmationSteps.returnToAccount();
        Assertions.assertTrue(accountSteps.isMainAccountLabelDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
        accountInfoSteps.goBack();
        Assertions.assertTrue(accountSteps.isMainAccountLabelDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
        Assertions.assertFalse(accountSteps.isMainAccountLabelDisplayed(), String.format(DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
    }

    @Test
    @Order(3)
    @TmsLink("LIB2-2456")
    @DisplayName("Отсутствие статуса 'Основной счет' после блокирования счета")
    public void  mainAccountStatusNotDisplayedAfterBlocked() {
        accountSteps.clickAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectBlockAccount();
        Assertions.assertTrue(confirmationSteps.isBlockAccountDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        confirmationSteps.accept();
        Assertions.assertFalse(accountInfoSteps.isMainAccountLabelDisplayed(), String.format(DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
    }

    @Test
    @Order(4)
    @TmsLink("LIB2-2453")
    @DisplayName("Отсутствие возможности сделать счет основным для закрытого счета")
    public void setMainAccountNotDisplayedWhenAccountIsClosed() {
        accountSteps.selectClosedAccounts();
        accountSteps.clickAccount();
        accountInfoSteps.clickDotsInfoButton();
        Assertions.assertFalse(accountInfoSteps.isSetMainAccountOptDisplayed(), String.format(DISPLAYED_MESSAGE, "Опция 'Сделать счет основным'"));
    }

    @Test
    @Order(5)
    @TmsLink("LIB2-2454")
    @DisplayName("Отсутствие возможности сделать счет основным для заблокированного счета")
    public void setMainAccountNotDisplayedWhenAccountIsBlocked() {
        accountSteps.selectBlockedAccounts();
        accountSteps.clickAccount();
        accountInfoSteps.clickDotsInfoButton();
        Assertions.assertFalse(accountInfoSteps.isSetMainAccountOptDisplayed(), String.format(DISPLAYED_MESSAGE, "Опция 'Сделать счет основным'"));
    }
}
