package com.example.AirportZadar.Controller;


import com.example.AirportZadar.Model.FlightRouteHelper;
import com.example.AirportZadar.Model.Flight;
import com.example.AirportZadar.Model.Plane;
import com.example.AirportZadar.Model.Route;
import com.example.AirportZadar.Service.FlightService;
import com.example.AirportZadar.Service.PlaneService;
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
public class FlightController {
    @Autowired
    public FlightService service;
    @Autowired
    public PlaneService planeService;
    @Autowired
    public RouteService routeService;

    public FlightController(FlightService service) {
        this.service = service;
    }
    
    @RequestMapping(path = {"flights", "flights/search"}, method = {RequestMethod.GET})
    public String Journeys( Model model, String keyword){
        
        if(keyword!=null){
            List<Flight> flights = service.findByKeyword(keyword);
            model.addAttribute("flights", flights);
        }
        else {
            List<Flight> flights = service.sortDepart();
            model.addAttribute("flights", flights);
        }
        return "Flights/showFlights";
    }
    
    @GetMapping("employee/manageFlights")
    public String showAddFlights(Model model){
        
        
        List<Flight> flights = service.sortDepart();
        List<Plane> planes = planeService.findAll();
        List<Route> routes = routeService.sortName();
        model.addAttribute("flights", flights);
        model.addAttribute("planes", planes);
        model.addAttribute("routes", routes);
        model.addAttribute("newFlight", new Flight());
        model.addAttribute("time", new FlightRouteHelper());
        return "Flights/showAddFlights";
    }
    
    @GetMapping("employee/manageFlightsAddFailed")
    public String showAddFlightsFailed(Model model){
        
        List<Flight> flights = service.sortDepart();
        List<Plane> planes = planeService.findAll();
        List<Route> routes = routeService.sortName();
        model.addAttribute("flights", flights);
        model.addAttribute("planes", planes);
        model.addAttribute("routes", routes);
        model.addAttribute("newFlight", new Flight());
        model.addAttribute("time", new FlightRouteHelper());
        
        return "Flights/showAddFlightsFailed";
    }
    
    @PostMapping("/employee/processAddFlight")
    public RedirectView addFLight(@ModelAttribute Flight newFlight, @ModelAttribute FlightRouteHelper time){
        service.setTime(time, newFlight);
        boolean bol = service.checkPlaneScheduled(newFlight);
        if(bol == false){
            service.saveFlight(newFlight);
            return new RedirectView("http://localhost:8080/employee/manageFlights");
        }
        return new RedirectView("http://localhost:8080/employee/manageFlightsAddFailed");
    }
    
    @GetMapping("employee/updateFlight/{ID}")
    public String showUpdateFlightForm(Model model, @PathVariable(value = "ID") Integer id){
        Flight oldFlight = service.findById(id);
        model.addAttribute("oldFlight", oldFlight);
        model.addAttribute("time", new FlightRouteHelper());
        return "Flights/updateFlightForm";
    }
    
    @GetMapping("employee/updateFlightFailed/{ID}")
    public String showUpdateFlightFormFailed(Model model, @PathVariable(value = "ID") Integer id){
        Flight oldFlight = service.findById(id);
        model.addAttribute("oldFlight", oldFlight);
        model.addAttribute("time", new FlightRouteHelper());
        return "Flights/updateFlightFormFailed";
    }
    
    @RequestMapping(value = "employee/processUpdateFlight/{ID}", method = {RequestMethod.PUT, RequestMethod.GET})
    public RedirectView updateFlight(@ModelAttribute FlightRouteHelper time, @PathVariable(value = "ID") Integer id){
        Flight updatedFlight = service.findById(id);
        service.setTime(time, updatedFlight);
        boolean bol = service.checkPlaneUpdateScheduled(updatedFlight);
        if(bol == false){
            service.saveFlight(updatedFlight);
            return new RedirectView("http://localhost:8080/employee/manageFlights");
        }
        return new RedirectView("http://localhost:8080/employee/updateFlightFailed/" + id);
    }
    
    @RequestMapping(value = "employee/deleteFlight/{ID}", method = {RequestMethod.DELETE, RequestMethod.GET})
    RedirectView deleteFlight(@PathVariable(value = "ID") Integer id){
        service.deleteFlight(id);
        return new RedirectView("http://localhost:8080/employee/manageFlights");
    }
    
}
