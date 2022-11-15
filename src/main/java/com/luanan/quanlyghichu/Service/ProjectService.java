package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.ProjectDTO;

public interface ProjectService {
	ResponseEntity<?> createProject(ProjectDTO dto);
	
	ResponseEntity<?> updateProject(int id, ProjectDTO dto);
	
	ResponseEntity<?> deleteProject(int id);
	
	ResponseEntity<?> getProjectByAccount(int id_account);
	
	ResponseEntity<?> getProjectById(int id);
}
