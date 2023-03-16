package com.igo.models.ticket;


import com.igo.models.fares.Cost;
import com.igo.models.payment.Card;

import java.util.Date;

/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 01/03/23
 */

public class Ticket {
    private static int EXPIRE_YEARS = 2;
    private static int totalTicketsGenerated = 0;
    private int ticketId;
    private String areaCode;
    private Date expireDate,purchaseDate;
    private double price;
    private Cost.PERIOD ticketType;
    private boolean cashPayment;
    private Card card;
    private int numberOfTicket;
    private boolean isUsed;

    public static Ticket generateTicketByCash(String areaCode, Cost.PERIOD ticketType, int numberOfTicket) {
        return new Ticket(areaCode,ticketType,numberOfTicket);
    }
    public static Ticket generateTicketByCard(String areaCode, Cost.PERIOD ticketType, int numberOfTicket,Card card) {
        return new Ticket(areaCode,ticketType,numberOfTicket,card);
    }
    private Ticket(String areaCode, Cost.PERIOD ticketType, int numberOfTicket) {
        this.ticketId = ++totalTicketsGenerated;
        this.areaCode = areaCode;
        this.ticketType = ticketType;
        this.cashPayment = true;
        this.numberOfTicket = numberOfTicket;
        this.price = Cost.getTicketPriceByTimePeriod(ticketType);
        this.purchaseDate = new Date();
        this.card= null;
        Date date = new Date();
        date.setYear(date.getYear() + EXPIRE_YEARS);
        this.expireDate = date;
    }
    private Ticket(String areaCode, Cost.PERIOD ticketType, int numberOfTicket, Card card) {
        this.ticketId = ++totalTicketsGenerated;
        this.areaCode = areaCode;
        this.ticketType = ticketType;
        this.cashPayment = false;
        this.numberOfTicket = numberOfTicket;
        this.price = Cost.getTicketPriceByTimePeriod(ticketType);
        this.purchaseDate = new Date();
        this.card= card;
        Date date = new Date();
        date.setYear(date.getYear() + EXPIRE_YEARS);
        this.expireDate = date;
    }

}
