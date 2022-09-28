package com.example.AirportZadar.Service;

import com.example.AirportZadar.Model.Role;
import com.example.AirportZadar.Repository.RoleRepo;
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
public class RoleService {
    @Autowired
    public RoleRepo repository;

    public RoleService(RoleRepo repository) {
        this.repository = repository;
    }

    public List<Role> getRolesAdmin() {
        List<Role> adminRoles = new ArrayList();
        List<Role> roles = repository.findAll();
        for(int i=0; i<roles.size(); i++){
            if(!roles.get(i).getRole_name().equalsIgnoreCase("Admin")){
                adminRoles.add(roles.get(i));
            }
            
        }
        return adminRoles;
    }
    
    
    
}
