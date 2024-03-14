package pojo.cardService;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCardLimitsRequest {
    private Integer operationPerDay;
    private Integer operationPerMonth;
    private Integer amountPerOperation;
    private Integer amountPerDay;
    private Integer amountPerMonth;
}
