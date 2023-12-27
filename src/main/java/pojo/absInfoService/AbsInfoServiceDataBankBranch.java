package pojo.absInfoService;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class AbsInfoServiceDataBankBranch {

    private String office_number;
    private String country;
    private String region;
    private String city;
    private String street;
    private String building_number;
    private String post_code;
    private String office_coordinates;
    private Boolean ramp;
    private String phone_number;
    private Boolean is_closed;
    private String opening_time;
    private String closing_time;
    private Boolean currency_exchange;
    private Boolean exotic_currency;
    private Boolean money_transfer;
    private Boolean cash_withdrawal;
    private Boolean accept_payment;
    private Boolean replenish_card;
    private Boolean replenish_account;
    private Boolean consultation;
    private Boolean insurance;
    private String bik;
    private String kpp;
    private String inn;
    private String payment_account;
    private String correspondent_account;
    private String bank_name_full;
    private String okpo;
    private String ogrn;
    private String swift;
}
