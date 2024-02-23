package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDataCalculator {
    private Integer depositProductId;
    private Float initialSum;
    private Integer termTime;
    private Boolean isCapitalisation;
    private String currencyCode;

    public DepositDataCalculator(Integer depositProductId, Integer termTime, Boolean isCapitalisation) {
        this.depositProductId = depositProductId;
        this.termTime = termTime;
        this.isCapitalisation = isCapitalisation;
    }

    public DepositDataCalculator(Integer depositProductId, Float initialSum, Integer termTime) {
        this.depositProductId = depositProductId;
        this.initialSum = initialSum;
        this.termTime = termTime;
    }

    public DepositDataCalculator(Integer depositProductId, Float initialSum, Boolean isCapitalisation) {
        this.depositProductId = depositProductId;
        this.initialSum = initialSum;
        this.isCapitalisation = isCapitalisation;
    }

    public DepositDataCalculator(Float initialSum, Integer termTime, Boolean isCapitalisation) {
        this.initialSum = initialSum;
        this.termTime = termTime;
        this.isCapitalisation = isCapitalisation;
    }
}
