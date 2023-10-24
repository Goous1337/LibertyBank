package pojo.customerService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserQuestion {

    private String securityQuestion;
    private String securityAnswer;
}
