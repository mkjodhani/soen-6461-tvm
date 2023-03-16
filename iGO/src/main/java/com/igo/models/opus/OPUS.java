package com.igo.models.opus;

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
    private static HashMap<Integer,OPUS> opusList = new HashMap<>();
    private static int totalOpusCards = 0;
    private int cardId;
    private Customer customer;
    private LocalDate lastRechargeDate;
    private LocalDate rechargeExpireDate;
    private Date issueDate;

    public OPUS generateOpusByCustomer(Customer customer) {
        this.customer = customer;
        this.cardId = ++ totalOpusCards;
        this.lastRechargeDate = null;

        opusList.put(this.cardId,this);
        this.issueDate = new Date();
        return this;
    }
    public static boolean isOpusExistsForUser(String userID){
        for(OPUS opus: opusList.values()){
            if(opus.getCustomer().getUserID().equals(userID)){
                return true;
            }
        }
        return false;
    }

    public void recharge(Cost.PERIOD timePeriod){
        LocalDate today = LocalDate.now();
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

    public LocalDate getLastRechargeDate() {
        return lastRechargeDate;
    }

    public LocalDate getRechargeExpireDate() {
        return rechargeExpireDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public int getCardId() {
        return cardId;
    }
}
