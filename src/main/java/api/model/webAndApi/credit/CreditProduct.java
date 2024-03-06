package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreditProduct {

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
    private List<CreditDetails> creditDetails;

    public String convertInterestRateToString(Double number){
        String str = String.valueOf(number).replace(".",",") + "%";
        return str;
    }
}
