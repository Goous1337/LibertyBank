package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class CreditProduct {
    @Setter
    private Integer id;
    @Setter
    private String name;
    @Setter
    private Integer minSum;
    @Setter
    private Integer maxSum;
    @Setter
    private  String currencyCode;
    @Setter
    private Double interestRate;
    @Setter
    private Boolean needGuarantees;
    @Setter
    private Boolean deliveryInCash;
    @Setter
    private Boolean earlyRepayment;
    @Setter
    private Boolean needIncomeDetails;
    @Setter
    private Integer minPeriodMonths;
    @Setter
    private Integer maxPeriodMonths;
    @Setter
    private String calculationMode;
    @Setter
    private Integer gracePeriodMonths;
    @Setter
    private Boolean rateIsAdjustable;
    @Setter
    private List<CreditDetails> creditDetails;

    public String getNameProduct() {
        return name;
    }
}
