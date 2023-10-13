package dataProviders;

import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class ClientServiceDataProviders {

    public static Stream<String> generateRandomInvalidPhoneNumbers() {
        return Stream.of(
                randomAlphanumeric(11),
                randomNumeric(10),
                randomNumeric(12),
                ""
        );
    }

    public static Stream<String> provideNonExistentClientPhoneNumbers() {
        return Stream.generate(DataUtils::getNonExistentClientPhoneNumber).limit(3);
    }

}
