package web.constans;

import property.WebPropertiesReader;

public class UrlConfig {

    public static final String BASE_URL = WebPropertiesReader.getWebBaseUrl();
    public static final String ACCOUNTS_URL = "/my-bills";
    public static final String CREATE_ACCOUNT_URL = "/create-current-account";
    public static final String LOGIN_URL = "/login";
    public static final String CHANGE_NOTIFICATION_URL = "/customer/notification";
    public static final String CREDITS = "/credits/my-credits";
    public static final String CREDIT_PRODUCTS_URL = "/credits/credit-products";
}
