/*package com.example.demo.test;

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
	WorkShopRepository workshopRepository;
	Session session;
	@Autowired
	@GetMapping(path="/workshophome")
	public ResponseEntity<List<WorkShop>> ShowWorkShops(){
		List<WorkShop> workshop =(List<WorkShop>) workshopRepository.findAll();
		if(workshopRepository.findAll() != null) {
			return new ResponseEntity(workshop,HttpStatus.OK);
		}
		return null;
	
	}
	
}*/
