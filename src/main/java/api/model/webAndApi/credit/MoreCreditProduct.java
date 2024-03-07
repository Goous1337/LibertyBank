package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MoreCreditProduct {

    private Integer id;
    private String name;
    private Integer minSum;
    private Integer maxSum;
    private String currencyCode;
    private Double interestRate;
    private Boolean needGuarantees;
    private Boolean deliveryInCash;
    private Boolean earlyRepayment;
    private Boolean needIncomeDetails;
    private Integer minPeriodMonths;
    private Integer maxPeriodMonths;
    private String calculationMode;
    private Integer gracePeriodMonths;
    private Boolean rateIsAdjustable;
    private List<MoreCreditDetails> creditDetails;

    public String convertInterestRateToString(Double number){
        String str = String.valueOf(number).replace(".",",") + "%";
        return str;
    }
}
