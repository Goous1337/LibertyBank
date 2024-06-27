package pojo.investmentService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionnaireData {
    private Boolean residence;
    private Boolean abroadTax;
    private Boolean beneficialOwner;
    private Boolean representative;
    private Boolean beneficiary;
}
