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

    public MyDepositMoreInfo() {
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyDepositMoreInfo)) {
            return false;
        }
        MyDepositMoreInfo myDepositMoreInfo = (MyDepositMoreInfo) obj;
        return getName().equals(myDepositMoreInfo.getName()) &&
                getDepAccountNumber().equals(myDepositMoreInfo.getDepAccountNumber()) &&
                getOpenDate().equals(myDepositMoreInfo.getOpenDate()) &&
                getCloseDate().equals(myDepositMoreInfo.getCloseDate()) &&
                getPeriodMonths().equals(myDepositMoreInfo.getPeriodMonths()) &&
                getInterestRate().equals(myDepositMoreInfo.getInterestRate());
    }
}
