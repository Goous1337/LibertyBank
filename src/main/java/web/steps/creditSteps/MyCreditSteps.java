package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.creditPages.*;

import static web.constans.credit.CreditServiceConstants.NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE;

public class MyCreditSteps {

    protected CreditInfoPage creditInfoPage;
    protected MyCreditsPage myCreditPage;
    protected MyCreditDetailedInformationPage myCreditDetailedInformationPage;
    protected CreditProdutsPage creditProdutsPage;
    protected CreditProductDetailedInformationPage creditProductDetailedInformation;

    public MyCreditSteps() {
        creditInfoPage = new CreditInfoPage();
        myCreditPage = new MyCreditsPage();
        myCreditDetailedInformationPage = new MyCreditDetailedInformationPage();
        creditProdutsPage = new CreditProdutsPage();
        creditProductDetailedInformation = new CreditProductDetailedInformationPage();
    }


    @Step("Отображается сумма первого кредита по списку авторизированного пользователя")
    public void assertTextMyCreditSumIsDisplayed() {
        Assertions.assertTrue(myCreditPage.isMyCreditSumTextDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditPage.isMyCreditSumTextDisplayed()));
        TestListener.takeScreenshot();
    }

    @Step("Отображается наименование первого кредита по списку авторизированного пользователя")
    public void assertTextNameCreditIsDisplayed() {
        Assertions.assertTrue(myCreditPage.isNameCreditText(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditPage.isNameCreditText()));
        TestListener.takeScreenshot();
    }

    @Step("Отображается дата окончания кредита первого по списку авторизированного пользователя")
    public void assertTextTermCreditIsDisplayed() {
        Assertions.assertTrue(myCreditPage.isTermCreditText(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditPage.isTermCreditText()));
        TestListener.takeScreenshot();
    }

    @Step("Отображается кнопка 'Показать больше' первой по списку авторизированного пользователя")
    public void assertButtonShowMoreAboutCreditIsDisplayed() {
        Assertions.assertTrue(myCreditPage.isShowMoreAboutCreditButton(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, myCreditPage.isShowMoreAboutCreditButton()));
        TestListener.takeScreenshot();
    }

    @Step("Нажатие кнопки 'Показать больше' действующего кредиты у авторизированного пользователя")
    public void clickShowMoreAboutMyCreditButton() {
        myCreditPage.clickShowMoreMyCreditButton();
    }


}
