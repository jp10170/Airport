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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

/**
 *
 * @author Korisnik
 */
@Entity
@NamedEntityGraph(name = "Plane.detail",
        attributeNodes = @NamedAttributeNode("flights"))
public class Plane{
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer plane_id;
    
    @JsonIgnore
    @OneToMany(targetEntity = Flight.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "plane_fk")
    private List<Flight> flights = new ArrayList();
    
    
    private String planeName;
    private Integer totalSeats;
    private Integer totalSeatsEconomy;
    private Integer totalSeatsBusiness;
    private Integer economyPrice;
    private Integer businessPrice;
    
    public Plane(){}

    public Plane(Integer plane_id, String planeName, Integer totalSeats, Integer totalSeatsEconomy, Integer totalSeatsBusiness, Integer economyPrice, Integer businessPrice) {
        this.plane_id = plane_id;
        this.planeName = planeName;
        this.totalSeats = totalSeats;
        this.totalSeatsEconomy = totalSeatsEconomy;
        this.totalSeatsBusiness = totalSeatsBusiness;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
    }

    public Integer getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(Integer plane_id) {
        this.plane_id = plane_id;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getTotalSeatsEconomy() {
        return totalSeatsEconomy;
    }

    public void setTotalSeatsEconomy(Integer totalSeatsEconomy) {
        this.totalSeatsEconomy = totalSeatsEconomy;
    }

    public Integer getTotalSeatsBusiness() {
        return totalSeatsBusiness;
    }

    public void setTotalSeatsBusiness(Integer totalSeatsBusiness) {
        this.totalSeatsBusiness = totalSeatsBusiness;
    }

    public Integer getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(Integer economyPrice) {
        this.economyPrice = economyPrice;
    }

    public Integer getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(Integer businessPrice) {
        this.businessPrice = businessPrice;
    }

    
    
    

    
    
    
    
}
