package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditProduct {

    private Integer id;
    private String name;
    private Double interestRate;
    private String currencyCode;
    private String details;
    private Integer minPeriodMonths;
    private Integer maxPeriodMonths;
    private Double minSum;
    private Double maxSum;
    private String typeCredit;

    public CreditProduct() {

    }

    public CreditProduct(String name, Double interestRate) {
        this.name = name;
        this.interestRate = interestRate;
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
        return  getName().equals(creditProduct.getName()) &&
                getInterestRate().equals(creditProduct.getInterestRate());
    }

}
