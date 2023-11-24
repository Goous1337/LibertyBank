package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DepositData {
    private Integer depositProductId;
    private Double initialAmount;
    private String periodMonths;
    private Boolean autoRenewal;

    public DepositData(Integer depositProductId, Double initialAmount, Boolean autoRenewal) {
        this.depositProductId = depositProductId;
        this.initialAmount = initialAmount;
        this.autoRenewal = autoRenewal;
    }
}
