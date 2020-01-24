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
	WorkshopRoleRepository workrole;
	
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
	public ResponseEntity<HashMap> setGroup(@RequestBody Ids id){
		HashMap<String, String> jsn = new HashMap<>();
		WorkShop workshop=workshopRepository.getOne(id.getWid());
		List<WorkshopGroup> groupwork= new ArrayList<WorkshopGroup>();
		Supervisor supervisor= new Supervisor();
		for(int i=0;i<id.getGid();++i) {
			WorkshopGroup groupevent= new WorkshopGroup();
			groupevent.setWorkshop(workshop);
			WSGRepoistory.save(groupevent);
			
		}
		jsn.put("msg", "done");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	@PostMapping(path="/attendants")
	public ResponseEntity<HashMap> joinWorkShop(@RequestBody NumbersIds id){
		User user=userRepository.getOne(id.getId2());
		int i;
		WorkShop workshop= workshopRepository.getOne(id.getId1());
		HashMap<String, String> jsn = new HashMap<>();
		List<UserRoleRelation> s= new ArrayList<UserRoleRelation>(); 
		Attendant Student= new Attendant();
		Student.setAbsences(0);
		Student.setIsPassed(false);
		Student.setScore(0);
		UserRoleRelation userroleR =new UserRoleRelation();
		List<Attendant> student=attendantRepository.findAll();
		List<WorkshopGroup> team=WSGRepoistory.findAll();
		for(i=0;i<team.size();++i) {
			if(team.get(i).getWorkshop().getId()==workshop.getId()) {
				if(team.get(i).getId()==id.getId3()) {
					userroleR.setActive(true);
					userroleR.setStart(workshop.getStart());
					userroleR.setEnd(workshop.getEnd());
					Student.setGroups(team.get(i));
					userroleR.setWorkshoprole(Student);
					s.add(userroleR);
					if(user.getUserrelation()==null) {
						user.setUserrelation(s);
						userRepository.save(user);
					}
					else {
						user.getUserrelation().add(userroleR);
						userRepository.save(user);
					}
				}
			}
		}
		if(i==team.size()) {
			jsn.put("msg", "workshopcreated successfully");
			return new ResponseEntity<HashMap>(jsn,HttpStatus.NO_CONTENT);
		}
	
		jsn.put("msg", "workshopcreated successfully");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	@PostMapping(path = "/graderreq")
	public ResponseEntity<HashMap> graderreq(@RequestBody Ids eventGrader){
		HashMap<String, String> jsn= new HashMap<>();
		List<String> names= new ArrayList<String>();
		List<Integer> var= new ArrayList<Integer>();
		User usr=userRepository.getOne(eventGrader.getGid());
		WorkShop work=workshopRepository.getOne(eventGrader.getWid());
		String name;
		Integer num;
		name=usr.getName();
		num=usr.getId();
		names.add(name);
		var.add(num);
		Supervisor supervisor= new Supervisor();
		if(work.getSupervisor()==null) {
			work.setSupervisor(supervisor);
			work.getSupervisor().setNames(names);
			work.getSupervisor().setIds(var);
			workshopRepository.save(work);
		}
		else {
			work.getSupervisor().getNames().add(name);
			work.getSupervisor().getIds().add(num);
			workshopRepository.save(work);
		}
		
		jsn.put("msg", "done");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
	}
	
	
	@PostMapping(value = "/show")
	public ResponseEntity<HashMap> show(@RequestBody Numbers id){
		HashMap<String, String> jsn= new HashMap<>();
		WorkShop work=workshopRepository.getOne(id.getId());
		List<WorkshopGroup> group= WSGRepoistory.findAll();
		List<User> users= userRepository.findAll();
		List<Attendant> students= attendantRepository.findAll();
		List<User> people=userRepository.findAll();
		List<Grader> graders=graderRepository.findAll();
		for(int i=0;i<people.size();++i) {
			for(int b=0;b<people.get(i).getUserrelation().size();++b) {
				if(people.get(i).getUserrelation().get(b).getWorkshoprole().getiD()==work.getSupervisor().getiD()) {
					jsn.put("supervisor:", people.get(i).getName());
				}
			}
		}
		for(int i=0;i<group.size();++i) {
			if(group.get(i).getWorkshop().getId()==id.getId()) {
				for(int j=0;j<users.size();++j) {
					for(int k=0;k<users.get(j).getUserrelation().size();++k) {
						for(int b=0;b<students.size();++b) {
							if(users.get(j).getUserrelation().get(k).getiD()==students.get(b).getiD()) {
								if(group.get(i).getId()==students.get(b).getGroups().getId()){
									jsn.put("Group:", group.get(i).toString());
									jsn.put("Attendants", users.get(j).getName());
								}
							}
						}
					}
				}
			}
		}
		for(int i=0;i<group.size();++i) {
			if(group.get(i).getWorkshop().getId()==id.getId()) {
				for(int j=0;j<users.size();++j) {
					for(int k=0;k<users.get(j).getUserrelation().size();++k) {
						for(int b=0;b<graders.size();++b) {
							if(users.get(j).getUserrelation().get(k).getiD()==graders.get(b).getiD()) {
								if(group.get(i).getId()==graders.get(b).getGroup().getId()){
									jsn.put("Group:", group.get(i).toString());
									jsn.put("Graders", users.get(j).getName());
								}
							}
						}
					}
				}
			}
		}
		
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
	}
	@PostMapping(value = "/showgrader")
	public ResponseEntity<List<User>> showgrader(@RequestBody Numbers id){
		List<User> users= new ArrayList<User>();
		WorkShop work=workshopRepository.getOne(id.getId());
		int a=work.getSupervisor().getIds().get(0);
		User user= userRepository.getOne(a);
		System.out.println(user.getName());
		//for(int i=0;i<work.getSupervisor().getIds().size();++i) {
			//User user= userRepository.getOne(4);
			//users.add(user);
		//}
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	@RequestMapping(value = "/grader",method = RequestMethod.POST)
	public ResponseEntity<HashMap> setgrader(@RequestBody NumbersIds id){
		WorkShop event= workshopRepository.getOne(id.getId1());
		int i;
		User user= userRepository.getOne(id.getId2());
		HashMap<String, String> jsn= new HashMap<>();
		List<WorkshopGroup> group= new ArrayList<>();
		WorkshopGroup gp= new WorkshopGroup();
		UserRoleRelation userRole= new UserRoleRelation();
		List<Grader> teacher= new ArrayList<Grader>();
		List<UserRoleRelation> role= new ArrayList<UserRoleRelation>();
		List<WorkshopGroup> team=WSGRepoistory.findAll();
		Grader master= new Grader();
		for(i=0;i<team.size();++i) {
			if(team.get(i).getWorkshop().getId()==event.getId()) {
				if(team.get(i).getId()==id.getId3()) {
					master.setGroup(team.get(i));
					userRole.setActive(true);
					userRole.setStart(event.getStart());
					userRole.setEnd(event.getEnd());
					userRole.setWorkshoprole(master);
					role.add(userRole);
					if(user.getUserrelation()==null) {
						user.setUserrelation(role);
						userRepository.save(user);
					}
					else {
						user.getUserrelation().add(userRole);
						userRepository.save(user);
					}
					
				}
			}
		}
		if(i==team.size()) {
			jsn.put("msg", "workshopcreated successfully");
			return new ResponseEntity<HashMap>(jsn,HttpStatus.NO_CONTENT);
		}
		jsn.put("msg", "workshopcreated successfully");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
	}
	 
	@PostMapping(name = "/role")
	public ResponseEntity<HashMap> role(@RequestBody Ids id){
	
		HashMap<String, String> jsn= new HashMap<>();
		WorkShop work=workshopRepository.getOne(id.getWid());
		List<WorkshopGroup> group= WSGRepoistory.findAll();
		User users= userRepository.getOne(id.getGid());
		List<Attendant> students= attendantRepository.findAll();
		List<User> people=userRepository.findAll();
		List<Grader> graders=graderRepository.findAll();
		for(int i=0;i<people.size();++i) {
			for(int b=0;b<people.get(i).getUserrelation().size();++b) {
				if(people.get(i).getUserrelation().get(b).getWorkshoprole().getiD()==work.getSupervisor().getiD()) {
					return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
				}
			}
		}
		for(int i=0;i<group.size();++i) {
			if(group.get(i).getWorkshop().getId()==id.getWid()) {
					for(int k=0;k<users.getUserrelation().size();++k) {
						for(int b=0;b<students.size();++b) {
							if(users.getUserrelation().get(k).getiD()==students.get(b).getiD()) {
								if(group.get(i).getId()==students.get(b).getGroups().getId()){
									return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
								}
							}
						}
					}
				}
		}
		for(int i=0;i<group.size();++i) {
			if(group.get(i).getWorkshop().getId()==id.getWid()) {
					for(int k=0;k<users.getUserrelation().size();++k) {
						for(int b=0;b<students.size();++b) {
							if(users.getUserrelation().get(k).getiD()==graders.get(b).getiD()) {
								if(group.get(i).getId()==graders.get(b).getGroup().getId()){
									return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
								}
							}
						}
					}
				}
		}
		jsn.put("msg", "this user has no role in this workshop");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
	}
}
