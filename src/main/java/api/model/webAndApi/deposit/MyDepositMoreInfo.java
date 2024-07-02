package api.model.webAndApi.deposit;

import lombok.Getter;
import lombok.Setter;
import web.constans.deposit.DepositsConstants;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class MyDepositMoreInfo {
    private String name;
    private String schemaName;
    private Date openDate;
    private Date closeDate;
    private Integer periodMonths;
    private Double initialAmount;
    private Double currentBalance;
    private Double interestRate;
    private String currencyCode;
    private String 	depaccountNumber;
    private Boolean autoRenewal;
    private Boolean isRevocable;
    private Boolean isActive;

}
