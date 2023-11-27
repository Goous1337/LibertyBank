package pojo.creditService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplyingLoanResponse {
    private Integer id;
    private Integer productId;
    private String status;
    private Integer amount;
    private Integer periodMonths;
    private String creationDate;
}
