package pojo.cardService.cardTariffs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferFee {
    private Integer ourClient;
    private Integer partnerClient;
    private Integer anotherClientRu;
    private Integer anotherClientWorld;
    private Integer onBankAccount;
    private Integer byPhoneNumber;
    private Integer minFeeWorld;
}
