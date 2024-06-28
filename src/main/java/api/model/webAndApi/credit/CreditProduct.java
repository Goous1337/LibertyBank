package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreditProduct {

    private Integer id;
    private String name;
    private Double interestRate;
    private List<String> currencyCodeList;
    private String details;
    private Integer minPeriodMonths;
    private Integer maxPeriodMonths;
    private Double minSum;
    private Double maxSum;
    private String creditPurpose;

    public CreditProduct() {

    }

    public CreditProduct(String name, Double interestRate, String details, Double minSum) {
        this.name = name;
        this.interestRate = interestRate;
        this.details = details;
        this.minSum = minSum;
    }

    public CreditProduct(String name, Double interestRate, String details, Double minSum, Integer maxPeriodMonths) {
        this.name = name;
        this.interestRate = interestRate;
        this.details = details;
        this.minSum = minSum;
        this.maxPeriodMonths = maxPeriodMonths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CreditProduct)) {
            return false;
        }

        CreditProduct creditProduct = (CreditProduct) o;
        return getName().equals(creditProduct.getName()) &&
                getInterestRate().equals(creditProduct.getInterestRate()) &&
                getDetails().equals(creditProduct.getDetails()) &&
                getMinSum().equals(creditProduct.getMinSum()) &&
                getMaxPeriodMonths().equals(creditProduct.getMaxPeriodMonths());
    }

}
