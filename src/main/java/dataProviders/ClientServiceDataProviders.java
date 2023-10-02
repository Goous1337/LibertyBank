package dataProviders;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.stream.Stream;

public class ClientServiceDataProviders {

    public static Stream<String> generateRandomInvalidPhoneNumbers() {
        return Stream.of(
                RandomStringUtils.randomAlphanumeric(11),
                RandomStringUtils.randomNumeric(10),
                RandomStringUtils.randomNumeric(12),
                ""
        );
    }

    public static Object[] provideNonExistentClientPhoneNumbers() {
        int numberOfPhoneNumbers = 3;
        Object[] phoneNumbers = new Object[numberOfPhoneNumbers];
        for (int i = 0; i < numberOfPhoneNumbers; i++) {
            phoneNumbers[i] = DataUtils.getNonExistentClientPhoneNumber();
        }
        return phoneNumbers;
    }

}
