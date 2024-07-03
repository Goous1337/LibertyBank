package web.constans.credit.creditEnums;

import lombok.Getter;

public enum CreditReportValueEnum {
    CREDIT_REPORT_NAME_CONSTANT("Название кредитного продукта"),
    CREDIT_REPORT_DATA_CONSTANT("Дата создания заявки"),
    CREDIT_REPORT_STATUS_CONSTANT("Статус заявки"),
    CREDIT_REPORT_INTEREST_RATE_CONSTANT("Процентная ставка"),
    CREDIT_REPORT_SUMMA_CONSTANT("Запрашиваемая сумма кредита"),
    CREDIT_REPORT_CURRENCY_CONSTANT("Валюта кредита"),
    CREDIT_REPORT_TERM_CONSTANT("Запрашиваемый период кредита"),
    CREDIT_REPORT_METHOD_OF_OBTAINING_CONSTANT("Способ получения"),
    CREDIT_REPORT_TYPE_CONSTANT("Тип кредита"),
    CREDIT_REPORT_PAYMENT_СALCULATION_SCHEME_CONSTANT("Схема расчета платежей"),
    CREDIT_REPORT_LOAN_RATE_TYPE_CONSTANT("Тип ставки");

    @Getter
    private String valueCreditReport;

    private CreditReportValueEnum(String valueCreditReport) {
        this.valueCreditReport = valueCreditReport;
    }

    @Override
    public String toString() {
        return valueCreditReport;
    }
}
