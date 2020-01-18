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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping(path="/workshop")
public class WorkShopController {
	@Autowired
	WorkShopRepository workshopRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SupervisorRepository superrep;
	
	@Autowired
	UserRoleRepository userrolerep;
	
	Session session;
	@GetMapping(path="/workshophome")
	public ResponseEntity<Iterable<WorkShop>>  ShowWorkShops(){
		return new ResponseEntity<Iterable<WorkShop>>(workshopRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path= "/users")
	public ResponseEntity<Iterable<User>> ShowUsers(){
		return new ResponseEntity<Iterable<User>>(userRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(path="/creation")
	public ResponseEntity<HashMap> CreateEvent(@RequestBody WorkShop workshop,@RequestBody User user){
		HashMap<String, String> jsn = new HashMap<>();
		UserRoleRelation userroleR =new UserRoleRelation();
		Supervisor supervisor= new Supervisor();
		supervisor.setWorkshop(workshop);
		User usr= new User();
		userroleR.setActive(true);
		userroleR.setStart(workshop.getStart());
		userroleR.setEnd(workshop.getEnd());
		userroleR.setUser(user);
		userroleR.setWorkshoprole(supervisor);
		user.getUserrelation().add(userroleR);
		supervisor.setUserrolerelations(user.getUserrelation());
		userRepository.save(user);
		jsn.put("msg", "workshopcreated successfully");
		usr=userRepository.findByUsername(user.getUsername());
		workshopRepository.save(workshop);
		superrep.save(supervisor);
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	
}
