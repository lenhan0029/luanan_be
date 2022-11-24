package com.luanan.quanlyghichu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanan.quanlyghichu.Model.DTO.Request.TaskDTO;
import com.luanan.quanlyghichu.Service.TaskService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@PostMapping
	public ResponseEntity<?> createTask(@RequestBody TaskDTO dto){
		return taskService.createTask(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@PathVariable("id") int id,@RequestBody TaskDTO dto){
		return taskService.updateTask(id, dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getTaskByProject(@RequestParam(name = "projectid",defaultValue = "0", required = true) int id_project){
		return taskService.getTaskByProject(id_project);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id){
		return taskService.getTaskById(id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id){
		return taskService.deleteTask(id);
	}
}
