package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.creditPages.CreditApplicationsPage;


import static web.constans.credit.CreditServiceConstants.NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE;

public class CreditApplicationInfoSteps {
    protected CreditApplicationsPage  applicationsPage;

    public CreditApplicationInfoSteps() {
        applicationsPage = new CreditApplicationsPage();
    }

    @Step("Проверка отображения количества поданных кредитных заявок")
    public void assertAmountOfCreditApplications() {
        Assertions.assertTrue(applicationsPage.isAmountOfCreditApplicationsDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Количество поданных заявок не отображается"));
    }

    @Step("Проверка отображения статуса кредитной заявки")
    public void assertStatusOfCreditApplication() {
        Assertions.assertTrue(applicationsPage.isStatusOfCreditApplicationDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Статус заявки не отображается"));
    }

    @Step("Проверка отображения название кредитного продукта")
    public void assertNameCreditProduct() {
        Assertions.assertTrue(applicationsPage.isNameCreditProductDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Название кредитного продукта не отображается"));
    }

    @Step("Проверка отображения суммы кредита")
    public void assertSumOfCredit() {
        Assertions.assertTrue(applicationsPage.isSumOfCreditDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Сумма кредита не отображается"));
    }

    @Step("Проверка отображения срока кредитования")
    public void assertDateOfCredit() {
        Assertions.assertTrue(applicationsPage.isDateOfCreditDisplayed(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Срок кредитования не отображается"));
    }

    @Step("Проверка отображения процента кредитования")
    public void assertPercentOfCredit() {
        Assertions.assertTrue(applicationsPage.isPercentOfCredit(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Процент кредитования не отображается"));
    }

    @Step("Проверка отображения даты создания заявки")
    public void assertDateOfCreateApplication() {
        Assertions.assertTrue(applicationsPage.isDateOfCreateApplication(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Дата создания заявки не отображается"));
    }

    @Step("Проверка сообщения при отсутствии заявок на кредит")
    public void assertMessageNoApplications() {
        Assertions.assertTrue(applicationsPage.isNoApplications(), String.format(NOT_DISPLAYED_CREDIT_WEB_ELEMENT_MESSAGE, "Сообщение об отсутствии заявок"));
    }

    @Step("Сравнение данных заявки на кредит UI и API")
    public void assertDataOfApplication() {
        Assertions.assertEquals(applicationsPage.getCreditApplicationsWeb(), applicationsPage.getCreditApplicationsBackend(), "Актуальный результат не соответствует ожидаемому");
    }
}
