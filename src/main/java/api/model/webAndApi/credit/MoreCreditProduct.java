package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoreCreditProduct {
    private Integer id;
    private String name;
    private Double minSum;
    private Double maxSum;
    private List<String> currencyCodeList;
    private Double interestRate;
    private Boolean needGuarantees;
    private Boolean deliveryInCash;
    private Boolean earlyRepayment;
    private Boolean needIncomeDetails;
    private Integer minPeriodMonths;
    private Integer maxPeriodMonths;
    private String calculationMode;
    private Integer gracePeriodMonths;
    private Boolean rateIsAdjustable;
    private String tagline;
    private List<MoreCreditDetails> creditDetails;

    public MoreCreditProduct() {
    }

    public MoreCreditProduct(String name, Double interestRate, Double minSum, Double maxSum) {
        this.name = name;
        this.interestRate = interestRate;
        this.minSum = minSum;
        this.maxSum = maxSum;
    }

    public MoreCreditProduct(String name, Double interestRate, Double minSum, Double maxSum, List<MoreCreditDetails> creditDetails) {
        this.name = name;
        this.interestRate = interestRate;
        this.minSum = minSum;
        this.maxSum = maxSum;
        this.creditDetails = creditDetails;
    }

    public MoreCreditProduct(Double interestRate, Double minSum, Double maxSum, List<MoreCreditDetails> creditDetails) {
        this.interestRate = interestRate;
        this.minSum = minSum;
        this.maxSum = maxSum;
        this.creditDetails = creditDetails;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MoreCreditProduct)) {
            return false;
        }
        MoreCreditProduct moreCreditProduct = (MoreCreditProduct) obj;
        return getName().equals(moreCreditProduct.getName()) &&
                getInterestRate().equals(moreCreditProduct.getInterestRate()) &&
                getMinSum().equals(moreCreditProduct.getMinSum()) &&
                getMaxSum().equals(moreCreditProduct.getMaxSum());
    }

}
