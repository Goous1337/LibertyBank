package web.constans.credit.creditEnums;

import lombok.Getter;

public enum CreditPaymentSchemeEnum {
    CREDIT_TYPE_ANNUITY("Аннуитетная"),
    CREDIT_TYPE_("Дифференцированный");

    @Getter
    private final String paymentSchemeCredit;

    CreditPaymentSchemeEnum(String paymentSchemeCredit) {
        this.paymentSchemeCredit = paymentSchemeCredit;
    }

    @Override
    public String toString() {
        return paymentSchemeCredit;
    }
}
