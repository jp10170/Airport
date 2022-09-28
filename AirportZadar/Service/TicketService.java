package com.example.AirportZadar.Service;

import com.example.AirportZadar.Model.Flight;
import com.example.AirportZadar.Model.Ticket;
import com.example.AirportZadar.Model.User;
import com.example.AirportZadar.Repository.FlightRepo;
import com.example.AirportZadar.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class TicketService {
    @Autowired
    public TicketRepo repository;
    @Autowired
    public FlightRepo flightRepo;

    public TicketService(TicketRepo repository) {
        this.repository = repository;
    }

    public void saveTicket(Flight flight, User user, Ticket ticket) {
        if(ticket.getSeatclass().equalsIgnoreCase("Economy")){
            ticket.setTicketPrice(flight.getRoute().getRoutePrice()+flight.getPlane().getEconomyPrice());
            ticket.setSeatno(flight.getAvailableEconomy());
            flight.setAvailableEconomy(flight.getAvailableEconomy() - 1);
        }
        else{
            ticket.setTicketPrice(flight.getRoute().getRoutePrice()+flight.getPlane().getBusinessPrice());
            ticket.setSeatno(flight.getAvailableBusiness());
            flight.setAvailableBusiness(flight.getAvailableBusiness() - 1);
        }
        flight.setAvailableSeats(flight.getAvailableSeats()-1);
        flightRepo.save(flight);
        ticket.setFlight(flight);
        ticket.setUser(user);
        repository.save(ticket); 
    }

    public void deleteTicket(int id) {
        Ticket ticket = repository.findById(id);
        Flight flight = ticket.getFlight();
        if(ticket.getSeatclass().equalsIgnoreCase("Economy")){
            flight.setAvailableEconomy(flight.getAvailableEconomy()+1);
        }
        else{
            flight.setAvailableBusiness(flight.getAvailableBusiness()+1);
        }
        flight.setAvailableSeats(flight.getAvailableSeats()+1);
        flightRepo.save(flight);
        repository.deleteById(id);
    }
    
    
}
