package web.constans.common;

import lombok.Getter;

public enum CommonCurrencyEnum {
    CURRENCY_RUB("RUB"),
    CURRENCY_EUR("EUR"),
    CURRENCY_USD("USD");

    @Getter
    private final String currency;

    CommonCurrencyEnum(String valueCreditName) {
        this.currency = valueCreditName;
    }

    @Override
    public String toString() {
        return currency;
    }
}
