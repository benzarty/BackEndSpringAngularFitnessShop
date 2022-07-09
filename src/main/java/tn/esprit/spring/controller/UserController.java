package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.services.UserService;

import java.util.List;

import javax.annotation.PostConstruct;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
   // @PreAuthorize("hasAnyRole('User','Admin')")

    public String forUser(){
        return "This URL is only accessible to the user";
    }
    
	@GetMapping("/retrieve-all-users")
    @PreAuthorize("hasRole('Admin')")
	@ResponseBody
	public List<User>getUsers() {
		return userService.retrieveAllUsers();
	}
	
	@PostMapping("/add-user")
    @PreAuthorize("hasRole('Admin')")
    @ResponseBody
	public User addUser(@RequestBody User r)
	{
		return userService.addUser(r);
	}
	
	@GetMapping("/retrieve-user/{user-id}")
	@ResponseBody
    @PreAuthorize("hasRole('Admin')")
  public User retrieveUser(@PathVariable("user-id") String userId) {
		return userService.retrieveUser(userId);
	}
	


	@DeleteMapping("/remove-user/{user-id}")
	@ResponseBody
    @PreAuthorize("hasRole('Admin')")
public void removeUser(@PathVariable("user-id") String userId) {
		userService.deleteUser(userId);
	}

	@PutMapping("/modify-user")
	@ResponseBody
    @PreAuthorize("hasRole('Admin')")
    public User modifyuser(@RequestBody User user) {
		return userService.updateUser(user);
	}
    
}
