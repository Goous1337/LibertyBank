package web.constans.credit.creditEnums;

import lombok.Getter;

public enum CreditMethodOfObtainEnum {
    CREDIT_METHOD_OF_OBTAIN_CARD("Карта"),
    CREDIT_METHOD_OF_OBTAIN_CASH("Наличные");

    @Getter
    private final String methodOfObtaining;

    CreditMethodOfObtainEnum(String methodOfObtainingCredit) {
        this.methodOfObtaining = methodOfObtainingCredit;
    }

    @Override
    public String toString() {
        return methodOfObtaining;
    }
}
