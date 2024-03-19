package pojo.creditService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditInfo {
    private Integer id;
    private Integer min_sum;
    private Integer max_sum;
    private Integer min_period_months;
    private Integer max_period_months;
}
