package constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LibertyServiceName {
    USER_SERVICE("user_service_db"),
    USER_ACCOUNT_SERVICE("user_account_service_db"),
    CUSTOMER_SERVICE("customer_service_db");

    private final String serviceName;
}
