package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tag("Web")
@Epic("4 - Счета")
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
        accountSteps.clickSecondAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectSetMainAccount();
        confirmationSteps.assertSetMainAccountDialogBoxIsDisplayed();
        confirmationSteps.accept();
        confirmationSteps.assertSuccessDialogBoxIsDisplayed();
        confirmationSteps.returnToAccount();
        accountSteps.assertMainAccountLabelIsDisplayed();
    }

    @Test
    @Order(2)
    @TmsLink("LIB2-2452")
    @DisplayName("Перенос статуса 'Основной счет' с одного открытого счета на другой")
    public void transferringMainAccountStatusFromOneAccountToAnother() {
        accountSteps.clickSecondAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectSetMainAccount();
        confirmationSteps.assertSetMainAccountDialogBoxIsDisplayed();
        confirmationSteps.accept();
        confirmationSteps.assertSuccessDialogBoxIsDisplayed();
        confirmationSteps.returnToAccount();
        accountSteps.assertMainAccountLabelIsDisplayed();
        accountInfoSteps.goBack();
        accountInfoSteps.assertMainAccountLabelIsDisplayed();
    }

    @Test
    @Order(3)
    @TmsLink("LIB2-2456")
    @DisplayName("Отсутствие статуса 'Основной счет' после блокирования счета")
    public void mainAccountStatusNotDisplayedAfterBlocked() {
        accountSteps.clickAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.selectBlockAccount();
        confirmationSteps.assertBlockAccountDialogBoxIsDisplayed();
        confirmationSteps.accept();
        accountInfoSteps.assertMainAccountLabelIsNotDisplayed();
    }

    @Test
    @Order(4)
    @TmsLink("LIB2-2453")
    @DisplayName("Отсутствие возможности сделать счет основным для закрытого счета")
    public void setMainAccountNotDisplayedWhenAccountIsClosed() {
        accountSteps.selectClosedAccounts();
        accountSteps.clickAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.assertSetMainAccountOptIsNotDisplayed();
    }

    @Test
    @Order(5)
    @TmsLink("LIB2-2454")
    @DisplayName("Отсутствие возможности сделать счет основным для заблокированного счета")
    public void setMainAccountNotDisplayedWhenAccountIsBlocked() {
        accountSteps.selectBlockedAccounts();
        accountSteps.clickAccount();
        accountInfoSteps.clickDotsInfoButton();
        accountInfoSteps.assertSetMainAccountOptIsNotDisplayed();
    }
}
