package web.constans.credit.creditEnums;

import lombok.Getter;

public enum CreditNameEnum {
    LIBERTY_CASH("Liberty Наличными"),
    LIBERTY_EXPRESS("Liberty Срочный"),
    LIBERTY_MONEY("Liberty Money"),
    LIBERTY_EASY("Liberty Easy"),
    LIBERTY_CAR("Liberty Car"),
    LIBERTY_MY_FLAT("Моя квартира");

    @Getter
    private final String valueCreditName;

    CreditNameEnum(String valueCreditName) {
        this.valueCreditName = valueCreditName;
    }

    @Override
    public String toString() {
        return valueCreditName;
    }
}
