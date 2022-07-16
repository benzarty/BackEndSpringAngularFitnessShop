package tn.esprit.spring.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.Repository.RoleDao;
import tn.esprit.spring.Repository.UserDao;
import tn.esprit.spring.domaine.Response;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.services.UserService;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

@RestController

public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDao userDao;
    
    
    @Autowired  
    ServletContext context;
    
    @Autowired
    private RoleDao roleDao;
    

    @Autowired
    private PasswordEncoder passwordEncoder;

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
	
	
	
	@PostMapping("/file")
	public Boolean ProfilUpdate(@RequestParam("file") MultipartFile file,
			@RequestParam("user") String user)
			throws JsonParseException, JsonMappingException, Exception {
		
		


			User arti = new ObjectMapper().readValue(user, User.class);
		boolean isExit = new File(context.getRealPath("/Images/")).exists();
		if (!isExit) {
			new File(context.getRealPath("/Images/")).mkdir();
			System.out.println("mk dir.............");
		}
		String filename = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
		File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
		try {
       	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}

		arti.setFileName(newFileName);
		
        arti.setUserPassword(getEncodedPassword(arti.getUserPassword()));
        
        User uu=userDao.findById(arti.getUserName()).orElse(null);
        
        if(getMatches(arti.getOldpassword(),uu.getUserPassword()))
        
        { 
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        arti.setRole(userRoles);
	


		User art = userDao.save(arti);   
return true;
	
		
        }
        
return false;
	}
	
	
	  public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }
	
	
	  public Boolean getMatches(String password,String v) {
	        return passwordEncoder.matches(password,v);
	    }
	
	
	
	
	  @PostMapping("/file2")
		public Boolean RegisterNewUser(@RequestParam("file") MultipartFile file,
				@RequestParam("user") String user)
				throws JsonParseException, JsonMappingException, Exception {
			
			


				User arti = new ObjectMapper().readValue(user, User.class);
			boolean isExit = new File(context.getRealPath("/Images/")).exists();
			if (!isExit) {
				new File(context.getRealPath("/Images/")).mkdir();
				System.out.println("mk dir.............");
			}
			String filename = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
			File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
			try {
	       	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

			} catch (Exception e) {
				e.printStackTrace();
			}

			arti.setFileName(newFileName);
			
	        arti.setUserPassword(getEncodedPassword(arti.getUserPassword()));
	        
	        User uu=userDao.findById(arti.getUserName()).orElse(null);
	        
	        
	        {
	        Role role = roleDao.findById("User").get();
	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(role);
	        arti.setRole(userRoles);
		


			User art = userDao.save(arti);   
	return true;
		
			
	        
	        
		}
	
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
}
