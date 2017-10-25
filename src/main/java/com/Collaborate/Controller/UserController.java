package com.Collaborate.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.collaborate.DAO.UserDao;
import com.collaborate.Model.User;
import com.collaborate.Model.Error;

@RestController
public class UserController 
{
    @Autowired
    
    private UserDao usersDao;
    
    @RequestMapping(value="/registration",method=RequestMethod.POST)
    
   
    
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getFirstname());
 
 
        usersDao.registration(user);
 
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User users,HttpSession session)
	{ 
	    System.out.println("Is Session New For" + users.getUsername() + session.isNew());
	    User validUser=usersDao.login(users);
	    if(validUser==null)

	    {
		    Error error=new Error(3,"Invalid username and password.. please enter valid credentials");
		    return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	   
	    else	
	    {
		    validUser.setOnline(true);
		    validUser=usersDao.updateUser(validUser);
		    session.setAttribute("user", validUser);
		    return new ResponseEntity<User>(validUser,HttpStatus.OK);    
		}
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?>logout(HttpSession session)
	{    
	    User users=(User)session.getAttribute("user");
	    if(users==null)
	    {
	        Error error=new Error(3,"Unauthorized user");
	        return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); 
	    }
	    System.out.println("Is Session New for" + users.getUsername() + session.isNew());
	    users.setOnline(false);
	    usersDao.updateUser(users);
	    session.removeAttribute("user");
	    session.invalidate();
	    return new ResponseEntity<Void>(HttpStatus.OK);
	    
	}
	
	@RequestMapping(value="/getuserdetails",method=RequestMethod.GET)
	public ResponseEntity<?> getUserDetails(HttpSession session)
	{    
	    User users=(User)session.getAttribute("user");
	    if(users==null)
	    {
	        Error error=new Error(3,"Unauthorized user");
	        return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); 
	    }
	    users=usersDao.getUserByUsername(users.getId());
	    return new ResponseEntity<User>(users,HttpStatus.OK);
	}
	
@RequestMapping(value="/updateprofile",method=RequestMethod.PUT)
public ResponseEntity<?> updateUserProfile(@RequestBody User user,HttpSession session)
{    
    User users=(User)session.getAttribute("user");
    if(users==null)
    {
        Error error=new Error(3,"Unauthorized user");
        return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); 
    }
    usersDao.updateUser(user);
    session.setAttribute("user", user);
    return new ResponseEntity<Void>(HttpStatus.OK);
}
}