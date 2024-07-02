package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;
import web.constans.deposit.DepositsConstants;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class MyCreditMoreInformation {
    private String name;
    private Double creditAmount;
    private String currencyCode;
    private Integer periodMonths;
    private Double interestRate;
    private Double generalDebt;
    private Double currPeriodTransaction;
    private Integer percentCreditPayment;
    private Date paymentDate;
    private Integer outstandingPrincipal;
    private Boolean isActive;
    private String number;
    private String creditAccountNumber;

    public MyCreditMoreInformation() {
    }

    public MyCreditMoreInformation(String name, Double creditAmount, Integer periodMonths, Double interestRate, Double generalDebt, Date paymentDate, String creditAccountNumber) {
        this.name = name;
        this.creditAmount = creditAmount;
        this.periodMonths = periodMonths;
        this.interestRate = interestRate;
        this.generalDebt = generalDebt;
        this.paymentDate = paymentDate;
        this.creditAccountNumber = creditAccountNumber;
    }

    public String getStringDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DepositsConstants.DATE_FORMAT);
        return dateFormat.format(date);
    }

    public java.sql.Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyCreditMoreInformation)) {
            return false;
        }
        MyCreditMoreInformation myCreditMoreInformation = (MyCreditMoreInformation) obj;
        return getName().equals(myCreditMoreInformation.getName()) &&
                getCreditAmount().equals(myCreditMoreInformation.getCreditAmount()) &&
                getPeriodMonths().equals(myCreditMoreInformation.getPeriodMonths()) &&
                getInterestRate().equals(myCreditMoreInformation.getInterestRate()) &&
                getGeneralDebt().equals(myCreditMoreInformation.getGeneralDebt()) &&
                getPaymentDate().equals(myCreditMoreInformation.getPaymentDate()) &&
                getCreditAccountNumber().equals(myCreditMoreInformation.getCreditAccountNumber());
    }

}
