package com.igo.models.tvm;

import com.igo.models.opus.OPUS;
import com.igo.models.person.Customer;
import com.igo.models.ticket.Ticket;

import java.util.HashMap;
import java.util.Observable;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class TVM extends Observable {
    private HashMap<Integer, Customer> customerHashMap;
    private HashMap<Integer, OPUS> opusHashMap;
    private HashMap<Integer, Ticket> ticketHashMap;
    private static TVM TVM;

    private TVM() {
        customerHashMap = new HashMap<>();
        opusHashMap = new HashMap<>();
        ticketHashMap = new HashMap<>();
    }
    public static TVM getReference(){
        if(TVM == null){
            TVM = new TVM();
        }
        return TVM;
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

    public void notifyAllObservers(){
        setChanged();
        notifyObservers();
    }
}
