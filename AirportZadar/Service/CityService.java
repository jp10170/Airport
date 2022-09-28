package com.example.AirportZadar.Service;

import com.example.AirportZadar.Model.City;
import com.example.AirportZadar.Repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class CityService {
    
    @Autowired
    public CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public List<City> findAll() {
        return repository.findAll();
    }

    public List<City> sortName() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "cityName"));
    }
    
    
}
