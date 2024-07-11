package api.model.webAndApi.deposit;

import lombok.Getter;
import lombok.Setter;
import web.constans.deposit.DepositsConstants;
import web.helpers.Converter;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class MyDepositProduct {
    private Integer id;
    private String name;
    private String currencyCode;
    private Double currentBalance;
    private java.sql.Date closeDate;
    private Double interestRate;
    private String depAccountNumber;
    private Converter converter;

    public MyDepositProduct() {

    }

    public MyDepositProduct(String name, Double interestRate, Double currentBalance, Date closeDate) {
        this.name = name;
        this.interestRate = interestRate;
        this.currentBalance = currentBalance;
        this.closeDate = closeDate;
    }


    public java.sql.Date getCloseDate() {
        return closeDate;
    }

    public String getStringDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DepositsConstants.DATE_FORMAT);
        return dateFormat.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MyDepositProduct)) {
            return false;
        }

        MyDepositProduct myDepositProduct = (MyDepositProduct) o;
        return getName().equals(myDepositProduct.getName()) &&
                getInterestRate().equals(myDepositProduct.getInterestRate()) &&
                getCurrentBalance().equals(myDepositProduct.getCurrentBalance()) &&
                getCloseDate().equals(myDepositProduct.getCloseDate());
    }
}
