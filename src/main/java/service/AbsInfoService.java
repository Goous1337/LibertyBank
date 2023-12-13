package service;

import io.restassured.response.Response;
import pojo.absInfoService.AbsInfoServiceData;

import static api.core.ApiClient.sendSimpleRequest;
import static constant.ApiEndpoints.ABS_INFO_SERVICE;
import static constant.ApiEndpoints.INVALID_ABS_INFO_SERVICE;
import static io.restassured.http.Method.POST;


public class AbsInfoService {

    public Response checkAddNewAtm(String country, String region, String city, String street, String building_number, String atm_coordinates,
                                   String room_number, Boolean payments, Boolean biometrics, String atm_number, Boolean is_closed,
                                   Boolean cash_withdrawal, Boolean withdrawal_currencies, Boolean cash_deposit, Boolean cash_deposit_currencies,
                                   Boolean money_transfer, Boolean nfc, Integer banknotes_per_pack, Integer max_amount, Boolean encashment_service) {

        return sendSimpleRequest
                (POST, ABS_INFO_SERVICE, new AbsInfoServiceData(country, region, city, building_number, atm_coordinates, street,
                        room_number, payments, biometrics, atm_number, is_closed, cash_withdrawal, withdrawal_currencies,
                        cash_deposit, cash_deposit_currencies, money_transfer, nfc, banknotes_per_pack, max_amount,
                        encashment_service));
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
}
