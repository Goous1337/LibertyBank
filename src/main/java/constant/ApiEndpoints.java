package constant;

/**
 * Константы API ендпоинтов
 */
public final class ApiEndpoints {
    public static final String USER_STATUS = "user/status";
    public static final String REGISTRATION = "registration";
    public static final String VERIFICATION_CODE = "security/session";
    public static final String VERIFICATION = "security/session/verification";
    public static final String CHANGE_PASSWORD = "security/password";
    public static final String INVALID_CHANGE_PASSWORD = "security/pass";
    public static final String SMS_NOTIFICATION = "user/settings/notifications/sms";
    public static final String EMAIL_NOTIFICATION = "user/settings/notifications/email";
    public static final String PUSH_NOTIFICATION = "user/settings/notifications/push";
    public static final String INVALID_NOTIFICATION = "user/settings/notif/push";
    public static final String INVALID_SMS_NOTIFICATION = "user/settings/notif/sms";
    public static final String INVALID_EMAIL_NOTIFICATION = "user/settings/notif/email";
    public static final String CHANGE_EMAIL = "auth/user/settings/email";
    public static final String QUESTION_ANSWER = "registration/user-profile/{customerId}/question";
    public static final String INVALID_QUESTION_ANSWER = "registration/user/{customerId}/question";
    public static final String RETRIEVING_USER_DATA = "auth/information";
    public static final String CITY_LIST = "city_dict/cities_list";
    public static final String INVALID_CITY_LIST = "city_dict/citiees_list";
    public static final String BANK_DIVISIONS_LIST = "bank_branch";
    public static final String BANK_EXCHANGE_RATES = "exchange_rates";
    public static final String INVALID_BANK_EXCHANGE_RATES = "exchange_ratesss";
    public static final String NOTIFICATION_SETTINGS = "/auth/user/settings/notifications";
    public static final String DEPOSIT_PRODUCTS = "deposits/api/v1/deposit-product";
    public static final String DEPOSIT_PRODUCTS_USER = "deposits/api/v1/deposit";
    public static final String INVALID_DEPOSIT_PRODUCTS = "deposits/api/v0/deposit-product";
    public static final String CREDIT_BACKGROUND = "credits/api/v1/credit-product/form";
    public static final String DEPOSIT_PRODUCTS_OFFER = "deposits/api/v1/deposit-product/";

    public static final String CREDIT_PRODUCTS = "credits/api/v1/credit-product";
    public static final String CREDIT_PRODUCTS_INFO = "credits/api/v1/credit-product/3";
    public static final String INVALID_CREDIT_PRODUCTS = "credits/api/v1/credit-product/.";
    public static final String INVALID_CREDIT_PRODUCT = "credits/api/v1/credit-product/7";
    public static final String DEPOSIT_SETTINGS = "deposits/api/v1/deposit/new";
    public static final String DEPOSIT_CALCULATOR = "deposits/api/v1/deposit-product/profit-calculating";
    public static final String DEPOSIT_SEND_EMAIL = "deposits/api/v1/deposit/check";
    public static final String CREDIT_BODY = "credits/api/v1/credit-order/new";
    public static final String CREDIT_ORDER_STATUS = "credits/api/v1/credit-order/status";
    public static final String INVALID_CREDIT_ORDER_TABLE = "credits/api/v1/credit-order/bank";
    public static final String CREDIT_WITHDRAWAL = "credits/api/v1/credit-order/";
    public static final String NOT_EXIST_CREDIT_WITHDRAWAL = "credits/api/v1/credit-order/10000";
    public static final String CREDIT_INFO = "credits/api/v1/credit";
    public static final String CREDIT_INFORMATION = "credits/api/v1/credit/current";
    public static final String CREDIT_SUM_CALCULATE = "credits/api/v1/credit-product/credit-calculate/sum-calculate";
    public static final String BANK_LIST = "api/v1/info-service/offices";
    public static final String BANK_LIST_MISTAKE = "api/v156/info-service/offices";

