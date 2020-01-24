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
	public ResponseEntity<List<WorkShop>>  ShowWorkShops(){
		return new ResponseEntity<List<WorkShop>>(workshopRepository.findAll(),HttpStatus.OK);
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
		User usr=new User(); 
		usr=userRepository.getOne(wrk.getUs());
		System.out.println(usr.getUsername());
		userroleR.setActive(true);
		userroleR.setStart(wrk.getWork().getStart());
		userroleR.setEnd(wrk.getWork().getEnd());
		userroleR.setWorkshoprole(supervisor);
		s.add(userroleR);
		if(usr.getUserrelation().isEmpty()) {
			usr.setUserrelation(s);
			wrk.getWork().setSupervisor(supervisor);
			userRepository.save(usr);
			workshopRepository.save(wrk.getWork());
		}
		else {
			usr.getUserrelation().add(userroleR);
			userRepository.save(usr);
			workshopRepository.save(wrk.getWork());
		}
		jsn.put("msg", "workshopcreated successfully");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	
	@PostMapping(path = "/setgroup")
	public ResponseEntity<HashMap> setGroup(@RequestBody WorkShop workshop){
		HashMap<String, String> jsn = new HashMap<>();
		List<WorkshopGroup> groupwork= new ArrayList<WorkshopGroup>();
		WorkshopGroup groupevent= new WorkshopGroup();
		Supervisor supervisor= new Supervisor();
		workshop.setEventGroup(groupwork);
		workshopRepository.save(workshop);
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
				userroleR.setWorkshoprole(student);
				s.add(userroleR);
				if(user.getUserrelation()==null) {
					
					user.setUserrelation(s);
					userRepository.save(user);
					break;
				}
				else {
					user.getUserrelation().add(userroleR);
					userRepository.save(user);
					break;
				}
				
			}
			
		}
		jsn.put("msg", "workshopcreated successfully");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	@PostMapping(path = "/graderreq")
	public ResponseEntity<HashMap> graderreq(@RequestBody WorkSup eventGrader){
		HashMap<String, String> jsn= new HashMap<>();
		List<User> users= new ArrayList<User>();
		User usr=userRepository.getOne(eventGrader.getUs());
		Supervisor supervisor= new Supervisor();
		users.add(usr);
		if(eventGrader.getWork().getSupervisor()==null) {
			eventGrader.getWork().setSupervisor(supervisor);
			eventGrader.getWork().getSupervisor().setUsers(users);
			workshopRepository.save(eventGrader.getWork());
		}
		else {
			eventGrader.getWork().getSupervisor().getUsers().add(usr);
			workshopRepository.save(eventGrader.getWork());
		}
		
		jsn.put("msg", "done");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
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
		userRole.setWorkshoprole(master);
		role.add(userRole);
		if(usr.getUserrelation().isEmpty()) {
			usr.setUserrelation(role);
			grader.getEvent().getEventGroup().get(grader.getIndx()).getGraders().add(master);
			userRepository.save(usr);
		}
		else {
			usr.getUserrelation().add(userRole);
			userRepository.save(usr);
		}
		jsn.put("msg", "workshopcreated successfully");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	 
	@PostMapping(name = "/role")
	public ResponseEntity<HashMap> role(@RequestBody WorkSup role){
		HashMap<String, String> jsn= new HashMap<>();
		User newuser= userRepository.getOne(role.getUs());
		for(int i=0;i<newuser.getUserrelation().size();++i) {
			if(newuser.getUserrelation().get(i).isActive()==false) {
				continue;
			}
			else {
				if(role.getWork().getSupervisor().getiD()==newuser.getUserrelation().get(i).getWorkshoprole().getiD()) {
					jsn.put("msg", "supervisor");
					return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
				}
				else {
					break;
				}
			}
		}
		for(int i=0;i<newuser.getUserrelation().size();++i) {
			if(newuser.getUserrelation().get(i).isActive()==false) {
				continue;
			}
			else {
				for(int n=0;n<role.getWork().getEventGroup().size();++n) {
					for(int p=0;p<role.getWork().getEventGroup().get(n).getAttendant().size();++p) {
						if(role.getWork().getEventGroup().get(n).getAttendant().get(p).getiD()==newuser.getUserrelation().get(i).getWorkshoprole().getiD()) {
							jsn.put("msg", "attendant");
							return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
						}
						else {
							continue;
						}
					}
				}
			}
		}
		
		for(int i=0;i<newuser.getUserrelation().size();++i) {
			if(newuser.getUserrelation().get(i).isActive()==false) {
				continue;
			}
			else {
				for(int n=0;n<role.getWork().getEventGroup().size();++n) {
					for(int p=0;p<role.getWork().getEventGroup().get(n).getGraders().size();++p) {
						if(role.getWork().getEventGroup().get(n).getGraders().get(p).getiD()==newuser.getUserrelation().get(i).getWorkshoprole().getiD()) {
							jsn.put("msg", "grader");
							return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
						}
						else {
							continue;
						}
					}
				}
			}
		}
		
		
		jsn.put("msg", "workshopcreated successfully");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
	}
}
