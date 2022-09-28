package com.example.AirportZadar.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Korisnik
 */
@Entity
public class Route {
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer route_id;
    
    @JsonIgnore
    @OneToMany(targetEntity = Flight.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_fk")
    private List<Flight> flights;
    
    private String routeName;
    private Integer routePrice;

    public Route() {
    }
    
    public Route(Integer route_id, String routeName, Integer routePrice) {
        this.route_id = route_id;
        this.routeName = routeName;
        this.routePrice = routePrice;
    }

    public Integer getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getRoutePrice() {
        return routePrice;
    }

    public void setRoutePrice(Integer routePrice) {
        this.routePrice = routePrice;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
    
    
    
    
}
