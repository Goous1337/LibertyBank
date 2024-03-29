package pojo.cardService.cardTariffs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cashback {
    private Integer interestPerMonth;
    private Integer interestForAll;
    private Integer interestForPartners;
    private Integer cashbackLimit;
}
