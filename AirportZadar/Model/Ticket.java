package com.example.AirportZadar.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Korisnik
 */
@Entity
public class Ticket{

    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer ticket_id;
    private Integer seatno;
    private String seatclass;
    private Integer ticketPrice;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_fk")
    private Flight flight;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;

    public Ticket() {
    }

    public Ticket(Integer ticket_id, Integer seatno, String seatclass, Integer ticketPrice, Flight flight, User user) {
        this.ticket_id = ticket_id;
        this.seatno = seatno;
        this.seatclass = seatclass;
        this.ticketPrice = ticketPrice;
        this.flight = flight;
        this.user = user;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getSeatno() {
        return seatno;
    }

    public void setSeatno(Integer seatno) {
        this.seatno = seatno;
    }

    public String getSeatclass() {
        return seatclass;
    }

    public void setSeatclass(String seatclass) {
        this.seatclass = seatclass;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    
    
}
