package com.luanan.quanlyghichu.Service.ServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.TaskDTO;
import com.luanan.quanlyghichu.Service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	@Override
	public ResponseEntity<?> createTask(TaskDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> updateTask(int id, TaskDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteTask(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getTaskByProject(int id_account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getTaskById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
