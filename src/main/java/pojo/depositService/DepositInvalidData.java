package pojo.depositService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositInvalidData {
    private String type;
    private String title;
    private Integer status;
    private String detail;
    private String instance;

    public DepositInvalidData() {
    }
}
