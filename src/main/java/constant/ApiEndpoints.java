package constant;

/**
 * Константы API ендпоинтов
 */
public final class ApiEndpoints {
    public static final String USER_STATUS = "user/status";
    public static final String REGISTRATION = "registration";
    public static final String VERIFICATION_CODE = "security/session";
    public static final String VERIFICATION = "security/session/verification";
    public static final String CHANGE_PASSWORD = "/security/password";
    public static final String INVALID_CHANGE_PASSWORD = "/security/pass";
    public static final String SMS_NOTIFICATION = "user/settings/notifications/sms";
    public static final String EMAIL_NOTIFICATION = "user/settings/notifications/email";
    public static final String PUSH_NOTIFICATION = "user/settings/notifications/push";
    public static final String INVALID_NOTIFICATION = "/user/settings/notif/push";
    public static final String INVALID_SMS_NOTIFICATION = "/user/settings/notif/sms";
    public static final String INVALID_EMAIL_NOTIFICATION = "/user/settings/notif/email";
    public static final String CHANGE_EMAIL = "auth/user/settings/email";
    public static final String QUESTION_ANSWER = "/registration/user-profile/{customerId}/question";
    public static final String INVALID_QUESTION_ANSWER = "/registration/user/{customerId}/question";

    public static final String RETRIEVING_USER_DATA = "/auth/information";
    public static final String CITY_LIST = "city_dict/cities_list";
    public static final String INVALID_CITY_LIST = "city_dict/citiees_list";
    public static final String BANK_DIVISIONS_LIST = "bank_branch";
    public static final String BANK_EXCHANGE_RATES = "exchange_rates";
    public static final String INVALID_BANK_EXCHANGE_RATES = "exchange_ratesss";
    public static final String NOTIFICATION_SETTINGS = "/auth/user/settings/notifications";

    public static final String DEPOSIT_PRODUCTS = "/deposits/api/v1/deposit-product";
    public static final String DEPOSIT_PRODUCTS_USER = "/deposits/api/v1/deposit";
    public static final String INVALID_DEPOSIT_PRODUCTS = "/deposits/api/v0/deposit-product";

    public static final String CREDIT_PRODUCTS = "/credits/api/v1/credit-product";
    public static final String INVALID_CREDIT_PRODUCTS = "/credits/api/v1/credit-product/.";
}