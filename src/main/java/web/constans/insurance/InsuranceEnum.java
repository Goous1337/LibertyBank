package web.constans.insurance;

public class InsuranceEnum {
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

    public enum ContentsApplicationField {
        INSURANCE_DURATION,
        THING_NAME,
        THING_COST,
        STREET,
        BUILDING,
        APARTMENT,
        FLOOR,
        ENTRANCE
    }

    public enum Currencies {
        EUR,
        RUB,
        USD
    }

    public enum InsuranceType {
        DMS,
        CARS,
        TRAVEL,
        PROPERTY,
        ACCIDENT,
    }

    public enum CarouselController {
        NEXT,
        PREV
    }

    public enum CarouselProducts {
        KASKO,
        DMSSTANDARD,
        APARTMENT,
        ACCIDENT,
        TRAVEL
    }

    public enum InsuranceProducts {
        STANDARD,
        STANDARDPLUS,
        PREMIUM,
        VIP,
        OSAGO,
        KASKO,
        APARTMENT,
        HOUSE,
        CONTENTS,
        ACCIDENT,
        TRAVEL
    }
}
