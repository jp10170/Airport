package com.example.AirportZadar.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 *
 * @author Korisnik
 */

@Entity
public class Flight {
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer flight_id;
    
    @JsonIgnore
    @OneToMany(targetEntity = Ticket.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_fk")
    private List<Ticket> tickets = new ArrayList();
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plane_fk")
    private Plane plane;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_fk")
    private Route route;
    
    private Timestamp departTime;
    private Timestamp landingTime;
    private Integer availableSeats;
    private Integer availableEconomy;
    private Integer availableBusiness;
    
    public Flight(){}

    public Flight(Integer flight_id, Plane plane, Route route, Timestamp departTime, Timestamp landingTime, Integer availableSeats, Integer availableEconomy, Integer availableBusiness) {
        this.flight_id = flight_id;
        this.plane = plane;
        this.route = route;
        this.departTime = departTime;
        this.landingTime = landingTime;
        this.availableSeats = availableSeats;
        this.availableEconomy = availableEconomy;
        this.availableBusiness = availableBusiness;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Timestamp getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Timestamp departTime) {
        this.departTime = departTime;
    }

    public Timestamp getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Timestamp landingTime) {
        this.landingTime = landingTime;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getAvailableEconomy() {
        return availableEconomy;
    }

    public void setAvailableEconomy(Integer availableEconomy) {
        this.availableEconomy = availableEconomy;
    }

    public Integer getAvailableBusiness() {
        return availableBusiness;
    }

    public void setAvailableBusiness(Integer availableBusiness) {
        this.availableBusiness = availableBusiness;
    }    
}
