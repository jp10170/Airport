package com.example.AirportZadar.Repository;

import com.example.AirportZadar.Model.Flight;
import java.sql.Timestamp;
import java.util.List;
import net.bytebuddy.TypeCache.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */

@Repository

public interface FlightRepo extends JpaRepository <Flight, Integer> {
    
    public List<Flight> findAll();

    public Flight findById(int flight_id);
    
    public void deleteById(int flight_id);

    
    
   
}
