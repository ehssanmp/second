package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(path = "/rel")
public class URRController {
	 @Autowired
	private UserRoleRelationRepository userrolerelationRepository;
	private WorkshopRoleRepository workshoprolerep;
	private GraderRepository graderrepository;
	private AttendantRepository attendantrepository;
	private SupervisorRepository supervisorreppository;
	@PostMapping(path="/AddUserRole")
	public @ResponseBody String AddNewRel(@RequestBody UserRoleRelation user1) {
		userrolerelationRepository.save(user1);
		return "savedddd";
	}
	@PostMapping(path = "/addWorkshopRole")
	public @ResponseBody String AddWorkshopRole(@RequestBody WorkshopRole role) {
		workshoprolerep.save(role);
		return "role is saved";
	}
	@PostMapping(path = "/grader")
	public @ResponseBody String AddGrader(@RequestBody Grader grader) {
		graderrepository.save(grader);
		return "saved";
	}
	@PostMapping(path = "/supervisor")
	public @ResponseBody String AddSupervisor(@RequestBody Supervisor supervisor) {
		supervisorreppository.save(supervisor);
		return "saved";
	}
	@PostMapping(path = "/attendant")
	public @ResponseBody String AddAttendant(@RequestBody Attendant attendant) {
		attendantrepository.save(attendant);
		return "saved";
	}

}
