package dataProviders.gui;

import java.util.stream.Stream;

public class AuthorizationDataProviders {

    public static Stream<Object[]> provideTestDataForPhoneNumber() {
        return Stream.of(
                new Object[]{"", 0, "rgba(245, 60, 20, 1)", ""},
                new Object[]{"7912123452", 16, "rgba(245, 60, 20, 1)",
                        "Номер телефона должен содержать 11 цифр"},
                new Object[]{"791212345333", 17, "rgba(77, 95, 113, 1)", ""},
                new Object[]{"aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ", 0,
                        "rgba(245, 60, 20, 1)", "Номер телефона должен содержать 11 цифр"},
                new Object[]{"!'(%)*$+,#-./:\";<=>?@[]^_`{|}~[]", 0, "rgba(245, 60, 20, 1)",
                        "Номер телефона должен содержать 11 цифр"},
                new Object[]{" 7 9 1 2 1 2 3 4 5 6 7 ", 17, "rgba(77, 95, 113, 1)", ""}
        );
    }

    public static Stream<Object[]> provideTestDataForPasswordAmountSymbols() {
        return Stream.of(
                new Object[]{"", 0, "rgba(245, 60, 20, 1)",
                        "Пароль должен содержать от 6 до 20 символов"},
                new Object[]{"1Aa2@", 5, "rgba(245, 60, 20, 1)",
                        "Пароль должен содержать от 6 до 20 символов"},
                new Object[]{"1234567890AaAaAaAa@#", 20, "rgba(77, 95, 113, 1)", ""}
        );
    }

    public static Stream<Object[]> provideTestDataForPasswordInvalidSymbols() {
        return Stream.of(
                new Object[]{" 789DFs%", 8, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"4321HgT&* ", 10, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"14 75E ea!$", 11, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"1928AZ!@", 8, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"1987pea&", 8, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"!@#$%AZds&*(", 12, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"2024QWEasd123", 13, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"Привет2020№;%*", 14, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"0192837", 7, "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"}
        );
    }

}
