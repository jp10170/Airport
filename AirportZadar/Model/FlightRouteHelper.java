
package com.example.AirportZadar.Model;

/**
 *
 * @author Korisnik
 */
public class FlightRouteHelper {
    
    private String departDateTime;
    private String landingDateTime;
    private String start;
    private String destination;
    
    public FlightRouteHelper() {
    }

    public FlightRouteHelper(String departDateTime, String landingDateTime, String start, String destination) {
        this.departDateTime = departDateTime;
        this.landingDateTime = landingDateTime;
        this.start = start;
        this.destination = destination;
    }

    public String getDepartDateTime() {
        return departDateTime;
    }

    public void setDepartDateTime(String departDateTime) {
        this.departDateTime = departDateTime;
    }

    public String getLandingDateTime() {
        return landingDateTime;
    }

    public void setLandingDateTime(String landingDateTime) {
        this.landingDateTime = landingDateTime;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
 
}
