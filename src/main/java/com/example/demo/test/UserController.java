package com.example.demo.test;


import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.Null;
@RestController // This means that this class is a Controller
@RequestMapping(path="/Home") // This means URL's start with /demo (after Application path)
public class UserController {
  // This means to get the bean called userRepository
        // Which is auto-generated by Spring, we will use it to handle the data
	@Autowired 
	private UserRepository userRepository;
    private Session session;
  @PostMapping(path="/Registration") // Map ONLY POST Requests
  public ResponseEntity<HashMap>  addNewUser (@RequestBody User user) throws Exception {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST reques
	  HashMap<String, Integer> js= new HashMap<>();
	  HashMap<String, String> js2= new HashMap<>();
	  User us= new User(); 
	  us=userRepository.findByUsername(user.getUsername());
	  if(us!=null){
		  js2.put("id", "user duplicate");
		  return new ResponseEntity(js2,HttpStatus.CONFLICT);
	  }
	  
	  User newUser = userRepository.save(user);
	  
	  js.put("id",newUser.getId());
	  return new ResponseEntity<HashMap>(js,HttpStatus.OK);

  }
  // This returns a JSON or XML with the users
  @PostMapping(path="/Login")
   public ResponseEntity<HashMap> Login(@RequestBody User usr){
	   HashMap<String, Null> jsn= new HashMap<>();
	   HashMap<String,Integer> jsn2= new HashMap<>(); 
	   User user=userRepository.findByUsername(usr.getUsername()) ;
	   if(user==null) {
		   return new ResponseEntity(jsn,HttpStatus.CONFLICT);
	   }
	   if(user.getPassword().equals(usr.getPassword())) {
		   jsn2.put("id", user.getId());
		   return new ResponseEntity<HashMap>(jsn2,HttpStatus.OK); 
	   }
	   return new ResponseEntity(jsn,HttpStatus.CONFLICT);
   }
}
