package com.example.AirportZadar.Repository;

import com.example.AirportZadar.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    Ticket findById(int id);
    
}
