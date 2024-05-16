package web.pages.creditPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.constans.credit.CreditReportValueEnum;
import web.pages.BasePage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreditApplicationReportPage extends BasePage {
    private Map<String, String> valuesCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Название кредитного продукта:')]/following-sibling::td")
    private WebElement valueNameCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Дата создания заявки:')]/following-sibling::td")
    private WebElement valueDataCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Статус заявки:')]/following-sibling::td/p")
    private WebElement valueStatusCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Процентная ставка:')]/following-sibling::td")
    private WebElement valueInterestRateCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Запрашиваемая сумма кредита:')]/following-sibling::td")
    private WebElement valueSummaCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Валюта кредита:')]/following-sibling::td")
    private WebElement valueCurrencyCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Запрашиваемый период кредита:')]/following-sibling::td")
    private WebElement valueTermCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Способ получения:')]/following-sibling::td")
    private WebElement valueMethodOfObtainingCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Тип кредита:')]/following-sibling::td")
    private WebElement valueTypeCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Схема расчета платежей:')]/following-sibling::td")
    private WebElement valuePaymentСalculationSchemeCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Тип ставки:')]/following-sibling::td")
    private WebElement valueLoanRateTypeCreditReport;
    @FindBy(xpath = "//button[contains(text(), 'Узнать статус заявки')]")
    private WebElement buttonStatusOfTheApplicationCredit;
    @FindBy(xpath = "//button[contains(text(), 'Отправить PDF')]")
    private WebElement buttonSendPdfApplicationCredit;

    public String getValueNameCreditReport() {
        return getTextElement(valueNameCreditReport);
    }

    public String getValueDataCreditReport() {
        return getTextElement(valueDataCreditReport);
    }

    public String getValueStatusCreditReport() {
        return getTextElement(valueStatusCreditReport);
    }

    public String getValueInterestRateCreditReport() {
        return getTextElement(valueInterestRateCreditReport);
    }

    private String getValueSummaCreditReport() {
        return getTextElement(valueSummaCreditReport);
    }

    public String getValueCurrencyCreditReport() {
        return getTextElement(valueCurrencyCreditReport);
    }

    public String getValueTermCreditReport() {
        return getTextElement(valueTermCreditReport);
    }

    public String getValueMethodOfObtainingCreditReport() {
        return getTextElement(valueMethodOfObtainingCreditReport);
    }

    public String getValueTypeCreditReport() {
        return getTextElement(valueTypeCreditReport);
    }

    public String getValuePaymentСalculationSchemeCreditReport() {
        return getTextElement(valuePaymentСalculationSchemeCreditReport);
    }

    public String getValueLoanRateTypeCreditReport() {
        return getTextElement(valueLoanRateTypeCreditReport);
    }

    public Map<String, String> putToMapValuesCreditReport() {
        valuesCreditReport = new HashMap<>();
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_NAME_CONSTANT.getValueCreditReport(), getValueNameCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_DATA_CONSTANT.getValueCreditReport(), getValueDataCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_STATUS_CONSTANT.getValueCreditReport(), getValueStatusCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_INTEREST_RATE_CONSTANT.getValueCreditReport(), getValueInterestRateCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_SUMMA_CONSTANT.getValueCreditReport(), getValueSummaCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_CURRENCY_CONSTANT.getValueCreditReport(), getValueCurrencyCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_TERM_CONSTANT.getValueCreditReport(), getValueTermCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_METHOD_OF_OBTAINING_CONSTANT.getValueCreditReport(), getValueMethodOfObtainingCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_TYPE_CONSTANT.getValueCreditReport(), getValueTypeCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_PAYMENT_СALCULATION_SCHEME_CONSTANT.getValueCreditReport(), getValuePaymentСalculationSchemeCreditReport());
        valuesCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_LOAN_RATE_TYPE_CONSTANT.getValueCreditReport(), getValueLoanRateTypeCreditReport());
        return valuesCreditReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditApplicationReportPage that = (CreditApplicationReportPage) o;
        return Objects.equals(valuesCreditReport, that.valuesCreditReport) &&
                Objects.equals(valueNameCreditReport, that.valueNameCreditReport) &&
                Objects.equals(valueDataCreditReport, that.valueDataCreditReport) &&
                Objects.equals(valueStatusCreditReport, that.valueStatusCreditReport) &&
                Objects.equals(valueInterestRateCreditReport, that.valueInterestRateCreditReport) &&
                Objects.equals(valueSummaCreditReport, that.valueSummaCreditReport) &&
                Objects.equals(valueCurrencyCreditReport, that.valueCurrencyCreditReport) &&
                Objects.equals(valueTermCreditReport, that.valueTermCreditReport) &&
                Objects.equals(valueMethodOfObtainingCreditReport, that.valueMethodOfObtainingCreditReport) &&
                Objects.equals(valueTypeCreditReport, that.valueTypeCreditReport) &&
                Objects.equals(valuePaymentСalculationSchemeCreditReport, that.valuePaymentСalculationSchemeCreditReport) &&
                Objects.equals(valueLoanRateTypeCreditReport, that.valueLoanRateTypeCreditReport) &&
                Objects.equals(buttonStatusOfTheApplicationCredit, that.buttonStatusOfTheApplicationCredit) &&
                Objects.equals(buttonSendPdfApplicationCredit, that.buttonSendPdfApplicationCredit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valuesCreditReport,
                valueNameCreditReport,
                valueDataCreditReport,
                valueStatusCreditReport,
                valueInterestRateCreditReport,
                valueSummaCreditReport,
                valueCurrencyCreditReport,
                valueTermCreditReport,
                valueMethodOfObtainingCreditReport,
                valueTypeCreditReport,
                valuePaymentСalculationSchemeCreditReport,
                valueLoanRateTypeCreditReport,
                buttonStatusOfTheApplicationCredit,
                buttonSendPdfApplicationCredit);
    }
}
