package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.creditPages.CreditApplicationPage;

public class CreditApplicationSteps {

    protected CreditApplicationPage creditApplicationPage;

    public CreditApplicationSteps() {
        creditApplicationPage = new CreditApplicationPage();
    }

    @Step("Ввод значения в поле ввода 'Сумма кредита'")
    public void assertValidateAmountCreditInput(String amountCredit) {
        creditApplicationPage.enterSummaCredit(amountCredit);
    }

    @Step("Ввод значения в поле ввода 'Срок кредита'")
    public void assertPeriodMonthsCreditInput(String periodMonths) {
        creditApplicationPage.enterPeriodMonthsCredit(periodMonths);
    }

    @Step("Ввод значения в поле ввода 'Идентификационный номер работодателя'")
    public void assertIdentificationNumberCreditInput(String identificationNumber) {
        creditApplicationPage.enterIdentificationNumberCredit(identificationNumber);
    }

    @Step("Ввод значения в поле ввода 'Общая долговая нагрузка'")
    public void assertMonthlyExpenditureCreditInput(String monthlyExpenditure) {
        creditApplicationPage.enterMonthlyExpenditureCredit(monthlyExpenditure);
    }

    @Step("Ввод значения в поле ввода 'Среднемесячный доход'")
    public void assertMonthlyIncomeCreditInput(String monthlyIncome) {
        creditApplicationPage.enterMonthlyIncomeCredit(monthlyIncome);
    }

    @Step("Проверка на отображения уведомления об ошибке в поле 'Cумма кредита'")
    public void assertAmountErrorMessageIsDisplayed() {
        Assertions.assertFalse(creditApplicationPage.textErrorAmountCreditIsDisplayed(),
                "Данные валидны в поле 'Cумма кредита'");
    }

    @Step("Проверка на отображения уведомления об ошибке в поле 'Срок кредита'")
    public void assertErrorPeriodMonthMessageIsDisplayed() {
        Assertions.assertFalse(creditApplicationPage.textErrorPeriodMonthCreditIsDisplayed(),
                "Данные валидны в поле 'Срок кредита'");
    }

}
