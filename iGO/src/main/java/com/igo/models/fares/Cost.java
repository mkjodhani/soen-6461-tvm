package com.igo.models.fares;

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
    public enum customerType{
        //includes international and local students
        STUDENT,
        //        age 65 and +
        SENIOR_CITIZEN,
        //        age 6 - 17
        CHILD,
        //        age 18 and +
        ADULT,
    }
    private static HashMap<TYPES,HashMap<PERIOD,HashMap<customerType,Double>>> fares = new HashMap();
    static {
//        put all the fairs for OPUS
        fares.put(TYPES.OPUS,new HashMap<>());

//        Weekly fairs
        fares.get(TYPES.OPUS).put(PERIOD.ONE_WEEK,new HashMap<>());
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(customerType.CHILD,17.50);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(customerType.SENIOR_CITIZEN,8.75);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(customerType.ADULT,29.00);

//        Monthly fairs
        fares.get(TYPES.OPUS).put(PERIOD.ONE_MONTH,new HashMap<>());
        fares.get(TYPES.OPUS).get(PERIOD.ONE_MONTH).put(customerType.CHILD,56.50);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_MONTH).put(customerType.STUDENT,56.50);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_MONTH).put(customerType.SENIOR_CITIZEN,28.25);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_MONTH).put(customerType.ADULT,94.00);
//        Four month fairs
        fares.get(TYPES.OPUS).put(PERIOD.ONE_WEEK,new HashMap<>());
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(customerType.CHILD,220.00);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(customerType.STUDENT,220.00);
        fares.get(TYPES.OPUS).get(PERIOD.ONE_WEEK).put(customerType.SENIOR_CITIZEN,110.00);


        fares.put(TYPES.TICKET,new HashMap<>());
        fares.get(TYPES.TICKET).get(PERIOD.TWO_TRIPS).put(customerType.ADULT,6.5);
        fares.get(TYPES.TICKET).get(PERIOD.ONE_TRIP).put(customerType.ADULT,3.5);
        fares.get(TYPES.TICKET).get(PERIOD.THREE_DAYS).put(customerType.ADULT,21.25);
        fares.get(TYPES.TICKET).get(PERIOD.UN_LIMITED_WEEKEND).put(customerType.ADULT,14.75);
        fares.get(TYPES.TICKET).get(PERIOD.ONE_DAY).put(customerType.ADULT,11.00);
//        TODO
//        opusFares.get(TYPES.TICKET).get(PERIOD.GROUP).put(customerType.ADULT,220.00);
    }

    public static double getTicketPriceByTimePeriod(PERIOD period){
        return fares.get(TYPES.TICKET).get(period).getOrDefault(customerType.ADULT,-1.00);
    }
    public static double getOpusRechargeAmount(PERIOD period,customerType type){
        return fares.get(TYPES.OPUS).get(period).getOrDefault(type,-1.00);
    }

    public static void updateTicketPriceByTimePeriod(PERIOD period, double amount){
        fares.get(TYPES.TICKET).get(period).put(customerType.ADULT,amount);
    }
    public static void updateOpusRechargeAmount(PERIOD period,customerType type, double amount){
        fares.get(TYPES.OPUS).get(period).put(type,amount);
    }
}
