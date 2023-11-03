package constant;

/**
 * Константы API ендпоинтов
 */
public final class ApiEndpoints {
    public static final String USER_STATUS = "user/status";
    public static final String REGISTRATION = "registration";
    public static final String VERIFICATION_CODE = "security/session";
    public static final String VERIFICATION = "security/session/verification";
    public static final String SMS_NOTIFICATION = "user/settings/notifications/sms";
    public static final String EMAIL_NOTIFICATION = "user/settings/notifications/email";
    public static final String INVALID_SMS_NOTIFICATION = "/user/settings/notif/sms";
    public static final String INVALID_EMAIL_NOTIFICATION = "/user/settings/notif/email";
    public static final String CHANGE_EMAIL = "auth/user/settings/email";
    public static final String QUESTION_ANSWER = "/registration/user-profile/{customerId}/question";
    public static final String INVALID_QUESTION_ANSWER = "/registration/user/{customerId}/question";

    public static final String CITY_LIST = "city_dict/cities_list";
    public static final String INVALID_CITY_LIST = "city_dict/citiees_list";
    public static final String BANK_DIVISIONS_LIST = "bank_branch";
    public static final String BANK_EXCHANGE_RATES = "exchange_rates";
    public static final String INVALID_BANK_EXCHANGE_RATES = "exchange_ratesss";
};