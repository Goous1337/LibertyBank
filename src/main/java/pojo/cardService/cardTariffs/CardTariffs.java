package pojo.cardService.cardTariffs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardTariffs {
    private String productType;
    private Integer servicePrice;
    private Integer costPerMonth;
    private Integer freeCostFrom;
    private Integer addCardCost;
    private Integer cardReissueCost;
    private String currency;
    private TransferFee transferFee;
    private WithdrawalCashFee withdrawalCashFee;
    private Cashback cashback;
}
