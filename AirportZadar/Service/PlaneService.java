package com.example.AirportZadar.Service;

import com.example.AirportZadar.Model.Plane;
import com.example.AirportZadar.Repository.PlaneRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class PlaneService {
    @Autowired
    public PlaneRepo repository;

    public PlaneService(PlaneRepo repository) {
        this.repository = repository;
    }

    public List<Plane> findAll() {
        return repository.findAll();
    }

    public List<Plane> getSorted() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "planeName"));
    }

    public Boolean savePlane(Plane newPlane) {
        if(!newPlane.getPlaneName().matches("[a-zA-Z]+")){
            return false;
        }
        List<Plane> planes = repository.findAll();
        for(int i=0; i<planes.size(); i++){
            if(planes.get(i).getPlaneName().equalsIgnoreCase(newPlane.getPlaneName())){
                return false;
            }
        }
        int totalSeats = newPlane.getTotalSeatsBusiness() + newPlane.getTotalSeatsEconomy();
        newPlane.setTotalSeats(totalSeats);
        repository.save(newPlane);
        return true;
    }
    
    
}
