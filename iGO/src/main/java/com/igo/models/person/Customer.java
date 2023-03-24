package com.igo.models.person;

import com.igo.models.data.Data;
import com.igo.models.fares.Cost;
import com.igo.models.opus.OPUS;
import com.igo.models.payment.Card;
import com.igo.models.ticket.Ticket;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 01/03/23
 */
public class Customer extends User{
    public enum TYPES{
        //includes international and local students
        STUDENT,
        //        age 65 and +
        SENIOR_CITIZEN,
        //        age 6 - 17
        CHILD,
        //        age 18 and +
        ADULT,
    }
    private TYPES customerType;
    private OPUS opusCard;

    private Customer(String firstName, String lastName, LocalDate birthDate, TYPES customerType) {
        super(firstName, lastName, birthDate);
        this.customerType = customerType;
    }

    public TYPES getCustomerType() {
        return customerType;
    }

    public OPUS getOpusCard() {
        return opusCard;
    }

    public void setCustomerType(TYPES customerType) {
        this.customerType = customerType;
    }

    public void setOpusCard(OPUS opusCard) {
        this.opusCard = opusCard;
    }

    public Ticket purchaseTicketUsingCase(String areaCode, Cost.PERIOD ticketType){
        return Ticket.generateTicketByCash(areaCode,ticketType);
    }
    public Ticket purchaseTicketBankCard(String areaCode, Cost.PERIOD ticketType, Card card){
        return Ticket.generateTicketByCard(areaCode,ticketType,card);
    }
    public static Customer  registerCustomer(String firstName, String lastName, LocalDate birthDate, TYPES customerType) {
        Customer customer = new Customer(firstName,lastName,birthDate,customerType);
        Data data = Data.getReference();
        data.getCustomerHashMap().put(customer.getUserID(),customer);
        return customer;
    }

}
