package web.constans;

public class InsuranceServiceConstants {
    public static final int START_OF_CENTURY = 2000;
    public static final int TWENTY_EIGHTEEN = 2018;
    public static final int NINETEEN_EIGHTY = 1980;
    public static final int TWENTY_TWENTY_FOUR = 2024;
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int FIRST_DAY_OF_MONTH = 0;
    public static final int SECOND_DAY_OF_MONTH = 1;
    public static final int THIRD_DAY_OF_MONTH = 2;
    public static final int FORTH_DAY_OF_MONTH = 3;
    public static final int TWENTY_EIGHTH_DAY_OF_MONTH = 27;
    public static final int TWENTY_NINTH_DAY_OF_MONTH = 28;
    public static final int THIRTIETH_DAY_OF_MONTH = 29;
    public static final int THIRTY_FIRST_DAY_OF_MONTH = 30;
    public static final String INSURANCE_DURATION_MINIMUM = "1";
    public static final String INSURANCE_DURATION_TWELFTH = "12";
    public static final String INSURANCE_DURATION_MAXIMUM = "60";
    public static final String INSURANCE_DURATION_INVALID = "6 years";
    public static final String VALID_NAME = "Николай";
    public static final String INVALID_NAME = "-Саш0к-";
    public static final Integer DOCUMENT_TYPE_PASSPORT = 0;
    public static final Integer DOCUMENT_TYPE_RESIDENCE = 1;
    public static final Integer DOCUMENT_TYPE_REFUGE = 2;
    public static final String DOCUMENT_NUMBER = "1015 205632";
    public static final String PHONE_NUMBER = "9002451452";
    public static final String EMAIL = "irinagal@mail.ru";
    public static final String DOCUMENT_DEPARTMENT = "ОУФМС в Октябрьском рне г. Москва";
    public static final String THING_NAME = "Шкаф";
    public static final String THING_COST = "25000";
    public static final String APARTMENT_COST = "3000000";
    public static final Integer THING_TYPE_FURNITURE = 0;
    public static final Integer CONSTRUCTION_TYPE_BRICK = 2;
    public static final Integer MOSCOW = 0;
    public static final String CITY = "Москва";
    public static final String STREET = "Красная";
    public static final String HOUSE = "7";
    public static final String FLOOR = "9";
    public static final String APARTMENT = "6";
    public static final String ENTRANCE = "1";
    public static final String STATE = "Москва";
    public static final String YEAR_OF_CONSTRUCTION = "2007";
    public static final String APARTMENT_AREA = "40";
    public static final String APARTMENT_INSURANCE_AMOUNT = "50000";

    public enum ApartmentApplicationField {
        INSURANCE_DURATION,
        LAST_NAME,
        FIRST_NAME,
        PATRONYMIC,
        PASSPORT_NUMBER,
        RESIDENCE_NUMBER,
        REFUGE_NUMBER,
        ISSUED_BY,
        PHONE,
        EMAIL,
        REGION,
        CITY,
        STREET,
        BUILDING,
        APARTMENT,
        ENTRANCE,
        CONSTRUCTION_YEAR,
        BUILDING_SPACE,
        ACTUAL_COST,
        INSURANCE_AMOUNT,
        BUILDING_REGION,
        BUILDING_CITY,
        BUILDING_STREET,
        BUILDING_HOUSE
    }

}
