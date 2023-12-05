package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositDataIncorrectValues {
    private Integer depositProductId;
    private String initialAmount;
    private String periodMonths;
    private Boolean autoRenewal;

    public DepositDataIncorrectValues() {
    }
}
