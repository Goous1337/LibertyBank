package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDataCalculator {
    private Integer depositProductId;
    private Integer initialSum;
    private Integer termTime;
    private Boolean isCapitalisation;

    public DepositDataCalculator(Integer depositProductId, Integer termTime, Boolean isCapitalisation) {
        this.depositProductId = depositProductId;
        this.termTime = termTime;
        this.isCapitalisation = isCapitalisation;
    }

    public DepositDataCalculator(Integer depositProductId, Integer initialSum, Integer termTime) {
        this.depositProductId = depositProductId;
        this.initialSum = initialSum;
        this.termTime = termTime;
    }

}
