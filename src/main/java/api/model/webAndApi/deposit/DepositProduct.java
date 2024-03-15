package api.model.webAndApi.deposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositProduct {
    private Integer id;
    private String name;
    private Double maxInterestRate;
    private String productDetails;
    private Boolean minDurationMonths;
    private Integer maxDurationMonths;
    private Double amountMin;
    private Double amountMax;
    private String currencyCode;

    public DepositProduct() {

    }

    public DepositProduct(String name, Double maxInterestRate, String productDetails, int maxDurationMonths, Double amountMin) {
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

        if (!(o instanceof DepositProduct)) {
            return false;
        }

        DepositProduct depositProduct = (DepositProduct) o;
        return  getName().equals(depositProduct.getName()) &&
                getMaxInterestRate().equals(depositProduct.getMaxInterestRate()) &&
                getProductDetails().equals(depositProduct.getProductDetails()) &&
                getMaxDurationMonths().equals(depositProduct.getMaxDurationMonths()) &&
                getAmountMin().equals(depositProduct.getAmountMin());
    }

}
