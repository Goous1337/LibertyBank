package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import web.pages.creditPages.CreditApplicationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.INVALID_COLOR;

public class CreditApplicationSteps {

    protected CreditApplicationPage creditApplicationPage;

    public CreditApplicationSteps() {
        creditApplicationPage = new CreditApplicationPage();
    }

    @Step("Ввод значения в поле ввода 'Сумма кредита'")
    public void enterValidateAmountCreditInput(String amountCredit) {
        creditApplicationPage.enterSummaCredit(amountCredit);
    }

    @Step("Ввод значения в поле ввода 'Срок кредита'")
    public void enterPeriodMonthsCreditInput(String periodMonths) {
        creditApplicationPage.enterPeriodMonthsCredit(periodMonths);
    }

    @Step("Ввод значения в поле ввода 'Идентификационный номер работодателя'")
    public void enterIdentificationNumberCreditInput(String identificationNumber) {
        creditApplicationPage.enterIdentificationNumberCredit(identificationNumber);
    }

    @Step("Ввод значения в поле ввода 'Общая долговая нагрузка'")
    public void enterMonthlyExpenditureCreditInput(String monthlyExpenditure) {
        creditApplicationPage.enterMonthlyExpenditureCredit(monthlyExpenditure);
    }

    @Step("Ввод значения в поле ввода 'Среднемесячный доход'")
    public void enterMonthlyIncomeCreditInput(String monthlyIncome) {
        creditApplicationPage.enterMonthlyIncomeCredit(monthlyIncome);
    }

    @Step("Проверка на отображения уведомления об ошибке в поле 'Cумма кредита'")
    public void assertAmountErrorMessageIsDisplayed() throws NoSuchElementException {
        Assertions.assertTrue(creditApplicationPage.textErrorAmountCreditIsDisplayed(),
                "Отстсвует сообщение об вводе не валидных значений в поле 'Cумма кредита'");
    }

    @Step("Проверка на отображения уведомления об ошибке в поле 'Срок кредита'")
    public void assertErrorPeriodMonthMessageIsDisplayed() throws NoSuchElementException {
        Assertions.assertTrue(creditApplicationPage.textErrorPeriodMonthCreditIsDisplayed(),
                "Данные валидны в поле 'Срок кредита'");
    }

    @Step("Проверка на отображения уведомления об ошибке в поле 'Идентификационный номер работодателя'")
    public void assertEmployerIdentificationNumberErrorMessageIsDisplayed(String employerIdentificationNumber) {
        Assertions.assertTrue(creditApplicationPage.textEmployerIdentificationNumberErrorIsDisplayed(employerIdentificationNumber),
                "Уведомление об ошибке не отобразилось для 'Идентификационный номер работодателя'");
    }

    @Step("Проверка на отображения уведомления об ошибке в поле 'Общая долговая нагрузка'")
    public void assertTotalDebtLoadErrorMessageIsDisplayed(String totalDebtLoad) {
        Assertions.assertTrue(creditApplicationPage.textTotalDebtLoadErrorIsDisplayed(totalDebtLoad),
                "Уведомление об ошибке не отобразилось для 'Общая долговая нагрузка'");
    }

    @Step("Проверка стиля кнопки 'Отправить заявку' при правильных значениях")
    public void assertSubmitButtonSuccessful(String bgButtonColor, String textButtonColor) {
        assertTrue(creditApplicationPage.checkButtonCondition(bgButtonColor, textButtonColor, true), INVALID_COLOR);
    }

    @Step("Проверка стиля кнопки 'Отправить заявку' при неправильных значениях")
    public void assertSubmitButtonInvalid(String bgButtonColor, String textButtonColor) {
        assertTrue(creditApplicationPage.checkButtonCondition(bgButtonColor, textButtonColor, false), INVALID_COLOR);
    }

    @Step("Продолжение оформления заявки после нажатия на кнопку 'Отправить заявку'")
    public void sendApplicationForm() {
        creditApplicationPage.clickSendCredit();
    }

    @Step("Ввод 6-ти значного кода")
    public void enterGenerationCode(String code) {
        creditApplicationPage.sendCode(code);
    }

    @Step("Продолжить действия после ввода 6-ти значного кода")
    public void clickNextButton() {
        creditApplicationPage.clickNextButton();
    }

    @Step("Проверка появления формы отчета")
    public void reportIsVisible() {
        creditApplicationPage.checkVisibleReport();
    }

}
