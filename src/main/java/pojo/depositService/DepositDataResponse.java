package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositDataResponse {
    private Integer depositProductId;
    private Double initialAmount;
    private String currencyCode;
    private String periodMonths;
    private Boolean autoRenewal;

    public DepositDataResponse() {
    }
}
