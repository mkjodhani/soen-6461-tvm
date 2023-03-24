package com.igo.models.fares;

import com.igo.models.person.Customer;

import java.util.HashMap;

/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 02/03/23
 */
public class Cost {
    public enum TYPES{
        TICKET,
        OPUS
    }
    public enum PERIOD {
        TWO_TRIPS,
        ONE_TRIP,
        THREE_DAYS,
        ONE_DAY,
        UN_LIMITED_WEEKEND,
        GROUP,
        ONE_WEEK,
        ONE_MONTH,
        FOUR_MONTH,
    }
    private static HashMap<TYPES,HashMap<PERIOD,HashMap<Customer.TYPES,Double>>> fares = new HashMap();
    static {
//        put all the fairs for OPUS
        fares.put(TYPES.OPUS,new HashMap<>());

//        Weekly fairs
        fares.get(TYPES.OPUS).put(PERIOD.ONE_WEEK,new HashMap<>());
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(Customer.TYPES.CHILD,17.50);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(Customer.TYPES.SENIOR_CITIZEN,8.75);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(Customer.TYPES.ADULT,29.00);

//        Monthly fairs
        fares.get(TYPES.OPUS).put(PERIOD.ONE_MONTH,new HashMap<>());
        fares.get(TYPES.OPUS).get(PERIOD.ONE_MONTH).put(Customer.TYPES.CHILD,56.50);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_MONTH).put(Customer.TYPES.STUDENT,56.50);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_MONTH).put(Customer.TYPES.SENIOR_CITIZEN,28.25);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_MONTH).put(Customer.TYPES.ADULT,94.00);
//        Four month fairs
        fares.get(TYPES.OPUS).put(PERIOD.FOUR_MONTH,new HashMap<>());
        fares.get(TYPES.OPUS).get(PERIOD.FOUR_MONTH).put(Customer.TYPES.CHILD,220.00);
        fares.get(TYPES.OPUS).get(PERIOD.FOUR_MONTH).put(Customer.TYPES.STUDENT,220.00);
        fares.get(TYPES.OPUS).get(PERIOD.FOUR_MONTH).put(Customer.TYPES.SENIOR_CITIZEN,110.00);


        fares.put(TYPES.TICKET,new HashMap<>());
        fares.get(TYPES.TICKET).put(PERIOD.TWO_TRIPS, new HashMap<>());
        fares.get(TYPES.TICKET).put(PERIOD.ONE_TRIP, new HashMap<>());
        fares.get(TYPES.TICKET).put(PERIOD.THREE_DAYS, new HashMap<>());
        fares.get(TYPES.TICKET).put(PERIOD.UN_LIMITED_WEEKEND, new HashMap<>());
        fares.get(TYPES.TICKET).put(PERIOD.ONE_DAY, new HashMap<>());
        fares.get(TYPES.TICKET).put(PERIOD.GROUP, new HashMap<>());
        fares.get(TYPES.TICKET).get(PERIOD.TWO_TRIPS).put(Customer.TYPES.ADULT,6.5);
        fares.get(TYPES.TICKET).get(PERIOD.ONE_TRIP).put(Customer.TYPES.ADULT,3.5);
        fares.get(TYPES.TICKET).get(PERIOD.THREE_DAYS).put(Customer.TYPES.ADULT,21.25);
        fares.get(TYPES.TICKET).get(PERIOD.UN_LIMITED_WEEKEND).put(Customer.TYPES.ADULT,14.75);
        fares.get(TYPES.TICKET).get(PERIOD.ONE_DAY).put(Customer.TYPES.ADULT,11.00);
        fares.get(TYPES.TICKET).get(PERIOD.GROUP).put(Customer.TYPES.ADULT,19.50);
    }

    public static double getTicketPriceByTimePeriod(PERIOD period){
        return fares.get(TYPES.TICKET).get(period).getOrDefault(Customer.TYPES.ADULT,-1.00);
    }
    public static double getOpusRechargeAmount(PERIOD period,Customer.TYPES type){
        return fares.get(TYPES.OPUS).getOrDefault(period,new HashMap<>()).getOrDefault(type,-1.00);
    }

    public static void updateTicketPriceByTimePeriod(PERIOD period, double amount){
        fares.get(TYPES.TICKET).get(period).put(Customer.TYPES.ADULT,amount);
    }
    public static void updateOpusRechargeAmount(PERIOD period,Customer.TYPES type, double amount){
        fares.get(TYPES.OPUS).get(period).put(type,amount);
    }
}
