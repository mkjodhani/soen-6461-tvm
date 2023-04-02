package com.igo.models.opus;

import com.igo.models.data.Data;
import com.igo.models.fares.Cost;
import com.igo.models.person.Customer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 01/03/23
 */

public class OPUS {
    private static int totalOpusCards = 0;
    private int cardId;
    private Customer customer;
    private LocalDate lastRechargeDate;
    private LocalDate rechargeExpireDate;
    private Date issueDate;
    private Cost.PERIOD currentPlan;

    public OPUS(Customer customer) {
        this.customer = customer;
        this.cardId = ++ totalOpusCards;
        this.lastRechargeDate = null;
        this.issueDate = new Date();
        Data.getReference().getOpusHashMap().put(this.cardId,this);
    }
    public static OPUS registerOpus(int userID) {
        Customer customer = Data.getReference().getCustomerHashMap().getOrDefault(userID,null);
        if (customer == null){
            return null;
        }
        else if(isOpusExistsForUser(userID)){
            return null;
        }
        OPUS opus = new OPUS(customer);
        return opus;
    }
    public static boolean isOpusExistsForUser(int userID){
        for(OPUS opus: Data.getReference().getOpusHashMap().values()){
            if(opus.getCustomer().getUserID() == userID){
                return true;
            }
        }
        return false;
    }

    public void recharge(Cost.PERIOD timePeriod){
        LocalDate today = LocalDate.now();
        currentPlan = timePeriod;
        this.lastRechargeDate = LocalDate.now();
        switch (timePeriod){
            case ONE_WEEK:
                today.with(nextOrSame(DayOfWeek.SUNDAY));
                this.rechargeExpireDate = today;
                break;
            case ONE_MONTH:
                this.rechargeExpireDate = today.withDayOfMonth(
                        today.getMonth().length(today.isLeapYear()));
                break;
            case FOUR_MONTH:
                LocalDate date = today.plusMonths(3);
                this.rechargeExpireDate = date.withDayOfMonth(
                        date.getMonth().length(date.isLeapYear()));
                break;
            default:
                this.lastRechargeDate = null;
                this.rechargeExpireDate = null;
        }
    }


    public Customer getCustomer() {
        return customer;
    }

    public int getCardId() {
        return cardId;
    }

    public String getLastRechargeDate() {
        if (lastRechargeDate == null){
            return "";
        }
        return lastRechargeDate.toString();
    }

    public String getRechargeExpireDate() {
        if (rechargeExpireDate == null){
            return "";
        }
        return rechargeExpireDate.toString();
    }

    public String getIssueDate() {
        return issueDate.toLocaleString();
    }

    public Cost.PERIOD getCurrentPlan() {
        return currentPlan;
    }
}
