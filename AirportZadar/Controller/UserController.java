package com.example.AirportZadar.Controller;

import com.example.AirportZadar.Model.Role;
import com.example.AirportZadar.Model.User;
import com.example.AirportZadar.Service.RoleService;
import com.example.AirportZadar.Service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
public class UserController {
    @Autowired
    public UserService service;
    @Autowired
    public RoleService roleService;

    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping("/index")
    public String showIndex(){
        return "index";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @GetMapping("/registerfailed")
    public String showRegistrationFormfailed(Model model) {
        model.addAttribute("user", new User());
        return "registerFailed";
    }
     
    @PostMapping("/process_register")
    public RedirectView processRegister(@ModelAttribute User user, Model model){
        boolean bol = service.processRegistration(user);
        if(bol == true){
            return new RedirectView("http://localhost:8080/login");
        }
        else
            return new RedirectView("http://localhost:8080/registerfailed");   
    }
    
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }
    
    
    @RequestMapping(path = {"employee/manageUsers", "employee/manageUsers/search"}, method = {RequestMethod.GET})
    public String manageUsers(Authentication authentication, Model model, String keyword){
        String email = authentication.getName();
        User currentUser = service.getUserByEmail(email);
        if(keyword!=null){
            List<User> users = service.getByKeyword(keyword, currentUser.getRole().getRole_id());
            model.addAttribute("users", users);
        }
        else {
            List<User> users = service.findAll(currentUser.getRole().getRole_id());
            model.addAttribute("users", users);
        }
        return "Users/manageUsers";
    }
    
    @GetMapping("admin/updateRole/{ID}")
    public String showUpdateRoleForm(Model model, @PathVariable(value = "ID") Integer id){
        User user = service.findById(id);
        List<Role> roles = roleService.getRolesAdmin();
        model.addAttribute("oldUser", user);
        model.addAttribute("roles", roles);
        return "Users/updateUserForm";
    }
    
    @RequestMapping(path = {"admin/processUpdateRole/{ID}"}, method = {RequestMethod.GET, RequestMethod.PUT})
    public RedirectView processUpdateRole(@ModelAttribute User oldUser,  @PathVariable(value = "ID") Integer id){
        service.updateUser(oldUser, id);
        return new RedirectView("http://localhost:8080/employee/manageUsers");
    }
    
    
    
    
    
}
