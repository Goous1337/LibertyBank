package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.CreditInfoPage;

import static web.constans.AccountServiceConstants.*;
import static web.constans.CreditConstants.*;

public class CreditInfoSteps {

    protected CreditInfoPage creditInfoPage;

    public CreditInfoSteps() {
        creditInfoPage = new CreditInfoPage();
    }

    /*
        Liberty Cash
     */
    @Step("Отображается название кредита")
    public void assertNameOfCredit() {
        Assertions.assertEquals(LIBERTY_IN_CASH, creditInfoPage.getLibertyCashCreditName(), MISMATCH);
        TestListener.takeScreenshot();
    }

    @Step("Отображается процентная ставка")
    public void assertProcentOfCredit() {
        Assertions.assertEquals(LIBERTY_IN_CASH_PROCENT, creditInfoPage.getLibertyCashProcent(), MISMATCH);
        TestListener.takeScreenshot();
    }

    @Step("Отображение описания кредита")
    public void assertDescription() {
        Assertions.assertTrue(creditInfoPage.getDescriptionLibertyCash(), String.format(NOT_DISPLAYED_MESSAGE, "Описание"));
    }

    @Step("Отображение 'Показать больше'")
    public void assertShowMore() {
        Assertions.assertTrue(creditInfoPage.isShowMoreLibertyCash(), String.format(NOT_DISPLAYED_MESSAGE, "Показать больше"));
    }

    @Step("Отображение 'Подать заявку'")
    public void assertGetApplication() {
        Assertions.assertTrue(creditInfoPage.isGetApplicationLibertyCash(), String.format(NOT_DISPLAYED_MESSAGE, "Подать заявку"));
    }

    /*
          Liberty Cash
     */
    @Step("Отображается название кредита")
    public void assertNameOfLibertyExpressCredit() {
        Assertions.assertEquals(LIBERTY_EXPRESS, creditInfoPage.getLibertyExpressCreditName(), MISMATCH);
        TestListener.takeScreenshot();
    }

    @Step("Отображается процентная ставка")
    public void assertProcentLibertyExpressCredit() {
        Assertions.assertEquals(LIBERTY_EXPRESS_PROCENT, creditInfoPage.getLibertyExpressProcent(), MISMATCH);
        TestListener.takeScreenshot();
    }

    @Step("Отображение описания кредита")
    public void assertDescriptionLibertyExpress() {
        Assertions.assertTrue(creditInfoPage.getDescriptionLibertyExpress(), String.format(NOT_DISPLAYED_MESSAGE, "Описание"));
    }

    @Step("Отображение 'Показать больше'")
    public void assertShowMoreLibertyExpress() {
        Assertions.assertTrue(creditInfoPage.isShowMoreLibertyExpress(), String.format(NOT_DISPLAYED_MESSAGE, "Показать больше"));
    }

    @Step("Отображение 'Подать заявку'")
    public void assertGetApplicationLibertyExpress() {
        Assertions.assertTrue(creditInfoPage.isGetApplicationLibertyExpress(), String.format(NOT_DISPLAYED_MESSAGE, "Подать заявку"));
    }
}
