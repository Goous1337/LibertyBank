package api.model.webAndApi.deposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositProductShortInfo {
    private Integer id;
    private String name;
    private Double maxInterestRate;
    private String productDetails;
    private Boolean minDurationMonths;
    private Integer maxDurationMonths;
    private Double amountMin;
    private Double amountMax;
    private String currencyCode;

    public DepositProductShortInfo() {

    }

    public DepositProductShortInfo(String name, Double maxInterestRate, String productDetails, int maxDurationMonths, Double amountMin) {
        this.name = name;
        this.maxInterestRate = maxInterestRate;
        this.productDetails = productDetails;
        this.maxDurationMonths = maxDurationMonths;
        this.amountMin = amountMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DepositProductShortInfo)) {
            return false;
        }

        DepositProductShortInfo depositProductShortInfo = (DepositProductShortInfo) o;
        return  getName().equals(depositProductShortInfo.getName()) &&
                getMaxInterestRate().equals(depositProductShortInfo.getMaxInterestRate()) &&
                getProductDetails().equals(depositProductShortInfo.getProductDetails()) &&
                getMaxDurationMonths().equals(depositProductShortInfo.getMaxDurationMonths()) &&
                getAmountMin().equals(depositProductShortInfo.getAmountMin());
    }

}
