package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import pojo.absInfoService.AbsInfoServiceDataBankBranch;

import static constant.LibertyServiceName.ABS_INFO_SERVICE;
import static dataBase.DataBaseConnector.getDBConnection;

public class AbsInfoServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static void deleteNewAtm(String atm_number) {
        String sql = "DELETE FROM atm WHERE atm_number=?";
        getDBConnection(ABS_INFO_SERVICE).update(sql, atm_number);
        LOG.info(String.format("Удален банкомат номером: %s", atm_number));
    }

    public static void deleteNewBankBranch(String bankBranchNumber) {
        String sql = "DELETE FROM bank_office WHERE office_number=?";
        getDBConnection(ABS_INFO_SERVICE).update(sql, bankBranchNumber);
        LOG.info(String.format("Удален филиал банка номером: %s", bankBranchNumber));
    }

    public static String getNewBankBranchId(String numberBank) {
        String sql = "SELECT uuid FROM bank_office WHERE office_number=?";
        String uuid = (getDBConnection(ABS_INFO_SERVICE).queryForObject(sql, String.class, numberBank));
        LOG.info(String.format("Получен uuid банка: %s: %s", numberBank, uuid));
        return uuid;
    }

    public static String getNewBankBranchUuid(String uuid) {
        String sql = "SELECT uuid FROM bank_office WHERE uuid=?::uuid";
        String actualUuid = (getDBConnection(ABS_INFO_SERVICE).queryForObject(sql, String.class, uuid));
        LOG.info(String.format("Получен uuid банка: %s", uuid));
        return actualUuid;
    }

    public static String getBankBranchUuid(int id) {
        String sql = "SELECT uuid FROM bank_office WHERE id=?";
        String uuid = getDBConnection(ABS_INFO_SERVICE).queryForObject(sql, String.class, id);
        return uuid;
    }

    public static AbsInfoServiceDataBankBranch getBankBranchData(int id) {

        String sqlBankOfficesData = "SELECT bank_office.office_number, bank_office.ramp,\n" +
                "bank_office.phone_number,bank_office.is_closed,bank_office.opening_time,\n" +
                "bank_office.closing_time,bank_office.currency_exchange,bank_office.exotic_currency,\n" +
                "bank_office.money_transfer,bank_office.cash_withdrawal,bank_office.accept_payment,\n" +
                "bank_office.replenish_card,bank_office.replenish_account,bank_office.consultation,\n" +
                "bank_office.insurance,\n" +
                "office_address.country, office_address.region,office_address.city,\n" +
                "office_address.street,office_address.building_number,\n" +
                "office_address.office_coordinates,\n" +
                "office_address.post_code,branch_details.bik,branch_details.kpp,\n" +
                "branch_details.inn,branch_details.okpo,branch_details.ogrn,\n" +
                "branch_details.swift,\n" +
                "branch_details.payment_account,branch_details.correspondent_account,\n" +
                "branch_details.bank_name_full\n" +
                "FROM bank_office \n" +
                "INNER JOIN office_address \n" +
                "ON bank_office.id = office_address.id \n" +
                "INNER JOIN branch_details\n" +
                "ON branch_details.id = bank_office.id\n" +
                "AND address_id = ?";
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch = (AbsInfoServiceDataBankBranch) getDBConnection(ABS_INFO_SERVICE).queryForObject
                (sqlBankOfficesData, new Object[]{id}, new BeanPropertyRowMapper(AbsInfoServiceDataBankBranch.class));
        absInfoServiceDataBankBranch.setClosing_time(absInfoServiceDataBankBranch.getClosing_time().substring(0, 5));
        absInfoServiceDataBankBranch.setOpening_time(absInfoServiceDataBankBranch.getOpening_time().substring(0, 5));
        return absInfoServiceDataBankBranch;
    }
}
