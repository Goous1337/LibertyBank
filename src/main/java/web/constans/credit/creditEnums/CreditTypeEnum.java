package web.constans.credit.creditEnums;

import lombok.Getter;

public enum CreditTypeEnum {

    CREDIT_TYPE_CONSUMER("Потребительский"),
    CREDIT_TYPE_TARGET("Целевой");

    @Getter
    private final String typeCredit;

    CreditTypeEnum(String typeCredit) {
        this.typeCredit = typeCredit;
    }

    @Override
    public String toString() {
        return typeCredit;
    }
}
