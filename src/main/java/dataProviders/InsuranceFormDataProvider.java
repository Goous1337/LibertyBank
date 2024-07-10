package dataProviders;

import java.util.stream.Stream;

import static web.constans.InsuranceServiceConstants.INSURANCE_DURATION_MAXIMUM;
import static web.constans.InsuranceServiceConstants.INSURANCE_DURATION_MINIMUM;
import static web.constans.InsuranceServiceConstants.INSURANCE_DURATION_TWELFTH;
import static web.enums.InsuranceEnum.Currencies;

public class InsuranceFormDataProvider {
    public static Stream<Object[]> formValidationFirstStepValidData() {
        return Stream.of(
                new Object[]{Currencies.EUR, INSURANCE_DURATION_MINIMUM},
                new Object[]{Currencies.USD, INSURANCE_DURATION_TWELFTH},
                new Object[]{Currencies.RUB, INSURANCE_DURATION_MAXIMUM}
        );
    }
}
