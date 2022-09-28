package com.example.AirportZadar.Service;

import com.example.AirportZadar.Model.FlightRouteHelper;
import com.example.AirportZadar.Model.Route;
import com.example.AirportZadar.Repository.RouteRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class RouteService {
    @Autowired
    public RouteRepo repository;

    public RouteService(RouteRepo repository) {
        this.repository = repository;
    }

    public List<Route> sortName() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "routeName"));
    }
    
    public List<Route> findAll(){
       return repository.findAll();
    }

    public Boolean setRouteName(Route route, FlightRouteHelper helper) {
        if(helper.getStart().equals(helper.getDestination())){
            return false;
        }
        String rName = helper.getStart() + "-" + helper.getDestination();
        List<Route> routes = repository.findAll();
        for(int i=0; i<routes.size(); i++){
            if(rName.equals(routes.get(i).getRouteName())){
                return false;
            }
        }
        route.setRouteName(rName);
        return true;
    }

    public void saveRoute(Route newRoute) {
        repository.save(newRoute);
    }

    public Route findById(int id) {
        return repository.findById(id);
    }

    public void deleteRoute(Integer id) {
        repository.deleteById(id);
    }
    
    
}
