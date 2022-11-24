package com.luanan.quanlyghichu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanan.quanlyghichu.Model.DTO.Request.ProjectDTO;
import com.luanan.quanlyghichu.Service.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<?> createProject(@RequestBody ProjectDTO dto){
		return projectService.createProject(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProject(@PathVariable("id") int id, @RequestBody ProjectDTO dto){
		return projectService.updateProject(id, dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllById(@RequestParam(name = "accountid",defaultValue = "0", required = true) int id_account){
		return projectService.getProjectByAccount(id_account);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id){
		return projectService.getProjectById(id);
	}
}
