package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.creditPages.*;

import static web.constans.CreditServiceConstants.NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE;

public class MyCreditDetailedInformationSteps {
    protected MyCreditDetailedInformationPage myCreditDetailedInformationPage;

    public MyCreditDetailedInformationSteps() {
        myCreditDetailedInformationPage = new MyCreditDetailedInformationPage();
    }

    @Step("Проверка отображения 'Название кредитного продукта' у активного кредита пользователя")
    public void assertTextNameTitleCreditIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.nameTitleCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.nameTitleCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Номер счета' у активного кредита пользователя")
    public void assertTextAccountNumberCreditIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.accountNumberCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.accountNumberCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Статус кредита' у активного кредита пользователя")
    public void assertTextStatusCreditIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.statusCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.statusCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Ближайший платеж' у активного кредита пользователя")
    public void assertTextNextPaymentCreditIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.nextPaymentCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.nextPaymentCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Дата ближайшего платежа (с учетом процентной части)' у активного кредита пользователя")
    public void assertTextPaymentDateCreditTextIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.paymentDateCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.paymentDateCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Сумма кредита' у активного кредита пользователя")
    public void assertTextLimitCreditIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.limitCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.limitCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Сумма остатка по кредиту ('Осталось погасить')' у активного кредита пользователя")
    public void assertTextRepayCreditIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.repayCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.repayCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Срок кредита' у активного кредита пользователя")
    public void assertTextPeriodMonthsCreditIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.periodMonthsCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.periodMonthsCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Ставка' у активного кредита пользователя")
    public void assertTextInterestRateCreditIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.interestRateCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.interestRateCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Нажатие кнопки 'Многоточие' действующего кредиты у авторизированного пользователя")
    public void clickDotsButton() {
        myCreditDetailedInformationPage.clickDotsButton();
    }

    @Step("Проверка отображения кнопки 'Рекзвиты' у активного кредита пользователя")
    public void assertButtonRequisitesIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.requisitesButtonDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.requisitesButtonDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'График платежей' у активного кредита пользователя")
    public void assertButtonPaymentScheduleIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.paymentScheduleButtonDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.paymentScheduleButtonDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Нажатие кнопки скопировать номер счет клиента")
    public void clickCopyAccountNumberCreditButton() {
        myCreditDetailedInformationPage.clickCopyAccountNumberCreditButton();
    }

    @Step("Проверка отображения текста 'Скопированное после нажатия кнопки скопировать счет'")
    public void assertTextOutputCopiedIsDisplayed() {
        Assertions.assertTrue(myCreditDetailedInformationPage.outputCopiedTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditDetailedInformationPage.outputCopiedTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Сравнение подброной информации о кредитном продукте банка в БД и UI")
    public void assertMoreInfoAboutCreditProducts() {
        Assertions.assertEquals(myCreditDetailedInformationPage.getMoreMyCreditInformationObjectFromBackEnd(),
                myCreditDetailedInformationPage.getMoreMyCreditInformationObjectFromWeb(),
                "Актуальный результат не соответствует ожидаемому");
    }
}
