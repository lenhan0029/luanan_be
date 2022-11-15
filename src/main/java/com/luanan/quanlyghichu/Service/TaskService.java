package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.TaskDTO;

public interface TaskService {

ResponseEntity<?> createTask(TaskDTO dto);
	
	ResponseEntity<?> updateTask(int id, TaskDTO dto);
	
	ResponseEntity<?> deleteTask(int id);
	
	ResponseEntity<?> getTaskByProject(int id_account);
	
	ResponseEntity<?> getTaskById(int id);
}
