package com.example.demo.test;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
	
	@Autowired
	WorkShopGroupRepository WSGRepoistory;
	
	@Autowired
	AttendantRepository attendantRepository;
	
	@Autowired
	GraderRepository graderRepository;
	
	@Autowired
	UserRoleRelationRepository relationRepository;
	
	Session session;
	@GetMapping(path="/workshophome")
	public ResponseEntity<Iterable<WorkShop>>  ShowWorkShops(){
		return new ResponseEntity<Iterable<WorkShop>>(workshopRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(path= "/users")
	public @ResponseBody Iterable<User> ShowUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping(path="/creation")
	public ResponseEntity<HashMap> CreateEvent(@RequestBody WorkSup wrk){
		HashMap<String, String> jsn = new HashMap<>();
		List<UserRoleRelation> s= new ArrayList<UserRoleRelation>(); 
		UserRoleRelation userroleR =new UserRoleRelation();
		Supervisor supervisor= new Supervisor();
		supervisor.setWorkshop(wrk.getWork());
		User usr=new User(); 
		usr=userRepository.getOne(wrk.getUs());
		System.out.println(usr.getUsername());
		userroleR.setActive(true);
		userroleR.setStart(wrk.getWork().getStart());
		userroleR.setEnd(wrk.getWork().getEnd());
		userroleR.setUser(usr);
		userroleR.setWorkshoprole(supervisor);
		s.add(userroleR);
		if(usr.getUserrelation().isEmpty()) {
			
			usr.setUserrelation(s);
			wrk.getWork().setSupervisor(supervisor);
			userRepository.save(usr);
		}
		else {
			supervisor.setUserrolerelations(s);
			usr.getUserrelation().add(userroleR);
			userRepository.save(usr);
		}
		jsn.put("msg", "workshopcreated successfully");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	
	@PostMapping(path = "/setgroup")
	public ResponseEntity<HashMap> setGroup(@RequestBody WorkSup workshop){
		HashMap<String, String> jsn = new HashMap<>();
		List<WorkshopGroup> groupwork= new ArrayList<WorkshopGroup>();
		WorkshopGroup groupevent= new WorkshopGroup();
		for(int i=0;i<workshop.getUs();++i) {
			groupwork.add(groupevent);
		}
		workshop.getWork().setEventGroup(groupwork);
		workshopRepository.save(workshop.getWork());
		jsn.put("msg", "done");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	@PostMapping(path="/attendants")
	public ResponseEntity<HashMap> joinWorkShop(@RequestBody WorkSup workshop){
		User user=new User();
		HashMap<String, String> jsn = new HashMap<>();
		List<UserRoleRelation> s= new ArrayList<UserRoleRelation>(); 
		UserRoleRelation userroleR =new UserRoleRelation();
		Attendant student=new Attendant();
		user=userRepository.getOne(workshop.getUs());
		List<WorkshopGroup> team= new ArrayList<WorkshopGroup>();
		if(workshop.getWork().getEventGroup()==null) {
			
			jsn.put("msg", "its empty");
			return new  ResponseEntity<HashMap>(jsn,HttpStatus.CONFLICT);
		}
		for(int i=0;i<workshop.getWork().getEventGroup().size();++i) {
			if(workshop.getWork().getEventGroup().get(i).getAttendant().size()==10) {
				continue;
			}
			else{
				student.setGroups(workshop.getWork().getEventGroup().get(i));
				student.setIsPassed(false);
				student.setScore(0);
				userroleR.setActive(true);
				userroleR.setStart(workshop.getWork().getStart());
				userroleR.setEnd(workshop.getWork().getEnd());
				userroleR.setUser(user);
				userroleR.setWorkshoprole(student);
				s.add(userroleR);
				if(user.getUserrelation()==null) {
					
					user.setUserrelation(s);
					userRepository.save(user);
					break;
				}
				else {
					student.setUserrolerelations(s);
					user.getUserrelation().add(userroleR);
					userRepository.save(user);
					break;
				}
				
			}
			
		}
		jsn.put("msg", "workshopcreated successfully");
		workshopRepository.save(workshop.getWork());
		attendantRepository.save(student);
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	@PostMapping(path = "/graderreq")
	public ResponseEntity<HashMap> graderreq(@RequestBody WorkSup eventGrader){
		HashMap<String, String> jsn= new HashMap<>();
		List<User> users= new ArrayList<User>();
		User usr=userRepository.getOne(eventGrader.getUs());
		users.add(usr);
		eventGrader.getWork().getSupervisor().setUsers(users);
		workshopRepository.save(eventGrader.getWork());
		jsn.put("msg", "done");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
	}
	@GetMapping(name = "/setgrader")
	public ResponseEntity<List<User>>showGrader(@RequestBody WorkShop work ){
		return new ResponseEntity<List<User>>(work.getSupervisor().getUsers(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/grader",method = RequestMethod.POST)
	public ResponseEntity<HashMap> setgrader(@RequestBody WorkGroupUSer grader){
		User usr=userRepository.getOne(grader.getId());
		HashMap<String, String> jsn= new HashMap<>();
		List<Grader> teacher= new ArrayList<Grader>();
		List<UserRoleRelation> role= new ArrayList<UserRoleRelation>();
		UserRoleRelation userRole= new UserRoleRelation();
		Grader master= new Grader();
		master.setGroup(grader.getEvent().getEventGroup().get(grader.getIndx()));
		userRole.setActive(true);
		userRole.setStart(grader.getEvent().getStart());
		userRole.setEnd(grader.getEvent().getEnd());
		userRole.setUser(usr);
		userRole.setWorkshoprole(master);
		role.add(userRole);
		if(usr.getUserrelation().isEmpty()) {
			usr.setUserrelation(role);
			grader.getEvent().getEventGroup().get(grader.getIndx()).getGraders().add(master);
			userRepository.save(usr);
		}
		else {
			master.setUserrolerelations(role);
			usr.getUserrelation().add(userRole);
			userRepository.save(usr);
		}
		jsn.put("msg", "workshopcreated successfully");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	 
	@PostMapping(name = "/role")
	public ResponseEntity<HashMap> role(@RequestBody WorkSup role){
		HashMap<String, String> jsn= new HashMap<>();
		User us= new User();
		us=userRepository.getOne(role.getUs());
		for(int i=0 ;i<us.getUserrelation().size();++i) {
			if(us.getUserrelation().get(i).getWorkshoprole().equals(role.getWork().getSupervisor())) {
				jsn.put("msg", "this guy is supervisor");
				return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
			}
			else {
				continue;
			}
		}
		
		for(int i=0;i<us.getUserrelation().size();++i) {
			for(int n=0;n<role.getWork().getEventGroup().size();++n) {
				for(int p=0;p<role.getWork().getEventGroup().get(n).getAttendant().size();++p) {
					if(us.getUserrelation().get(i).getWorkshoprole().equals(role.getWork().getEventGroup().get(n).getAttendant().get(p))) {
						jsn.put("msg", "this guy is attendant");
						return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
					}
					else {
						continue;
					}
				}
			}
		}
		for(int i=0;i<us.getUserrelation().size();++i) {
			for(int n=0;n<role.getWork().getEventGroup().size();++n) {
				if(us.getUserrelation().get(i).getWorkshoprole().equals(role.getWork().getEventGroup().get(n).getGraders())) {
					jsn.put("msg", "this guy is attendant");
					return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
				}
				else {
					continue;
				}
			}
		}
		jsn.put("msg", "this guy is nothing");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
		
	}
	
}
