package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.creditPages.*;

import static web.constans.CreditServiceConstants.*;

public class CreditInfoSteps {

    protected CreditInfoPage creditInfoPage;
    protected MyCreditsPage myCreditPage;


    public CreditInfoSteps() {
        creditInfoPage = new CreditInfoPage();
        myCreditPage = new MyCreditsPage();
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
    public void assertButtonSubmittedCreditRequestIsDisplayed() {
        Assertions.assertTrue(creditInfoPage.isSubmittedCreditRequestDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Поданные кредитные заявки"));
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
    public void assertUrlSubmittedCreditRequest() {
        Assertions.assertEquals(CREDIT_SUBMITTED_REQUEST_PAGE_URL, creditInfoPage.getActualTitleCredit(), String.format(NOT_EQUALS_URL_MESSAGE, CREDIT_SUBMITTED_REQUEST_PAGE_URL, creditInfoPage.getActualTitleCredit()));
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
    public void clickSubmittedCreditRequestButton() {
        creditInfoPage.clickSubmittedCreditRequestButton();
    }


}
