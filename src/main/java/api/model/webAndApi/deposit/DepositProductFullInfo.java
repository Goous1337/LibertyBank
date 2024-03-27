package api.model.webAndApi.deposit;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepositProductFullInfo {
    private Integer id;
    private String name;
    private String schemaName;
    private Double interestRateEarly;
    private Boolean isCapitalization;
    private Double amountMin;
    private Double amountMax;
    private Integer minDurationMonths;
    private Integer maxDurationMonths;
    private String currencyCode;
    private Boolean isActive;
    private Boolean isRevocable;
    private String productDetails;
    private Boolean autoRenewal;
    private Double maxInterestRate;
    private List<DepositProductDetails> depositDetail;


    public DepositProductFullInfo() {

    }

    public DepositProductFullInfo(String name,
                                  String productDetails,
                                  Double maxInterestRate,
                                  Double amountMin,
                                  Double amountMax,
                                  Integer maxDurationMonths,
                                  List<DepositProductDetails> depositDetail) {
        this.name = name;
        this.productDetails = productDetails;
        this.maxInterestRate = maxInterestRate;
        this.amountMin = amountMin;
        this.amountMax = amountMax;
        this.maxDurationMonths = maxDurationMonths;
        this.depositDetail = depositDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DepositProductFullInfo)) {
            return false;
        }

        DepositProductFullInfo depositProductFullInfo = (DepositProductFullInfo) o;
        return getName().equals(depositProductFullInfo.getName()) &&
                getProductDetails().equals(depositProductFullInfo.getProductDetails()) &&
                getMaxInterestRate().equals(depositProductFullInfo.getMaxInterestRate()) &&
                getAmountMin().equals(depositProductFullInfo.getAmountMin()) &&
                getAmountMax().equals(depositProductFullInfo.getAmountMax()) &&
                getMaxDurationMonths().equals(depositProductFullInfo.getMaxDurationMonths()) &&
                getDepositDetail().equals(depositProductFullInfo.getDepositDetail());
    }
}
