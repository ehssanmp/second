package com.example.demo.test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/form")
public class FormController {

	@Autowired
	GroupFormRepository groupformrep ;
	
	@Autowired
	GraderFormRepository graderformrep;
	
	@Autowired
	AttendantFormRepository attendantformrep;
	
	@Autowired
	WorkShopGroupRepository grouprep;
	
	@Autowired
	AttendantRepository attendantrep;
	
	@Autowired
	GraderRepository graderrep;
	
	@Autowired
	GroupFormAnswerSheetRepository groupformanswersheetrep;
	
	@Autowired
	GraderFormAnswerSheetRepository graderformanswersheetrep;
	
	@Autowired
	AttendantFormAnswerSheetRepository attendantformanswersheetrep;
	
	@Autowired
	WorkShopRepository workshoprep;
	
	@RequestMapping(path = "/test")
	public ResponseEntity<Object> show(@RequestBody Date date){

		return new ResponseEntity<Object>(date,HttpStatus.OK);
	}
	
	@PostMapping(path = "/groupformCreation")
	public ResponseEntity<HashMap<String , String>> creatinggroupForm(@RequestBody GroupForm form){ 
		HashMap<String , String> msg = new HashMap<>();
		msg.put("message", "Form created successfully");
		if(form.getEvent().getForm() != null) {
			form.getEvent().getForm().add(form);
		}
		else {
			List<Form> formlist = new ArrayList<Form>();
			formlist.add(form);
			form.getEvent().setForm(formlist);
		}
		groupformrep.save(form);
		workshoprep.save(form.getEvent());
		return new ResponseEntity<HashMap<String ,String>>(msg ,HttpStatus.OK);
	}
	
	@PostMapping(path = "/attendformCreation")
	public ResponseEntity<HashMap<String , String>> creatingattendForm(@RequestBody AttendForm form){ 
		HashMap<String , String> msg = new HashMap<>();
		msg.put("message", "Form created successfully");
		if(form.getEvent().getForm() != null) {
			form.getEvent().getForm().add(form);
		}
		else {
			List<Form> formlist = new ArrayList<Form>();
			formlist.add(form);
			form.getEvent().setForm(formlist);
		}
		attendantformrep.save(form);
		workshoprep.save(form.getEvent());
		return new ResponseEntity<HashMap<String ,String>>(msg ,HttpStatus.OK);
	}
	
	@PostMapping(path = "/graderformCreation")
	public ResponseEntity<HashMap<String , String>> creatinggraderForm(@RequestBody GraderForm form){ 
		HashMap<String , String> msg = new HashMap<>();
		msg.put("message", "Form created successfully");
		if(form.getEvent().getForm() != null) {
			form.getEvent().getForm().add(form);
		}
		else {
			List<Form> formlist = new ArrayList<Form>();
			formlist.add(form);
			form.getEvent().setForm(formlist);
		}
		graderformrep.save(form);
		workshoprep.save(form.getEvent());
		return new ResponseEntity<HashMap<String ,String>>(msg ,HttpStatus.OK);
	}
	
	
	
