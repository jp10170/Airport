package com.example.AirportZadar.Model;

/**
 *
 * @author Korisnik
 */
public class TicketHelper {
    
    private Integer economyPrice;
    private Integer businessPrice;
    
    public TicketHelper(){}

    public TicketHelper(Integer economyPrice, Integer businessPrice) {
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
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
