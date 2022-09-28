package com.example.AirportZadar.Controller;

import com.example.AirportZadar.Model.City;
import com.example.AirportZadar.Model.FlightRouteHelper;
import com.example.AirportZadar.Model.Route;
import com.example.AirportZadar.Service.CityService;
import com.example.AirportZadar.Service.RouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Korisnik
 */
@Controller
public class RouteController {
    @Autowired
    public RouteService service;
    @Autowired CityService cityService;

    public RouteController(RouteService service) {
        this.service = service;
    }
    
    @GetMapping("employee/manageRoutes")
    public String showAddRoutes(Model model){
        List<Route> routes = service.sortName();
        List<City> cities = cityService.sortName();
        model.addAttribute("routes", routes);
        model.addAttribute("cities", cities);
        model.addAttribute("newRoute", new Route());
        model.addAttribute("helper", new FlightRouteHelper());
        return "Routes/showAddRoutes";
    }
    
    @GetMapping("employee/manageRoutesAddFailed")
    public String showAddRoutesFailed(Model model){
        List<Route> routes = service.sortName();
        List<City> cities = cityService.sortName();
        model.addAttribute("routes", routes);
        model.addAttribute("cities", cities);
        model.addAttribute("newRoute", new Route());
        model.addAttribute("helper", new FlightRouteHelper());
        return "Routes/showAddRoutesFailed";
    }
    
    @PostMapping("/employee/processAddRoute")
    public RedirectView addRoute(@ModelAttribute Route newRoute, @ModelAttribute FlightRouteHelper helper){
        System.out.println(helper.getDestination());
        System.out.println(helper.getStart());
        Boolean routeSet = service.setRouteName(newRoute, helper);
        if(routeSet == true){
            service.saveRoute(newRoute);
            return new RedirectView("http://localhost:8080/employee/manageRoutes");
        }
        return new RedirectView("http://localhost:8080/employee/manageRoutesAddFailed");
    }
    
    @GetMapping("employee/updateRoutePrice/{ID}")
    public String showUpdateRouteForm(Model model, @PathVariable(value = "ID") Integer id){
        Route oldRoute = service.findById(id);
        model.addAttribute("oldRoute", oldRoute);
        model.addAttribute("updatedRoute", new Route());
        return "Routes/updateRoutePriceForm";
    }
    
    @RequestMapping(value = "employee/processUpdateRoutePrice/{ID}", method = {RequestMethod.PUT, RequestMethod.GET})
    public RedirectView updateFlight(@ModelAttribute Route updatedRoute, @PathVariable(value = "ID") Integer id){
        Route oldRoute = service.findById(id);
        oldRoute.setRoutePrice(updatedRoute.getRoutePrice());
        service.saveRoute(oldRoute);
        return new RedirectView("http://localhost:8080/employee/manageRoutes");
    }
    
    @RequestMapping(value = "employee/deleteRoute/{ID}", method = {RequestMethod.DELETE, RequestMethod.GET})
    RedirectView deleteRoute(@PathVariable(value = "ID") Integer id){
        service.deleteRoute(id);
        return new RedirectView("http://localhost:8080/employee/manageRoutes");
    }
}
