package com.example.AirportZadar.Repository;


import com.example.AirportZadar.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    
    
}
