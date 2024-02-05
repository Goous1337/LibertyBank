package pojo.accountService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountData {
    private String currency;
    private String accountType;
    private Boolean isMain;
}
