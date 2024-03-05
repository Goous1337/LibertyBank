package web.constans;

import property.WebPropertiesReader;

public class UrlConfig {

    public static final String BASE_URL = WebPropertiesReader.getWebBaseUrl();
    public static final String ACCOUNTS_URL = "/my-bills";
    public static final String CREDIT_URL = "/credits";
    public static final String CREDIT_PRODUCT_URL = "/credits/credit-products";
    public static final String CREATE_ACCOUNT_URL = "/create-current-account";
    public static final String LOGIN_URL = "/login";
    public static final String CHANGE_NOTIFICATION_URL = "/customer/notification";
}
