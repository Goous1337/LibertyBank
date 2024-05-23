package property;

import static property.PropertiesReader.getPropertyValue;

public final class BaseProperties {

    public static String BASE_URL = getPropertyValue("base_url");
    public static String URL_USER_ACCOUNT_SERVICE = getPropertyValue("user_account_service");
    public static String CUSTOMER_SERVICE = getPropertyValue("customer_service");
    public static String CUSTOMER_SERVICE_2_0 = getPropertyValue("customer_service_2_0");
    public static String INFO_SERVICE = getPropertyValue("info_service");
    public static String CREDIT_SERVICE = getPropertyValue("credit_service");
    public static String DEPOSIT_SERVICE = getPropertyValue("deposit_service");
    public static String DEPOSIT_SERVICE_NEW = getPropertyValue("deposit_service_new");
    public static String ACCESS_TOKEN_CUSTOMER_SERVICE = getPropertyValue("access_token_credit_service");
    public static String ACCESS_TOKEN_INSURANCE_SERVICE = getPropertyValue("access_token_insurance_service");
    public static String INVALID_TOKEN_CREDIT_SERVICE = "JSO1q6y8muhBjpLlviMOUOIc";
    public static String ABS_INFO_SERVICE = getPropertyValue("abs_info_service");
    public static String ABS_CLIENT_SERVICE = getPropertyValue("abs_client_service");
    public static String ACCOUNT_SERVICE = getPropertyValue("account_service");
    public static String CARD_SERVICE = getPropertyValue("card_service");
    public static String INSURANCE_SERVICE = getPropertyValue("insurance_service");
    public static String INSURANCE_SERVICE_GROUPS = getPropertyValue("insurance_service_groups_of_polices");
    public static String INSURANCE_POLICY_SERVICE = getPropertyValue("insurance_service_policy");
    public static String INSURANCE_SERVICE_LIST_OF_INSURANCE_POLICES = getPropertyValue("insurance_service_polices");
}