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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<HashMap> CreateEvent(@RequestBody WorkSup wrk){
		HashMap<String, String> jsn = new HashMap<>();
		List<UserRoleRelation> s= new List<UserRoleRelation>() {
			
			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<UserRoleRelation> subList(int fromIndex, int toIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public UserRoleRelation set(int index, UserRoleRelation element) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public UserRoleRelation remove(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public ListIterator<UserRoleRelation> listIterator(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ListIterator<UserRoleRelation> listIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int lastIndexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Iterator<UserRoleRelation> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int indexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public UserRoleRelation get(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean addAll(int index, Collection<? extends UserRoleRelation> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean addAll(Collection<? extends UserRoleRelation> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void add(int index, UserRoleRelation element) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean add(UserRoleRelation e) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		UserRoleRelation userroleR =new UserRoleRelation();
		Supervisor supervisor= new Supervisor();
		supervisor.setWorkshop(wrk.getWork());
		User usr= userRepository.getOne(wrk.getUs());
		userroleR.setActive(true);
		userroleR.setStart(wrk.getWork().getStart());
		userroleR.setEnd(wrk.getWork().getEnd());
		userroleR.setUser(usr);
		userroleR.setWorkshoprole(supervisor);
		s.add(userroleR);
		if(usr.getUserrelation()==null) {
			
			usr.setUserrelation(s);
			userRepository.save(usr);
		}
		else {
			supervisor.setUserrolerelations(s);
			usr.getUserrelation().add(userroleR);
			userRepository.save(usr);
		}
		//userRepository.save(user);
		jsn.put("msg", "workshopcreated successfully");
		workshopRepository.save(wrk.getWork());
		superrep.save(supervisor);
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	
	@PostMapping(path = "/setgroup")
	public ResponseEntity<HashMap> setGroup(@RequestBody WorkShop workshop,int count){
		HashMap<String, String> jsn = new HashMap<>();
		List<WorkshopGroup> groupwork= new List<WorkshopGroup>() {
			
			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<WorkshopGroup> subList(int fromIndex, int toIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public WorkshopGroup set(int index, WorkshopGroup element) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public WorkshopGroup remove(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public ListIterator<WorkshopGroup> listIterator(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ListIterator<WorkshopGroup> listIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int lastIndexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Iterator<WorkshopGroup> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int indexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public WorkshopGroup get(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean addAll(int index, Collection<? extends WorkshopGroup> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean addAll(Collection<? extends WorkshopGroup> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void add(int index, WorkshopGroup element) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean add(WorkshopGroup e) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		WorkshopGroup groupevent= new WorkshopGroup();
		for(int i=0;i<count;++i) {
			groupwork.add(groupevent);
		}
		workshop.setEventGroup(groupwork);
		workshopRepository.save(workshop);
		jsn.put("msg", "done");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	@PostMapping(path="/attendants")
	public ResponseEntity<HashMap> joinWorkShop(@RequestBody WorkShop workshop,@RequestBody Integer id){
		User user;
		HashMap<String, String> jsn = new HashMap<>();
		List<UserRoleRelation> s= new List<UserRoleRelation>() {
			
			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<UserRoleRelation> subList(int fromIndex, int toIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public UserRoleRelation set(int index, UserRoleRelation element) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public UserRoleRelation remove(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public ListIterator<UserRoleRelation> listIterator(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ListIterator<UserRoleRelation> listIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int lastIndexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Iterator<UserRoleRelation> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int indexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public UserRoleRelation get(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean addAll(int index, Collection<? extends UserRoleRelation> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean addAll(Collection<? extends UserRoleRelation> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void add(int index, UserRoleRelation element) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean add(UserRoleRelation e) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		UserRoleRelation userroleR =new UserRoleRelation();
		Attendant student=new Attendant();
		user=userRepository.getOne(id);
		List<WorkshopGroup> team= new List<WorkshopGroup>() {
			
			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<WorkshopGroup> subList(int fromIndex, int toIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public WorkshopGroup set(int index, WorkshopGroup element) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public WorkshopGroup remove(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public ListIterator<WorkshopGroup> listIterator(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ListIterator<WorkshopGroup> listIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int lastIndexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Iterator<WorkshopGroup> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int indexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public WorkshopGroup get(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean addAll(int index, Collection<? extends WorkshopGroup> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean addAll(Collection<? extends WorkshopGroup> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void add(int index, WorkshopGroup element) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean add(WorkshopGroup e) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		for(int i=0;i<workshop.getEventGroup().size();++i) {
			if(workshop.getEventGroup().get(i).getAttendant().size()==10) {
				continue;
			}
			else{
				student.setGroups(workshop.getEventGroup().get(i));
				student.setIsPassed(false);
				student.setScore(0);
				userroleR.setActive(true);
				userroleR.setStart(workshop.getStart());
				userroleR.setEnd(workshop.getEnd());
				userroleR.setUser(user);
				userroleR.setWorkshoprole(student);
				s.add(userroleR);
				if(user.getUserrelation()==null) {
					
					user.setUserrelation(s);
					//userRepository.save(user);
				}
				else {
					student.setUserrolerelations(s);
					user.getUserrelation().add(userroleR);
					break;
					//userRepository.save(user);
				}
				
			}
			
		}
		jsn.put("msg", "workshopcreated successfully");
		workshopRepository.save(workshop);
		attendantRepository.save(student);
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
	}
	@PostMapping(path = "/graderreq")
	public ResponseEntity<HashMap> graderreq(@RequestBody WorkSup eventGrader){
		HashMap<String, String> jsn= new HashMap<>();
		List<User> users= new List<User>() {
			
			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<User> subList(int fromIndex, int toIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public User set(int index, User element) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public User remove(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public ListIterator<User> listIterator(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ListIterator<User> listIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int lastIndexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Iterator<User> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int indexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public User get(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean addAll(int index, Collection<? extends User> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean addAll(Collection<? extends User> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void add(int index, User element) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean add(User e) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		User usr=userRepository.getOne(eventGrader.getUs());
		System.out.println(usr.getAge());
		users.add(usr);
		eventGrader.getWork().getSupervisor().setUsers(users);
		workshopRepository.save(eventGrader.getWork());
		superrep.save(eventGrader.getWork().getSupervisor());
		jsn.put("msg", "done");
		return new ResponseEntity<HashMap>(jsn,HttpStatus.OK);
		
	}
	
}