	@GetMapping(path = "/showingGroups")
	public ResponseEntity<List<WorkshopGroup>> shoingGroups (@RequestBody WorkShop workshop){
		return new ResponseEntity<List<WorkshopGroup>>(workshop.getEventGroup(),HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/showingAttendants")
	public ResponseEntity<List<Attendant>> shoingAttendant (@RequestBody WorkshopGroup group){
		return new ResponseEntity<List<Attendant>>(group.getAttendant(),HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/showingGrader")
	public ResponseEntity<List<Grader>> shoingGrader (@RequestBody WorkshopGroup group){
		return new ResponseEntity<List<Grader>>(group.getGraders(),HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/showingGraderAnswersheet")
	public ResponseEntity<Iterable<GraderFormAnswerSheet>> showingGraderanswersheet (@RequestBody Grader grader){
		return new ResponseEntity<Iterable<GraderFormAnswerSheet>>(grader.getGraderformanswersheet(),HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/showingGroupAnswersheet")
	public ResponseEntity<List<GroupFormAnswerSheet>> shoingGroupanswersheet (@RequestBody WorkshopGroup group){
		return new ResponseEntity<List<GroupFormAnswerSheet>>(group.getGroupformanswersheet(),HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/showingAttendantAnswersheet")
	public ResponseEntity<Iterable<AttendFormAnswerSheet>> shoingGraderanswersheet (@RequestBody Attendant attend){
		return new ResponseEntity<Iterable<AttendFormAnswerSheet>>(attend.getAttendformanswersheet(),HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/gettingGroupformQuestions")
	public ResponseEntity<GroupForm> getgroupform(@RequestBody Integer groupId){
		WorkshopGroup group = grouprep.getOne(groupId);
		for(GroupForm groupform: groupformrep.findAll()) {
			for(WorkshopGroup groupTemp: groupform.getEvent().getEventGroup()) {
				if(groupTemp.equals(group)){
					return new ResponseEntity<GroupForm>(groupform,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<GroupForm>(HttpStatus.CONFLICT);
	}
	
	@GetMapping(path = "/gettingAttendantformQuestions")
	public ResponseEntity<AttendForm> getattendantform(@RequestBody Integer attendantId){
		Attendant student = attendantrep.getOne(attendantId);
		for(AttendForm studentform : attendantformrep.findAll()) {
			for(WorkshopGroup groupTemp: studentform.getEvent().getEventGroup()) {
				for(Attendant studentTemp : groupTemp.getAttendant()) {
					if(studentTemp.equals(student)) {
						return new ResponseEntity<AttendForm>(studentform,HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<AttendForm>(HttpStatus.CONFLICT);
	}
	
	@GetMapping(path = "/gettingGraderformQuestions")
	public ResponseEntity<GraderForm> getgraderform(@RequestBody Integer graderId){
		Grader grader = graderrep.getOne(graderId);
		for(GraderForm graderform : graderformrep.findAll()) {
			for(WorkshopGroup groupTemp: graderform.getEvent().getEventGroup()) {
				for(Grader graderTemp : groupTemp.getGraders()) {
					if(graderTemp.equals(grader)) {
						return new ResponseEntity<GraderForm>(graderform,HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<GraderForm>(HttpStatus.CONFLICT);
	}

	@PostMapping(path = "/answeringGroupForm")
	public ResponseEntity<HashMap<String, String>> savinggroupanswers(@RequestBody AnsweringGroupFormParameters answerandtheform){
		WorkshopGroup group = answerandtheform.getGroup();
		GroupFormAnswerSheet answersheet = answerandtheform.getGroupformanswrs();
		group.getGroupformanswersheet().add(answersheet);
		answersheet.setWorkshopgroup(group);
		grouprep.save(group);
		groupformanswersheetrep.save(answersheet);
		HashMap<String , String> js1 = new HashMap<>();
		js1.put("message", "answers are saved");
		return new ResponseEntity<HashMap<String,String>>(js1,HttpStatus.OK);
	}
	
	@PostMapping(path = "/answeringGraderForm")
	public ResponseEntity<HashMap<String, String>> savinggraderanswers(@RequestBody AnsweringGraderFormParameters answerandtheform){
		Grader grader = answerandtheform.getGrader();
		GraderFormAnswerSheet answersheet = answerandtheform.getGraderformanswersheet();
		grader.getGraderformanswersheet().add(answersheet);
		answersheet.setGrader(grader);
		graderrep.save(grader);
		graderformanswersheetrep.save(answersheet);
		HashMap<String , String> js1 = new HashMap<>();
		js1.put("message", "answers are saved");
		return new ResponseEntity<HashMap<String,String>>(js1,HttpStatus.OK);
	}
	
	@PostMapping(path = "/answeringAttendantForm")
	public ResponseEntity<HashMap<String, String>> savingattendantanswers(@RequestBody AnsweringAttendantFormParameters answerandtheform){
		Attendant attendant = answerandtheform.getAttendant();
		AttendFormAnswerSheet answersheet = answerandtheform.getAttendantformanswersheet();
		attendant.getAttendformanswersheet().add(answersheet);
		answersheet.setAttendat(attendant);
		attendantrep.save(attendant);
		attendantformanswersheetrep.save(answersheet);
		HashMap<String , String> js1 = new HashMap<>();
		js1.put("message", "answers are saved");
		return new ResponseEntity<HashMap<String,String>>(js1,HttpStatus.OK);
	}
	
}
