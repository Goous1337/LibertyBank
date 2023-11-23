package property;

import static property.PropertiesReader.getPropertyValue;

public final class BaseProperties {

    public static String BASE_URL = getPropertyValue("base_url");
    public static String URL_USER_ACCOUNT_SERVICE = getPropertyValue("user_account_service");
    public static String CUSTOMER_SERVICE = getPropertyValue("customer_service");
    public static String INFO_SERVICE = getPropertyValue("info_service");

    public static String CREDIT_SERVICE = getPropertyValue("credit_service");
    public static String DEPOSIT_SERVICE = getPropertyValue("deposit_service");

}