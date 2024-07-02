package web.constans.credit.creditEnums;

import lombok.Getter;

public enum CreditLoanRateTypeEnum {
    CREDIT_LOAN_RATE_TYPE_FIX("Фиксированная"),
    CREDIT_LOAN_RATE_TYPE_LINKED("Привязанная к СР");

    @Getter
    private final String loanRateType;

    CreditLoanRateTypeEnum(String loanRateType) {
        this.loanRateType = loanRateType;
    }

    @Override
    public String toString() {
        return loanRateType;
    }
}
