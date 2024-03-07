package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class CreditProduct{

    private Integer id;
    private String name;
    private Double interestRate;
    private String currencyCode;
    private String details;

    public String convertInterestRateToString(Double number) {
        String str = String.valueOf(number).replace(".", ",") + "%";
        return str;
    }
}
