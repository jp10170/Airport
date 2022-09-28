package com.example.AirportZadar.Repository;

import com.example.AirportZadar.Model.Plane;
import com.example.AirportZadar.Model.User;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Korisnik
 */
@Controller
public interface UserRepo extends JpaRepository<User, Integer>{
    
    User findByEmail(String email);

    @Query(value = "select * from user u where u.email like %:keyword%", nativeQuery = true)
    List<User> findByKeyword(@Param("keyword") String keyword);
    
    @EntityGraph(value = "User.detail", type = EntityGraph.EntityGraphType.LOAD)
    User findById(int id);
}
