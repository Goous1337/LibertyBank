package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;
import web.constans.deposit.DepositsConstants;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class CreditApplications {
    private String id;
    private String name;
    private Double amount;
    private Integer periodMonths;
    private Double interestRate;
    private String currencyCode;
    private String status;
    private String creationDate;

    public CreditApplications() {
    }

    public CreditApplications(String name, Double amount, Integer periodMonths, Double interestRate, String status, String creationDate) {
        this.name = name;
        this.amount = amount;
        this.periodMonths = periodMonths;
        this.interestRate = interestRate;
        this.status = status;
        this.creationDate = creationDate;
    }

    public String getStringDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DepositsConstants.DATE_FORMAT);
        return dateFormat.format(date);
    }

   // public java.sql.Date getCreationDate() {
  //      return creationDate;
   // }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyCreditMoreInformation)) {
            return false;
        }
        CreditApplications creditApplications = (CreditApplications) obj;
        return getName().equals(creditApplications.getName()) &&
                getPeriodMonths().equals(creditApplications.getPeriodMonths()) &&
                getInterestRate().equals(creditApplications.getInterestRate()) &&
                getAmount().equals(creditApplications.getAmount()) &&
             //   getCurrencyCode().equals(creditApplications.getCurrencyCode()) &&
                getStatus().equals(creditApplications.getStatus()) &&
                getCreationDate().equals(creditApplications.getCreationDate());
    }
}
