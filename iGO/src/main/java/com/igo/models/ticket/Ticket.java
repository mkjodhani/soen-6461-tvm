package com.igo.models.ticket;


import com.igo.models.data.Data;
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
    private int ticketId,lifetime;
    private String areaCode;
    private Date expireDate,purchaseDate;
    private double price;
    private Cost.PERIOD ticketType;
    private boolean cashPayment;
    private Card card;
    private boolean isUsed;

    public static Ticket generateTicketByCash(String areaCode, Cost.PERIOD ticketType) {
        Ticket ticket = new Ticket(areaCode,ticketType);
        Data.getReference().getTicketHashMap().put(ticket.ticketId,ticket);
        return ticket;
    }
    public static Ticket generateTicketByCard(String areaCode, Cost.PERIOD ticketType,Card card) {
        Ticket ticket = new Ticket(areaCode,ticketType,card);
        Data.getReference().getTicketHashMap().put(ticket.ticketId,ticket);
        return ticket;
    }
    private Ticket(String areaCode, Cost.PERIOD ticketType) {
        this.ticketId = ++totalTicketsGenerated;
        this.areaCode = areaCode;
        this.ticketType = ticketType;
        this.cashPayment = true;
        this.price = Cost.getTicketPriceByTimePeriod(ticketType);
        this.purchaseDate = new Date();
        this.card= null;
        Date date = new Date();
        date.setYear(date.getYear() + EXPIRE_YEARS);
        this.expireDate = date;
        this.isUsed = false;
    }
    private Ticket(String areaCode, Cost.PERIOD ticketType, Card card) {
        this.ticketId = ++totalTicketsGenerated;
        this.areaCode = areaCode;
        this.ticketType = ticketType;
        this.cashPayment = false;
        this.price = Cost.getTicketPriceByTimePeriod(ticketType);
        this.purchaseDate = new Date();
        this.card= card;
        Date date = new Date();
        date.setYear(date.getYear() + EXPIRE_YEARS);
        this.expireDate = date;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getLifetime() {
        return lifetime;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public double getPrice() {
        return price;
    }

    public Cost.PERIOD getTicketType() {
        return ticketType;
    }

    public boolean isCashPayment() {
        return cashPayment;
    }

    public Card getCard() {
        return card;
    }

    public boolean isUsed() {
        return isUsed;
    }
    public void scan(){
        this.isUsed = true;
    }
}
