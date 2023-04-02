package com.igo.models.payment;

import com.igo.models.geography.Address;
import com.igo.models.ticket.Ticket;

import java.time.LocalDate;

/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 04/03/23
 */
public class Receipt {
    private Ticket ticket;
    private LocalDate timeStamp;
    private Address address;

    public Ticket getTicket() {
        return ticket;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public Address getAddress() {
        return address;
    }

    public Receipt(Ticket ticket, LocalDate timeStamp, Address address) {
        this.ticket = ticket;
        this.timeStamp = timeStamp;
        this.address = address;
    }
}
