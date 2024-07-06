package web.steps.depositsSteps;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.depositPages.*;
import static constant.DepositConstants.*;
import static web.constans.credit.CreditServiceConstants.NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE;


public class MyDepositsDetailedInfoStep {
    protected MyDepositsDetailedInfoPage myDepositsDetailedInfoPage;
    public MyDepositsDetailedInfoStep() {
        myDepositsDetailedInfoPage = new MyDepositsDetailedInfoPage();
    }

    @Step("Сравнение подробной информации о депозите пользователя API и UI")
    public void assertMoreInfoAboutMyDepositProduct(){
        Assertions.assertEquals(myDepositsDetailedInfoPage.getMyDepositProductObjectFromBackEnd(),
                myDepositsDetailedInfoPage.getMyDepositProductObjectFromWeb(),
                "Актуальный результат не соответствует ожидаемому");
    }

    @Step("Проверка отображения 'Название депозитного продукта' у активного пользователя")
    public void assertTextNameTitleDepositIsDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.depositProductNameTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Название депозита не отображается"));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Номер счёта депозитного продукта' у активного пользователя")
    public void assertTextAccountNumberDepositIsDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.depAccountNumberTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Номер счёта депозита не отображается"));
        TestListener.takeScreenshot();
    }
    @Step("Проверка отображения 'Статуса депозитного продукта' у активного пользователя")
    public void assertTextStatusDepositIsDisplayed(){
        Assertions.assertTrue(myDepositsDetailedInfoPage.statusDepositTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Cтатус депозита не отображается"));
    }
    @Step("Проверка отображения 'Даты открытия депозитного продукта' у активного пользователя")
    public void assertTextOpenDateDepositDisplayed(){
        Assertions.assertTrue(myDepositsDetailedInfoPage.openDateDepositTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Дата открытия депозита не отображается"));
    }
    @Step("Проверка отображения 'Даты закрытия депозитного продукта' у активного пользователя")
    public void assertTextCloseDateDepositDisplayed(){
        Assertions.assertTrue(myDepositsDetailedInfoPage.closeDateDepositTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Дата закрытия депозита не отображается"));
    }
    @Step("Проверка отображения 'Начальной суммы на счету депозитного продукта' у активного пользователя")
    public void assertTextInitialDepositAmountDisplayed(){
        Assertions.assertTrue(myDepositsDetailedInfoPage.initialDepositAmountTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Начальная сумма депозита не отображается"));
    }
    @Step("Проверка отображения 'Конечной суммы на счету депозитного продукта' у активного пользователя")
    public void assertTextFinalDepositAmountDisplayed(){
        Assertions.assertTrue(myDepositsDetailedInfoPage.finalDepositAmountTexDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Конечная сумма депозита не отображается"));
    }
    @Step("Проверка отображения 'Длительности существования депозитного продукта' у активного пользователя")
    public void assertTextPeriodMonthsDepositDisplayed(){
        Assertions.assertTrue(myDepositsDetailedInfoPage.periodMonthsDepositTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Длительность существования депозита не отображается"));
    }
    @Step("Проверка отображения 'Ставки депозитного продукта' у активного пользователя")
    public void assertTextInterestRateTextDisplayed(){
        Assertions.assertTrue(myDepositsDetailedInfoPage.interestRateTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Ставка депозита не отображается"));
    }
    @Step("Проверка нажатия значка 'Копирования номера счета депозитного продукта' у активного пользователя")
    public void clickCopyDepAccountNumberButton(){
        myDepositsDetailedInfoPage.clickCopyDepositAccountNumberButton();
    }
    @Step("Проверка отображения текста 'Скопированное после нажатия кнопки скопировать счет'")
    public void assertTextOutputCopiedIsDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.outputCopiedTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Сообщение о подтверждение копирования не отображается"));
        TestListener.takeScreenshot();
    }
    @Step("Нажатие кнопки 'Многоточие' действующего кредиты у авторизированного пользователя")
    public void clickDotsButton() {
        myDepositsDetailedInfoPage.clickDotsButton();
    }
    @Step("Проверка отображения кнопки 'Рекзвиты' у активного пользователя")
    public void assertButtonRequisitesIsDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.requisitesButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка реквизитов не отображается"));
        TestListener.takeScreenshot();
    }
    @Step("Проверка отображения кнопки 'Графика начисления процентов' у активного пользователя")
    public void assertPaymentScheduleButtonDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.paymentScheduleButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка графика начисления процентов не отображается"));
        TestListener.takeScreenshot();
    }
    @Step("Проверка отображения кнопки 'Информации о пополнении депозита' у активного пользователя")
    public void assertPaymentInfoButtonDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.paymentInfoButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка информации о пополнении депозита не отображается"));
        TestListener.takeScreenshot();
    }
    @Step("Проверка отображения кнопки 'Проолнгации депозита' у активного пользователя")
    public void assertExtendDepositButtonDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.extendDepositButtonDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Кнопка пролонгации депозита не отображается"));
        TestListener.takeScreenshot();
    }
}
