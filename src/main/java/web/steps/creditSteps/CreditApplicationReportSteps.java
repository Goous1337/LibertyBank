package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.creditPages.CreditApplicationReportPage;

public class CreditApplicationReportSteps {
    protected CreditApplicationReportPage creditApplicationReportPage;

    public CreditApplicationReportSteps() {
        creditApplicationReportPage = new CreditApplicationReportPage();
    }

    @Step("Проверка появления формы отчета")
    public void reportIsVisible() {
        creditApplicationReportPage.checkVisibleReport();
    }

    @Step("Проверка вывода информации в отчете")
    public void assertCreditReports() {
        creditApplicationReportPage.putToMapXpathValuesCreditReport();
        Assertions.assertEquals(creditApplicationReportPage.convertExpectedMapToString(), creditApplicationReportPage.convertXpatxMapToString(), "");
    }

    @Step("Сбор данных со страницы подробной информации о кредитных продуктах банка - 'Название кредитного продукта' и 'Процентная ставка'")
    public void getValuesCreditProduct() {
        creditApplicationReportPage.putToMapCreditName();
        creditApplicationReportPage.putToMapCreditInterestRate();
    }

    @Step("Сбор данных со страницы оформлений кредитной заявки: 'Сумма','Срок кредита', 'Текущая дата', 'Статус кредита по умолчанию', 'Валюта кредита'")
    public void getValuesCreditApplicationStep(String summa, String term) {
        creditApplicationReportPage.putToMapCreditSum(summa);
        creditApplicationReportPage.putToMapCreditTerm(term);
        creditApplicationReportPage.putToMapCreditCurrency();
    }

    @Step("Сбор значений по умолчанию для нового созданных кредитных заявок: " +
            "'Текущая дата', " +
            "'Статус кредитной заявки'," +
            "'Способ получения', " +
            "'Тип кредита', " +
            "'Схема расчета'," +
            "'Тип ставки'")
    public void getDefaultValuesStep() {
        creditApplicationReportPage.putToMapCreditData();
        creditApplicationReportPage.putToMapCreditStatus();
        creditApplicationReportPage.putToMapCreditMethodOfObtaining();
        creditApplicationReportPage.putToMapCreditType();
        creditApplicationReportPage.putToMapCreditScheme();
        creditApplicationReportPage.putToMapCreditLoanRateType();
    }
}
