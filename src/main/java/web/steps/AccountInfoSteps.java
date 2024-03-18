package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.AccountInfoPage;

import static web.constans.AccountServiceConstants.*;

public class AccountInfoSteps {

    protected AccountInfoPage accountInfoPage;

    public AccountInfoSteps() {
        accountInfoPage = new AccountInfoPage();
    }

    @Step("Отображается название счета")
    public void assertAccountNameIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isAccountNameDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Название счета"));
        TestListener.takeScreenshot();
    }

    @Step("Получить название счета")
    public String getAccountName() {
        return accountInfoPage.getAccountName();
    }

    @Step("Нажать на значок карандаша")
    public void clickPencil() {
        accountInfoPage.clickPencilImage();
    }

    @Step("Отображается номер счета")
    public void assertAccountNumberIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isAccountNumberDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Номер счета"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается дата открытия счета")
    public void assertOpenDateDisplayed() {
        Assertions.assertTrue(accountInfoPage.isOpenDateDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Дата открытия счета"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается дата закрытия счета")
    public void assertCloseDateIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isCloseDateDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Дата закрытия счета"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается номер договора")
    public void assertContractNumberIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isContractNumberDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Номер договора"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус 'Основной счет'")
    public void assertMainAccountLabelIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isMainAccountLabelDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается статус 'Основной счет'")
    public void assertMainAccountLabelIsNotDisplayed() {
        Assertions.assertFalse(accountInfoPage.isMainAccountLabelDisplayed(), String.format(DISPLAYED_MESSAGE, "Статус " + MAIN_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }

    @Step("Отображается статус счета")
    public void assertAccountStatusIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isAccountStatusDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Статус счета"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается иконка валюты")
    public void assertCurrencyIconIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isCurrencyIconDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Иконка валюты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается баланс счета")
    public void assertBalanceIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isBalanceDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Баланс"));
        TestListener.takeScreenshot();
    }

    public String getAccountStatus() {
        return accountInfoPage.getAccountStatus();
    }

    @Step("Нажать кнопку Закрыть счет")
    public void clickCloseButton() {
        accountInfoPage.clickCloseAccountOpt();
    }

    @Step("Вернуться к списку счетов")
    public void goBack() {
        accountInfoPage.clickGoBackButton();
    }

    @Step("Нажать на кебаб-меню")
    public void clickDotsInfoButton() {
        accountInfoPage.clickDotsInfoButton();
    }

    @Step("Выбрать 'Реквизиты'")
    public void selectRequisites() {
        accountInfoPage.clickRequisitesOpt();
    }

    @Step("Выбрать 'Выписка по счету'")
    public void selectAccountStatement() {
        accountInfoPage.clickAccountStatementOpt();
    }

    @Step("Выбрать 'Сделать счет основным'")
    public void selectSetMainAccount() {
        accountInfoPage.clickSetMainAccountOpt();
    }

    @Step("Отображается пункт 'Сделать счет основным'")
    public void assertSetMainAccountOptIsDisplayed() {
        Assertions.assertTrue(accountInfoPage.isSetMainAccountOptDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Опция 'Сделать счет основным'"));
        TestListener.takeScreenshot();
    }

    @Step("Не отображается пункт 'Сделать счет основным'")
    public void assertSetMainAccountOptIsNotDisplayed() {
        Assertions.assertFalse(accountInfoPage.isSetMainAccountOptDisplayed(), String.format(DISPLAYED_MESSAGE, "Опция 'Сделать счет основным'"));
        TestListener.takeScreenshot();
    }

    @Step("Выбрать 'Справка о доступном остатке'")
    public void selectInformationAboutAvailableBalance() {
        accountInfoPage.clickInformationAboutAvailableBalanceOpt();
    }

    @Step("Выбрать 'Закрыть счет'")
    public void selectCloseAccount() {
        accountInfoPage.clickCloseAccountOpt();
    }

    @Step("Выбрать 'Заблокировать счет'")
    public void selectBlockAccount() {
        accountInfoPage.clickBlockAccountOpt();
    }

    @Step("Выбрать 'Разблокировать счет'")
    public void selectUnblockAccount() {
        accountInfoPage.clickUnblockAccountOpt();
    }

    @Step("Проверить наличие лейбла 'Закрыт'")
    public boolean isClosedLabelDisplayed() {
        return accountInfoPage.isClosedLabelDisplayed();
    }

    @Step("Статус счета не соответствует статусу 'Закрыт'")
    public void assertAccountStatusIsNotClosed() {
        Assertions.assertNotEquals(CLOSED_ACCOUNT_STATUS, getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, CLOSED_ACCOUNT_STATUS));
    }

    @Step("Название счета соответствует ожидаемому")
    public void assertActualAccountNameEqualsExpectedName() {
        Assertions.assertEquals(VALID_ACCOUNT_NAME, getAccountName(), String.format(NOT_EQUALS_MESSAGE, "Название счета"));
        TestListener.takeScreenshot();
    }

    @Step("Статус счета соответствует статусу 'Активный'")
    public void assertAccountStatusIsOpen() {
        Assertions.assertEquals(OPEN_ACCOUNT_STATUS, getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, OPEN_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }

    @Step("Статус счета соответствует статусу 'Закрыт'")
    public void assertAccountStatusIsClosed() {
        Assertions.assertEquals(CLOSED_ACCOUNT_STATUS, getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, CLOSED_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }

    @Step("Статус счета соответствует статусу 'Заблокирован'")
    public void assertAccountStatusIsBlocked() {
        Assertions.assertEquals(BLOCKED_ACCOUNT_STATUS, getAccountStatus(), String.format(STATUS_ERROR_MESSAGE, BLOCKED_ACCOUNT_STATUS));
        TestListener.takeScreenshot();
    }
}
