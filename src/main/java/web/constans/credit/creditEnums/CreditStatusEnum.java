package web.constans.credit.creditEnums;

import lombok.Getter;

public enum CreditStatusEnum {

    CREDIT_STATUS_PENDING("В обработке"),
    CREDIT_STATUS_REJECT("отказ по заявке банком"),
    CREDIT_STATUS_APPROVED("заявка одобрена банком"),
    CREDIT_STATUS_WITHDRAWN("отозвана клиентом"),
    CREDIT_STATUS_ACCEPTED("подтверждена клиентом");

    @Getter
    private final String valueCreditStatus;

    CreditStatusEnum(String valueCreditStatus) {
        this.valueCreditStatus = valueCreditStatus;
    }

    @Override
    public String toString() {
        return valueCreditStatus;
    }
}
