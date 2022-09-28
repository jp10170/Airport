package com.example.AirportZadar.Repository;

import com.example.AirportZadar.Model.Role;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface RoleRepo extends JpaRepository <Role, Integer> {
    ArrayList<Role> findAll();
    
    Role findById(int id);
}
