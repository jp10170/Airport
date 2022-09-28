package com.example.AirportZadar.Repository;


import com.example.AirportZadar.Model.Plane;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface PlaneRepo extends JpaRepository <Plane, Integer> {
    ArrayList<Plane> findAll();

    Plane findById(int plane_id);
    
    @EntityGraph(value = "Plane.detail", type = EntityGraphType.LOAD)
    Plane findByplaneName(String planeName);
}
