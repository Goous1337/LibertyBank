package service;

import io.restassured.response.Response;
import pojo.absInfoService.AbsInfoServiceData;
import pojo.absInfoService.AbsInfoServiceDataBankBranch;

import static api.core.ApiClient.sendSimpleRequest;
import static constant.ApiEndpoints.*;
import static io.restassured.http.Method.POST;


public class AbsInfoService {

    public Response checkAddNewAtm(AbsInfoServiceData absInfoServiceData) {
        return sendSimpleRequest
                (POST, ABS_INFO_SERVICE, absInfoServiceData);
    }

    public Response checkInvalidURLAddNewAtm(String country, String region, String city, String street, String building_number, String atm_coordinates,
                                   String room_number, Boolean payments, Boolean biometrics, String atm_number, Boolean is_closed,
                                   Boolean cash_withdrawal, Boolean withdrawal_currencies, Boolean cash_deposit, Boolean cash_deposit_currencies,
                                   Boolean money_transfer, Boolean nfc, Integer banknotes_per_pack, Integer max_amount, Boolean encashment_service) {

        return sendSimpleRequest
                (POST, INVALID_ABS_INFO_SERVICE, new AbsInfoServiceData(country, region, city, building_number, atm_coordinates, street,
                        room_number, payments, biometrics, atm_number, is_closed, cash_withdrawal, withdrawal_currencies,
                        cash_deposit, cash_deposit_currencies, money_transfer, nfc, banknotes_per_pack, max_amount,
                        encashment_service));
    }

    public Response checkAddNewBankBranch(AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch) {
        return sendSimpleRequest
                (POST, ABS_INFO_SERVICE_NEW_BANK, absInfoServiceDataBankBranch);
    }

    public Response checkInvalidURLAddNewBankBranch(AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch) {
        return sendSimpleRequest
                (POST, INVALID_ABS_INFO_SERVICE_NEW_BANK, absInfoServiceDataBankBranch);
    }

}
