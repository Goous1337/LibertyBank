package pojo.creditService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplyingLoanRequest {
    private Integer productId;
    private Integer amount;
    private Integer periodMonths;
    private Integer monthlyIncome;
    private Integer monthlyExpenditure;
    private String employerIdentificationNumber;
    private String currencyCode;
}
