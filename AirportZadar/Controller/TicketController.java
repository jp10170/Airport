package com.example.AirportZadar.Controller;

import com.example.AirportZadar.Model.Flight;
import com.example.AirportZadar.Model.Ticket;
import com.example.AirportZadar.Model.TicketHelper;
import com.example.AirportZadar.Model.User;
import com.example.AirportZadar.Service.FlightService;
import com.example.AirportZadar.Service.TicketService;
import com.example.AirportZadar.Service.UserService;
import java.lang.ProcessBuilder.Redirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class TicketController {
    @Autowired
    public TicketService service;
    @Autowired
    public FlightService flightService;
    @Autowired
    public UserService userService;

    public TicketController(TicketService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "user/bookTicket/{ID}",method = {RequestMethod.GET})
    public String showBookTicket(Authentication authentication, Model model, @PathVariable(value = "ID") Integer ID){
        
        Flight flight = flightService.findById(ID);
        TicketHelper helper = new TicketHelper();
        helper.setBusinessPrice(flight.getRoute().getRoutePrice()+flight.getPlane().getBusinessPrice());
        helper.setEconomyPrice(flight.getRoute().getRoutePrice()+flight.getPlane().getEconomyPrice());
        model.addAttribute("flight", flight);
        model.addAttribute("helper", helper);
        model.addAttribute("ticket", new Ticket());
        return "Ticket/bookTicket";

    }
    
    @PostMapping(value = "user/processBookTicket/{ID}")
    public RedirectView processBookTicket(Authentication authentication,@ModelAttribute Ticket ticket, @PathVariable(value = "ID") Integer ID){
        Flight flight = flightService.findById(ID);
        User user = userService.getUserByEmail(authentication.getName());
        service.saveTicket(flight, user, ticket);
        return new RedirectView("http://localhost:8080/flights");
    }
    
    @RequestMapping(value = "user/myTickets",method = {RequestMethod.GET})
    public String myTickets(Authentication authentication, Model model){
        User user = userService.getUserTickets(authentication);
        if(user != null){
            model.addAttribute("user", user);
            return "Ticket/myTickets";
        }
        else{
            return "Ticket/noTickets";
        }
    }
    
    @RequestMapping(value = "user/deleteTicket/{ID}",method = {RequestMethod.GET, RequestMethod.DELETE})
    public RedirectView cancelTicket(@PathVariable(value = "ID") Integer id){
        service.deleteTicket(id);
        return new RedirectView("http://localhost:8080/user/myTickets");
    }
    
    @RequestMapping(value = "employee/checkTickets/{ID}",method = {RequestMethod.GET})
    public String userTickets(Model model, @PathVariable(value = "ID") Integer id){
        User user = userService.getUsersTickets(id);
        if(user != null){
            model.addAttribute("user", user);
            return "Ticket/userTickets";
        }
        else{
            return "Ticket/noTickets";
        }
    }
    
}
