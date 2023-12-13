package pojo.absInfoService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AbsInfoServiceData {
    private String country;
    private String region;
    private String city;
    private String street;
    private String building_number;
    private String atm_coordinates;
    private String room_number;
    private Boolean payments;
    private Boolean biometrics;
    private String atm_number;
    private Boolean is_closed;
    private Boolean cash_withdrawal;
    private Boolean withdrawal_currencies;
    private Boolean cash_deposit;
    private Boolean cash_deposit_currencies;
    private Boolean money_transfer;
    private Boolean nfc;
    private Integer banknotes_per_pack;
    private Integer max_amount;
    private Boolean encashment_service;
}
