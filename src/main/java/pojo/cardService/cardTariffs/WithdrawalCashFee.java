package pojo.cardService.cardTariffs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawalCashFee {
    private Integer ourBank;
    private Integer partnersBank;
    private Integer anotherBankRu;
    private Integer anotherBankWorld;
    private Integer minCashFeeWorld;
}
