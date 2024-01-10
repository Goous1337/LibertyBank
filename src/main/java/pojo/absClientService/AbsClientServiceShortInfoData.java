package pojo.absClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsClientServiceShortInfoData {
    private String patronymic;
    private String first_name;
    private String last_name;
    private String customer_uuid;
}
