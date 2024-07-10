package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;
import web.constans.deposit.DepositsConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private Date creationDate;


    private List<CreditApplications> applications;

    public CreditApplications() {
    }

    public CreditApplications(String name, Double amount, Integer periodMonths, Double interestRate, String status, Date creationDate) {
        this.name = name;
        this.amount = amount;
        this.periodMonths = periodMonths;
        this.interestRate = interestRate;
       // this.currencyCode = currencyCode;
        this.status = status;
        this.creationDate = creationDate;
    }

    public String getStringDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DepositsConstants.DATE_FORMAT_APP);
        return dateFormat.format(date);
    }

    public java.sql.Date getCreationDateApp() {
        return (java.sql.Date) creationDate;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CreditApplications)) {
            return false;
        }
        CreditApplications creditApplications = (CreditApplications) obj;
        return getName().equals(creditApplications.getName()) &&
                getPeriodMonths().equals(creditApplications.getPeriodMonths()) &&
                getInterestRate().equals(creditApplications.getInterestRate()) &&
                getAmount().equals(creditApplications.getAmount()) &&
             //   getCurrencyCode().equals(creditApplications.getCurrencyCode()) &&
                getStatus().equals(creditApplications.getStatus())&&
                getCreationDate().equals(creditApplications.getCreationDate());
    }
}
