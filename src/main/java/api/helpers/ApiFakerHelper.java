package api.helpers;

import com.github.javafaker.Faker;

import java.util.stream.Stream;

public class ApiFakerHelper {
    private static final Faker FAKER = new Faker();

    public static Stream<String> generateTimestamps() {
        return Stream.generate(() -> {
            switch (FAKER.random().nextInt(3)) {
                case 0:
                    return FAKER.regexify("[A-Za-z]{4}");
                case 1:
                    return FAKER.regexify("[А-Яа-я]{4}");
                case 2:
                    return FAKER.regexify("[!@#%^&*()_+-=]{4}");
                default:
                    return FAKER.regexify("[0-9]{10}");
            }
        }).limit(3);
    }

    public static Stream<String> generateLimits() {
        return Stream.generate(() -> {
            switch (FAKER.random().nextInt(4)) {
                case 0:
                    return FAKER.regexify("[!@#%^&*()_+-=]{4}");
                case 1:
                    return FAKER.regexify("[А-Яа-я]{4}");
                case 2:
                    return FAKER.regexify("[A-Za-z]{4}");
                case 3:
                    return FAKER.regexify("[0-9]{11}");
                default:
                    return FAKER.regexify("[0-9]{12}");
            }
        }).limit(3);
    }

    public static Stream<String> generateTags() {
        return Stream.generate(() -> {
            switch (FAKER.random().nextInt(4)) {
                case 0:
                    return FAKER.regexify("[!@#%^&*()_+-=]{4}");
                case 1:
                    return FAKER.regexify("[А-Яа-я]{4}");
                case 2:
                    return FAKER.regexify("[A-Za-z]{4}");
                case 3:
                    return FAKER.regexify("[0-9]{5}");
                default:
                    return FAKER.regexify("[0-9]{4}");
            }
        }).limit(3);
    }
}
