package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositDataInvalidRequest {
    private String errorMessage;

    public DepositDataInvalidRequest() {
    }
}
