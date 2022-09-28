package com.example.AirportZadar.Service;

import com.example.AirportZadar.Model.Role;
import com.example.AirportZadar.Model.User;
import com.example.AirportZadar.Repository.UserRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class UserService {
    
    @Autowired
    public UserRepo repository;
    
    public UserService(UserRepo repository){
        this.repository = repository;
    }

    public boolean processRegistration(User user) {
        ArrayList<User> users = (ArrayList<User>) repository.findAll();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getEmail().compareTo(user.getEmail()) == 0 ){
                return false;
            }
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = new Role();
        role.setRole_id(3);
        user.setRole(role);
        repository.save(user);
        return true;
    }

    public List<User> getUsers() {
        List<User> users = repository.findAll(Sort.by(Sort.Direction.ASC, "email"));
        List<User> u = new ArrayList();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getRole().getRole_id().equals(1)){
                
            }
            else{
                u.add(users.get(i));
            }
        }
        return u;
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<User> getByKeyword(String keyword, int roleId) {
        if(roleId == 1){
            ArrayList<User> users = (ArrayList<User>) repository.findByKeyword(keyword);
            for(int i=0; i<users.size(); i++){
                if(users.get(i).getRole().getRole_id()==1){
                    users.remove(users.get(i));
                }
            }
            return users;
        }
        else
            return repository.findByKeyword(keyword);
    }

    public List<User> findAll(Integer roleId) {
        if(roleId == 1){
            ArrayList<User> users = (ArrayList<User>) repository.findAll(Sort.by(Sort.Direction.ASC, "email"));
            for(int i=0; i<users.size(); i++){
                if(users.get(i).getRole().getRole_id()==1){
                    users.remove(users.get(i));
                }
            }
            return users;
        }
        else
            return repository.findAll(Sort.by(Sort.Direction.ASC, "email"));
    }

    public User getUserTickets(Authentication authentication) {
        User user = getUserByEmail(authentication.getName());
        int id = user.getUser_id();
        return repository.findById(id);
    }

    public User findById(int id) {
        return repository.findById(id);
    }

    public void updateUser(User oldUser, int id) {
        User user = findById(id);
        user.setRole(oldUser.getRole());
        repository.save(user);
        
    }

    public User getUsersTickets(int id) {
        return repository.findById(id);
    }
    
}
