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
    private String 	depAccountNumber;
    private Boolean autoRenewal;
    private Boolean isRevocable;
    private Boolean isActive;

    public MyDepositMoreInfo(String name, String depAccountNumber, Date openDate, Date closeDate, Integer periodMonths, Double interestRate) {
        this.name = name;
        this.depAccountNumber = depAccountNumber;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.periodMonths = periodMonths;
        this.interestRate = interestRate;
    }

    public String getStringDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DepositsConstants.DATE_FORMAT);
        return dateFormat.format(date);
    }
}
