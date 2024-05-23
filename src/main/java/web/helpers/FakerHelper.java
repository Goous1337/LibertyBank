package web.helpers;

import com.github.javafaker.Faker;

public class FakerHelper {
    private static final Faker FAKER = new Faker();

    public static String generateFourDigitNumber() {
        return FAKER.number().digits(4);
    }

    public static String generateInvalidPin() {
        return FAKER.regexify("[0-9]{3}[a-zA-Z]{1}");
    }

    public static String generateRandomCharacter() {
        return FAKER.bothify("@#%^?");
    }

    public static String generateRandomWord() {
        return FAKER.lorem().word();
    }
}
