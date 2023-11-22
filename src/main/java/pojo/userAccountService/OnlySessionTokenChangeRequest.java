package pojo.userAccountService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OnlySessionTokenChangeRequest {
    private String sessionToken;
}
