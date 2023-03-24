package com.igo.mock;

import com.igo.models.fares.Cost;
import com.igo.models.person.Customer;
import com.igo.models.ticket.Ticket;

import java.time.LocalDate;

/**
 * @author mkjodhani
 * @project
 * @since 24/03/23
 */
public class RepresentativeData {
    public static void ImportUsers() {
        // STUDENT
        Customer student1 = Customer.registerCustomer("Mayur","Jodhani", LocalDate.of(2000,3,1), Customer.TYPES.STUDENT);
        // SENIOR CITIZEN
        Customer seniorCitizen = Customer.registerCustomer("Nithin Reddy","Indurthi", LocalDate.of(1970,2,12), Customer.TYPES.SENIOR_CITIZEN);
        // ADULT
        Customer adult = Customer.registerCustomer("Nithin","Harikrishnan", LocalDate.of(1997,11,4), Customer.TYPES.ADULT);
        // CHILD
        Customer child = Customer.registerCustomer("Venkat Sai","Janumpally", LocalDate.of(2012,5,6), Customer.TYPES.CHILD);
        // CHILD
        Customer student2 = Customer.registerCustomer("Abhishek","Gupta", LocalDate.of(2010,8,7), Customer.TYPES.CHILD);
    }
    public static void ImportTickets() {
        String areaCode = "H3W";
        Ticket oneDayTicket = Ticket.generateTicketByCash(areaCode, Cost.PERIOD.ONE_DAY);
        Ticket oeTripticket = Ticket.generateTicketByCash(areaCode, Cost.PERIOD.ONE_TRIP);
        Ticket twoTripsticket = Ticket.generateTicketByCash(areaCode, Cost.PERIOD.TWO_TRIPS);
        Ticket threeDayticket = Ticket.generateTicketByCash(areaCode, Cost.PERIOD.THREE_DAYS);
        Ticket unlimitedWeekendticket = Ticket.generateTicketByCash(areaCode, Cost.PERIOD.THREE_DAYS);
        Ticket groupTicket = Ticket.generateTicketByCash(areaCode, Cost.PERIOD.THREE_DAYS);
    }
}
