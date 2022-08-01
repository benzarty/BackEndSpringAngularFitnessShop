package tn.esprit.spring.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import tn.esprit.spring.Repository.RoleDao;
import tn.esprit.spring.Repository.UserDao;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JavaMailSender mailSender;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("aaaa"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setUserName("benz");
        user.setUserPassword(getEncodedPassword("benz"));
        user.setUserFirstName("aaaa");
        user.setUserLastName("sharma");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setActive(false);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
    public List<User> retrieveAllUsers() {
		 List<User> users=(List<User>)userDao.findAll() ;
		 return users;
	}
    
    public User addUser(User r) {
    	 Role role = roleDao.findById("User").get();
         Set<Role> userRoles = new HashSet<>();
         userRoles.add(role);
         r.setRole(userRoles);
		return userDao.save(r);
	}

	
	public User retrieveUser(String id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).orElse(null);
	}
	
	
	public void deleteUser(String id) {
		
		
		User u=userDao.findById(id).orElse(null)	;
		
		u.getRole().clear();
		
		userDao.save(u);
		userDao.deleteById(id);
		
		
		}

	
	public User updateUser(User c) {
		
		User u=userDao.findById(c.getUserName()).orElse(null);
		
		u.setUserFirstName(c.getUserFirstName());
		u.setUserLastName(c.getUserLastName());
u.setEmail(c.getEmail());
u.setPhone(c.getPhone());
u.setActive(c.isActive());


return userDao.save(u);
		
		
	}
	
	
	
public void sendSimpleEmail(User r) {
	
	User u=userDao.findByEmail(r.getEmail());
	
				
	int random = (int)(Math.random() * 9000000 + 1);
	
	String s=String.valueOf(random);

	
    u.setUserPassword(getEncodedPassword(s));

    
    userDao.save(u);

	System.out.println(u);
SimpleMailMessage message = new SimpleMailMessage();
message.setFrom("m.benzarti.1996@gmail.com");
message.setTo(u.getEmail());
message.setText("Your new Password is "+s);
message.setSubject("Password Change");
mailSender.send(message);


}
    
}
