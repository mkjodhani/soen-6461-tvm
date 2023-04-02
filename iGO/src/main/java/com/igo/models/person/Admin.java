package com.igo.models.person;
import com.igo.models.fares.Cost;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 01/03/23
 */
public class Admin extends User{
    private String areaCode;

    public Admin(String firstName, String lastName, LocalDate birthDate, String areaCode) {
        super(firstName, lastName, birthDate);
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void updateTicketPriceByTimePeriod(Cost.PERIOD period, double amount){
        Cost.updateTicketPriceByTimePeriod(period,amount);
    }
    public void updateOpusRechargeAmount(Cost.PERIOD period, Customer.TYPES type, double amount){
        Cost.updateOpusRechargeAmount(period,type,amount);
    }

}
