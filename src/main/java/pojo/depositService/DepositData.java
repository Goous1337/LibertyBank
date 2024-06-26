package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DepositData {
    private Integer depositProductId;
    private Float initialAmount;
    private String depositTerm;
    private String currencyCode;
    private Boolean autoRenewal;

    public DepositData(Integer depositProductId, Float initialAmount, String currencyCode, Boolean autoRenewal) {
        this.depositProductId = depositProductId;
        this.initialAmount = initialAmount;
        this.currencyCode = currencyCode;
        this.autoRenewal = autoRenewal;
    }

}
