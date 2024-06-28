package api.model.webAndApi.credit;

import lombok.Getter;
import web.constans.credit.creditEnums.CreditNameEnum;

@Getter
public class CreditReport {
    private CreditNameEnum name;
    private String creationDate;
    private String statusCredit;
    private String interestRate;
    private String amount;
    private String currencyCode;
    private String periodMonths;
    private String MethodOfObtainingCredit;
    private String typeCredit;
    private String paymentSchemeCreditReport;
    private String loanRateTypeCreditReport;

}
