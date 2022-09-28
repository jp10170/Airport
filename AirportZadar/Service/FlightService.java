package com.example.AirportZadar.Service;

import com.example.AirportZadar.Model.Flight;
import com.example.AirportZadar.Model.Plane;
import com.example.AirportZadar.Model.FlightRouteHelper;
import com.example.AirportZadar.Model.Route;
import com.example.AirportZadar.Repository.FlightRepo;
import com.example.AirportZadar.Repository.PlaneRepo;
import com.example.AirportZadar.Repository.RouteRepo;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class FlightService {
    
    @Autowired
    public FlightRepo repository;
    @Autowired 
    PlaneRepo planeRepo;
    @Autowired
    RouteRepo routeRepo;

    public FlightService(FlightRepo repository) {
        this.repository = repository;
    }

    public List<Flight> findAll() {
        return repository.findAll();
    }
    
    public void setTime(FlightRouteHelper time, Flight newFlight){
        String d = time.getDepartDateTime().replace("T"," ") + ":00";
        String a = time.getLandingDateTime().replace("T"," ") + ":00";
        newFlight.setDepartTime(Timestamp.valueOf(d));
        newFlight.setLandingTime(Timestamp.valueOf(a));
    }

    public boolean checkPlaneScheduled(Flight newFlight) {
        boolean bol = false;
        LocalDateTime now = LocalDateTime.now();
        Timestamp currentTime = Timestamp.valueOf(now);
        if(currentTime.compareTo(newFlight.getDepartTime())>=0){
            return true;
        }
        var plane = planeRepo.findByplaneName(newFlight.getPlane().getPlaneName());
        if(plane != null){
            List<Flight> flights = plane.getFlights();
            for(int i = 0; i<flights.size(); i++){
                Flight flight = flights.get(i);
                if(((flight.getDepartTime().compareTo(newFlight.getDepartTime()) >= 0 && flight.getDepartTime().compareTo(newFlight.getLandingTime()) >=0) ||
                    (flight.getLandingTime().compareTo(newFlight.getDepartTime()) <= 0 && flight.getLandingTime().compareTo(newFlight.getLandingTime()) <=0)))
                {
                    bol = false;
                }
                else
                {
                    return true;
                }
            }
        }
        else{
            if(newFlight.getDepartTime().compareTo(newFlight.getLandingTime()) >0){
                return true;
            }
        }
        return bol;
    }
    
    
    

    public void saveFlight(Flight newFlight) {
        Plane plane = newFlight.getPlane();
        newFlight.setAvailableBusiness(plane.getTotalSeatsBusiness());
        newFlight.setAvailableEconomy(plane.getTotalSeatsEconomy());
        newFlight.setAvailableSeats(plane.getTotalSeats());
        repository.save(newFlight);
    }

    public void deleteFlight(int id) {
        
        repository.deleteById(id);
    }

    public List<Flight> sortDepart() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "departTime"));
    }

    public Flight findById(int id) {
        return repository.findById(id);
    }

    public boolean checkPlaneUpdateScheduled(Flight updatedFlight) {
        int br = 0;
        boolean bol = false;
        LocalDateTime now = LocalDateTime.now();
        Timestamp currentTime = Timestamp.valueOf(now);
        if(currentTime.compareTo(updatedFlight.getDepartTime())>=0){
            return true;
        }
        var plane = planeRepo.findByplaneName(updatedFlight.getPlane().getPlaneName());
        if(plane != null){
            List<Flight> flights = plane.getFlights();
            for(int i = 0; i<flights.size(); i++){
                Flight flight = flights.get(i);
                if(((flight.getDepartTime().compareTo(updatedFlight.getDepartTime()) >= 0 && flight.getDepartTime().compareTo(updatedFlight.getLandingTime()) >=0) ||
                    (flight.getLandingTime().compareTo(updatedFlight.getDepartTime()) <= 0 && flight.getLandingTime().compareTo(updatedFlight.getLandingTime()) <=0)))
                {
                    bol = false;
                }
                else
                {
                    br++;
                    if(br >= 2){
                         return true;
                    }
                }
            }
        }
        else{
            if(updatedFlight.getDepartTime().compareTo(updatedFlight.getLandingTime()) >0){
                return true;
            }
        }
        return bol;
    }

    public List<Flight> findByKeyword(String keyword) {
        List<Route> routes = routeRepo.findByKeyword(keyword);
        List<Flight> flights = repository.findAll(Sort.by(Sort.Direction.ASC, "departTime"));
        List<Flight> searchFlight = new ArrayList();
        if(routes == null){
            return searchFlight;
        }
        for(int i=0; i < routes.size(); i++){
            for(int j=0; j < flights.size(); j++){
                if(routes.get(i).getRoute_id().equals(flights.get(j).getRoute().getRoute_id())){
                    searchFlight.add(flights.get(i));
                }
            }
        }
        return searchFlight;
    }
}
    
    
    

