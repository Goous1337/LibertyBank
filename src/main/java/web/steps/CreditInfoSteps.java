package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.CreditInfoPage;

import static web.constans.CreditServiceConstants.*;
import static web.constans.AccountServiceConstants.*;
import static web.constans.CreditConstants.*;

public class CreditInfoSteps {

    protected CreditInfoPage creditInfoPage;

    public CreditInfoSteps() {
        creditInfoPage = new CreditInfoPage();
    }

    @Step("Отображается кнопка с текстом 'Мои кредиты'")
    public void assertButtonMyCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.isMyCreditButtonDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Мои кредиты"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка с текстом 'Кредитные продукты банка'")
    public void assertButtonCreditProductsBankIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.isCreditProductsBankButtonDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Кредитные продукты банка"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка с текстом 'Поданные заявки'")
    public void assertButtonSubmittedCreditАpplicationsIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.isSubmittedCreditАpplicationsDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Поданные кредитные заявки"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается сумма первого кредита по списку авторизированного пользователя")
    public void assertTextMyCreditSumIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.isMyCreditSumTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.isMyCreditSumTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Отображается наименование первого кредита по списку авторизированного пользователя")
    public void assertTextNameCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.isNameCreditText(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.isNameCreditText()));
        TestListener.takeScreenshot();
    }

    @Step("Отображается дата окончания кредита первого по списку авторизированного пользователя")
    public void assertTextTermCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.isTermCreditText(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.isTermCreditText()));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Показать больше' первой по списку авторизированного пользователя")
    public void assertButtonShowMoreAboutCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.isShowMoreAboutCreditButton(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.isShowMoreAboutCreditButton()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка url Мои кредиты")
    public void assertUrlMyCretits() {
        Assertions.assertEquals(MY_CREDIT_PAGE_URL, creditInfoPage.getActualTitleCredit(), String.format(NOT_EQUALS_URL_MESSAGE, MY_CREDIT_PAGE_URL, creditInfoPage.getActualTitleCredit()));
    }

    @Step("Проверка url Кредитные продукты")
    public void assertUrlCreditProduct() {
        Assertions.assertEquals(CREDIT_PRODUCT_PAGE_URL, creditInfoPage.getActualTitleCredit(), String.format(NOT_EQUALS_URL_MESSAGE, CREDIT_PRODUCT_PAGE_URL, creditInfoPage.getActualTitleCredit()));
    }

    @Step("Проверка url Поданные кредитные заявки")
    public void assertUrlSubmittedCreditАpplications() {
        Assertions.assertEquals(CREDIT_SUBMITTED_АPPLICATIONS_PAGE_URL, creditInfoPage.getActualTitleCredit(), String.format(NOT_EQUALS_URL_MESSAGE, CREDIT_SUBMITTED_АPPLICATIONS_PAGE_URL, creditInfoPage.getActualTitleCredit()));
    }

    @Step("Проверка url подробной информации о моем кредите")
    public void assertUrlFirstMyCredit() {
        Assertions.assertEquals(MY_FIRST_CREDIT_PAGE_URL, creditInfoPage.getActualTitleCredit(), String.format(NOT_EQUALS_URL_MESSAGE, MY_FIRST_CREDIT_PAGE_URL, creditInfoPage.getActualTitleCredit()));
    }

    @Step("Нажатие кнопки 'Кредиты' в навбаре")
    public void clickCreditButton() {
        creditInfoPage.clickCreditButton();
    }

    @Step("Нажатие кнопки 'Мои кредиты' в разделе 'Кредиты'")
    public void clickMyCreditButton() {
        creditInfoPage.clickMyCreditButton();
    }

    @Step("Нажатие кнопки 'Кредитные продукты банка' в разделе 'Кредиты'")
    public void clickCreditProductButton() {
        creditInfoPage.clickCreditProductsBankButton();
    }

    @Step("Нажатие кнопки 'Поданные заявки' в разделе 'Кредиты'")
    public void clickSubmittedCreditАpplicationsButton() {
        creditInfoPage.clickSubmittedCreditАpplicationsButton();
    }

    @Step("Нажатие кнопки 'Показать больше' действующего кредиты у авторизированного пользователя")
    public void clickShowMoreAboutMyCreditButton() {
        creditInfoPage.clickShowMoreMyCreditButton();
    }

    @Step("Проверка отображения 'Название кредитного продукта' у активного кредита пользователя")
    public void assertTextNameTitleCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.nameTitleCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.nameTitleCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Номер счета' у активного кредита пользователя")
    public void assertTextAccountNumberCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.accountNumberCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.accountNumberCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Статус кредита' у активного кредита пользователя")
    public void assertTextStatusCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.statusCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.statusCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Ближайший платеж' у активного кредита пользователя")
    public void assertTextNextPaymentCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.nextPaymentCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.nextPaymentCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Дата ближайшего платежа (с учетом процентной части)' у активного кредита пользователя")
    public void assertTextPaymentDateCreditTextIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.paymentDateCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.paymentDateCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Сумма кредита' у активного кредита пользователя")
    public void assertTextLimitCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.limitCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.limitCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Сумма остатка по кредиту ('Осталось погасить')' у активного кредита пользователя")
    public void assertTextRepayCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.repayCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.repayCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Срок кредита' у активного кредита пользователя")
    public void assertTextPeriodMonthsCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.periodMonthsCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.periodMonthsCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Ставка' у активного кредита пользователя")
    public void assertTextInterestRateCreditIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.interestRateCreditTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.interestRateCreditTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Нажатие кнопки 'Многоточие' действующего кредиты у авторизированного пользователя")
    public void clickDotsButton() {
        creditInfoPage.clickDotsButton();
    }

    @Step("Проверка отображения кнопки 'Рекзвиты' у активного кредита пользователя")
    public void assertButtonRequisitesIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.requisitesButtonDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.requisitesButtonDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'График платежей' у активного кредита пользователя")
    public void assertButtonPaymentScheduleIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.paymentScheduleButtonDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.paymentScheduleButtonDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Нажатие кнопки скопировать номер счет клиента")
    public void clickCopyAccountNumberCreditButton() {
        creditInfoPage.clickCopyAccountNumberCreditButton();
    }

    @Step("Проверка отображения текста 'Скопированное после нажатия кнопки скопировать счет'")
    public void assertTextOutputCopiedIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.outputCopiedTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, creditInfoPage.outputCopiedTextDisplayed()));
        TestListener.takeScreenshot();
    }

    /*Краткая информация о 'Кредитные продукты банка'*/
    @Step("Сравнение названия кредита в краткой информации с БД и UI")
    public void assertShortTextNameCreditProductPageText() {
        Assertions.assertEquals(creditInfoPage.getShortNameProductsCreditBank(), creditInfoPage.getShortNameCreditExpressProductPageText(), "test");
    }

    /*Подрабная информация о 'Кредитные продукты банка'*/
    @Step("Сравнение названия кредита с БД и UI")
    public void assertTextNameCreditProductPageText() {
        Assertions.assertEquals(creditInfoPage.getNameProductsCreditBank(), creditInfoPage.getNameCreditProductPageText(), "test");
    }

    @Step("Сравнение процентной ставки кредита с БД и UI")
    public void assertInterestRateCreditProductPageText() {
        Assertions.assertEquals(creditInfoPage.getInterestRateProductCredit(), creditInfoPage.getInterestRateCreditProductPageText(), "test");
    }

    /*
        Клик кнопки 'Показать больше'
     */
    @Step("Клик кнопки 'Показать больше' у Liberty Наличными '")
    public void clickShowMoreLibertyCashButton() {
        creditInfoPage.clickButtonShowMoreLibertyCash();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Срочный '")
    public void clickShowMoreLibertyExpressButton() {
        creditInfoPage.clickButtonShowMoreLibertyExpress();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Money '")
    public void clickShowMoreLibertyMoneyButton() {
        creditInfoPage.clickButtonShowMoreLibertyMoney();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Easy '")
    public void clickShowMoreLibertyEasyButton() {
        creditInfoPage.clickButtonShowMoreLibertyEasy();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Car '")
    public void clickShowMoreLibertyCarButton() {
        creditInfoPage.clickButtonShowMoreLibertyCar();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Моя квартира '")
    public void clickShowMoreLibertyMyFlatButton() {
        creditInfoPage.clickButtonShowMoreLibertyMyFlat();
    }

}
