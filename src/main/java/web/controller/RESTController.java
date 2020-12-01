package web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
public class RESTController {

    private final UserService userService;
    private final RoleService roleService;

    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping("/user")
    public ModelAndView user(ModelAndView modelAndView) {
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping("/admin")
    public ModelAndView admin(ModelAndView modelAndView) {
        List<Role> roles = roleService.allRoles();
        modelAndView.setViewName("admin");
        modelAndView.addObject("rolesList", roles);
        return modelAndView;
    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/getUser")
    public User getUser(Principal principal) {
        return userService.getUserByName(principal.getName());
    }

    @GetMapping("/allUsers")
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody User user) {
        userService.edit(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
    }

    @GetMapping("/getUser/{id}")
    public User getById(@PathVariable(name = "id") int id) {
        return userService.getById(id);
    }

    @GetMapping("/allRoles")
    public List<Role> allRoles() {
        return roleService.allRoles();
    }

}
