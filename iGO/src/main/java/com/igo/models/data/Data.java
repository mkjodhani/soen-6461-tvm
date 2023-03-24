package com.igo.models.data;

import com.igo.models.opus.OPUS;
import com.igo.models.person.Customer;
import com.igo.models.ticket.Ticket;

import java.util.Date;
import java.util.HashMap;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class Data {
    private HashMap<Integer, Customer> customerHashMap;
    private HashMap<Integer, OPUS> opusHashMap;
    private HashMap<Integer, Ticket> ticketHashMap;
    private static Data data;

    private Data() {
        customerHashMap = new HashMap<>();
        opusHashMap = new HashMap<>();
        ticketHashMap = new HashMap<>();
    }
    public static Data getReference(){
        if(data == null){
            data = new Data();
        }
        return data;
    }

    public HashMap<Integer, Customer> getCustomerHashMap() {
        return customerHashMap;
    }

    public HashMap<Integer, OPUS> getOpusHashMap() {
        return opusHashMap;
    }

    public HashMap<Integer, Ticket> getTicketHashMap() {
        return ticketHashMap;
    }
}
