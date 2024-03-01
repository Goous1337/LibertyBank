package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DepositData {
    private Integer depositProductId;
    private Float initialAmount;
    private String depositTerm;
    private Boolean autoRenewal;

    public DepositData(Integer depositProductId, Float initialAmount, Boolean autoRenewal) {
        this.depositProductId = depositProductId;
        this.initialAmount = initialAmount;
        this.autoRenewal = autoRenewal;
    }
}
