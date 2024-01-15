package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import pojo.absInfoService.AbsInfoServiceDataBankBranch;

import java.util.List;

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
        LOG.info(String.format("Получен uuid офиса банка: %s по id: %s", uuid, id));
        return uuid;
    }

    public static AbsInfoServiceDataBankBranch getBankBranchData(int id) {

        String sqlBankOfficesData = """
                SELECT bank_office.office_number, bank_office.ramp,
                bank_office.phone_number,bank_office.is_closed,bank_office.opening_time,
                bank_office.closing_time,bank_office.currency_exchange,bank_office.exotic_currency,
                bank_office.money_transfer,bank_office.cash_withdrawal,bank_office.accept_payment,
                bank_office.replenish_card,bank_office.replenish_account,bank_office.consultation,
                bank_office.insurance,
                office_address.country, office_address.region,office_address.city,
                office_address.street,office_address.building_number,
                office_address.office_coordinates,
                office_address.post_code,branch_details.bik,branch_details.kpp,
                branch_details.inn,branch_details.okpo,branch_details.ogrn,
                branch_details.swift,
                branch_details.payment_account,branch_details.correspondent_account,
                branch_details.bank_name_full
                FROM bank_office
                INNER JOIN office_address
                ON bank_office.id = office_address.id
                INNER JOIN branch_details
                ON branch_details.id = bank_office.id
                AND address_id = ?
                """;
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch =
                (AbsInfoServiceDataBankBranch) getDBConnection(ABS_INFO_SERVICE).queryForObject
                        (sqlBankOfficesData, new Object[]{id}, new BeanPropertyRowMapper(AbsInfoServiceDataBankBranch.class));
        absInfoServiceDataBankBranch.setClosing_time(absInfoServiceDataBankBranch.getClosing_time().substring(0, 5));
        absInfoServiceDataBankBranch.setOpening_time(absInfoServiceDataBankBranch.getOpening_time().substring(0, 5));
        return absInfoServiceDataBankBranch;
    }

    public static List<String> getBankUuid() {
        String sql = "SELECT uuid FROM bank_office";
        List<String> uuid = getDBConnection(ABS_INFO_SERVICE).queryForList(sql, String.class);
        return uuid;
    }

    public static String getBankNewsUuid(int id) {
        String sql = "SELECT uuid FROM news WHERE id=?";
        String uuid = getDBConnection(ABS_INFO_SERVICE).queryForObject(sql, String.class, id);
        LOG.info(String.format("Получен uuid news: %s по id: %s", uuid, id));
        return uuid;
    }
}