    public static final String ABS_INFO_SERVICE = "api/v1/info-service/atm";
    public static final String INVALID_ABS_INFO_SERVICE = "api/v1/info-service/atm/";
    public static final String ABS_INFO_SERVICE_NEW_BANK = "api/v1/info-service/offices";
    public static final String ABS_INFO_SERVICE_UPDATE = "api/v1/info-service/offices/{uuid}";
    public static final String ABS_INFO_SERVICE_INVALID = "api/v1/info-service/officess/{uuid}";
    public static final String INVALID_ABS_INFO_SERVICE_NEW_BANK = "api/v1/info-service/offices/";
    public static final String ABS_NEWS = "api/v1/abs/news";
    public static final String ABS_NEWS_BY_UUID = "api/v1/abs/news/{newsUuid}";
    public static final String ABS_CLIENT_SHORT_INFO = "api/v1/client-service/clients/short-info";
    public static final String INVALID_ABS_NEWS = "api/v1/abs/new/{newsUuid}";
    public static final String CUSTOMER_SECURITY = "customer/api/v1/security/session";
    public static final String CUSTOMER_LOGIN = "customer/api/v1/login";
    public static final String INVALID_CUSTOMER_LOGIN = "customer/api/v1/loginn";
    public static final String CUSTOMER_CHANGE_PASSWORD = "customer/api/v1/security/password/changepassword";
    public static final String CUSTOMER_VERIFICATION_BY_CODE = "customer/api/v1/security/session/verification";
    public static final String ABS_CLIENT_SERVICE_PERSONAL_DATE = "api/v1/client-service/clients/personal-date";

    public static final String CLIENT_ACCOUNTS_LIST = "api/v1/info-service/atm";
    public static final String INVALID_CLIENT_ACCOUNTS_LIST = "api/v1/info-service/atm/";

    public static final String RETRIEVING_USER_INFO = "customer/api/v1/auth/information";
    public static final String PUSH_NOTIFICATION_2_0 = "customer/api/v1/auth/user/settings/notifications/push";
    public static final String INVALID_PUSH_NOTIFICATION_2_0 = "customer/api/v1/^auth/user/settings/notifications/push";
    public static final String CUSTOMER_2_0_NOTIFICATION = "customer/api/v1/auth/user/settings/notifications";
    public static final String INCORRECT_CUSTOMER_2_0_NOTIFICATION = "customer/ai/v1/auth/user/settings/notifications";
    public static final String CUSTOMER_2_0_RECOVERY = "customer/api/v1/security/password/recovery";
    public static final String CUSTOMER_2_0_SECURITY_VERIFICATION = "customer/api/v1/security/session/verification";
    public static final String CUSTOMER_2_0_EMAIL = "customer/api/v1/auth/user/settings/email";
    public static final String CUSTOMER_2_0_NOTIFICATION_SMS = "customer/api/v1/auth/user/settings/notifications/sms";
    public static final String CUSTOMER_2_0_NOTIFICATION_EMAIL = "customer/api/v1/auth/user/settings/notifications/email";
    public static final String INVALID_CUSTOMER_2_0_NOTIFICATION_EMAIL = "customer/api/v1/***auth/user/settings/notifications/email";
    public static final String INCORRECT_CUSTOMER_2_0_NOTIFICATION_SMS =
            "customer/api/v1/auth/user/settings/notifications/sm";

    public static final String QUESTION_ANSWER_2_0 = "customer/api/v1/auth/user/settings/controls";
    public static final String QUESTION_ANSWER_INVALID_URL_2_0 = "customer/api/v1/auth/user/settings/control";

    public static final String ACCOUNTS_LIST = "account-service/api/v1/accounts";
    public static final String CHANGE_PASSWORD_2_0 = "customer/api/v1/security/password";
    public static final String INVALID_CHANGE_PASSWORD_2_0 = "customer/api/v1/security/pas";
    public static final String ACCOUNTS_LIST_BY_CUSTOMER_ID = "account-service/api/v1/accounts/customer/";
    public static final String CARDS_LIST = "card-service/api/v1/card-products";
    public static final String ACTIVE_CARDS = "card-service/api/v1/cards";
    public static final String CARD_BY_NAME = "card-service/api/v1/card-products/typeName";
    public static final String REGISTRATION_HEADER = "Registration";
    public static final String APPLICATION_INSURANCE = "insurance-service/api/v1/insurance/applications";
}