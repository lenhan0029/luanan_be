package com.luanan.quanlyghichu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanan.quanlyghichu.Model.DTO.Request.CreateSubjectDTO;
import com.luanan.quanlyghichu.Service.SubjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	SubjectService subjectService;
	
	@PostMapping
	public ResponseEntity<?> createSubject(@RequestBody CreateSubjectDTO dto){
		return subjectService.createSubject(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id){
		return subjectService.getById(id);
	}
}
