package constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LibertyServiceName {
    USER_SERVICE("user_service_db"),
    USER_ACCOUNT_SERVICE("user_account_service_db"),
    CUSTOMER_SERVICE("customer_service_db"),
    INFO_SERVICE("info_service_db"),
    ABS_INFO_SERVICE("abs-info_service_db"),
    DEPOSIT_SERVICE_DB("deposit_service_db"),
    СREDIT_SERVICE("credit_service_db"),
    ABS_CLIENT_SERVICE("abs_client_service_db");
    private final String serviceName;
}
