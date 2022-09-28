package com.example.AirportZadar.Controller;

import com.example.AirportZadar.Model.Plane;
import com.example.AirportZadar.Service.PlaneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Korisnik
 */
@Controller
public class PlaneController {
    @Autowired
    public PlaneService service;

    public PlaneController(PlaneService service) {
        this.service = service;
    }
    
    @GetMapping("employee/managePlanes")
    public String showAddPlanes(Model model){
        List<Plane> planes = service.getSorted();
        model.addAttribute("planes",planes);
        model.addAttribute("newPlane", new Plane());
        return "Planes/managePlanes";
    }
    
    @GetMapping("employee/managePlanesAddFailed")
    public String showAddPlanesAddFailed(Model model){
        List<Plane> planes = service.getSorted();
        model.addAttribute("planes",planes);
        model.addAttribute("newPlane", new Plane());
        return "Planes/managePlanesAddFailed";
    }
    
    @PostMapping("/employee/processAddPlane")
    public RedirectView addRoute(@ModelAttribute Plane newPlane){
        Boolean planeSaved = service.savePlane(newPlane);
        if(planeSaved == true){
            return new RedirectView("http://localhost:8080/employee/managePlanes");
        }
        return new RedirectView("http://localhost:8080/employee/managePlanesAddFailed");
    }
    
    
}
