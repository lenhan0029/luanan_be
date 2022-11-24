package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.TaskDTO;
import com.luanan.quanlyghichu.Model.DTO.Response.ResponseModel;
import com.luanan.quanlyghichu.Model.Entities.Account;
import com.luanan.quanlyghichu.Model.Entities.Priority;
import com.luanan.quanlyghichu.Model.Entities.Project;
import com.luanan.quanlyghichu.Model.Entities.Status;
import com.luanan.quanlyghichu.Model.Entities.Task;
import com.luanan.quanlyghichu.Repository.AccountRepository;
import com.luanan.quanlyghichu.Repository.ProjectRepository;
import com.luanan.quanlyghichu.Repository.TaskRepository;
import com.luanan.quanlyghichu.Service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	final TaskRepository taskRepository;
	final ProjectRepository projectRepository;
	final AccountRepository accountRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository,
			AccountRepository accountRepository) {
		super();
		this.taskRepository = taskRepository;
		this.projectRepository = projectRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public ResponseEntity<?> createTask(TaskDTO dto) {
		Optional<Project> project = projectRepository.findById(dto.getId_project());
		if(project.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Dự án không tồn tại",404));
		}
		Task task = new Task();
		task.setDescription(dto.getDescription());
		task.setName(dto.getName());
		task.setPriority(Priority.valueOf(dto.getPriority()));
		task.setProject(project.get());
		task.setStatus(Status.valueOf(dto.getStatus()));
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	    OffsetDateTime st = OffsetDateTime.now();
	    if(dto.getTimeStart() != "") {
			st = OffsetDateTime.parse(dto.getTimeStart(),dateTimeFormatter);
			System.out.println(st.toString());
			
		}
		task.setTimeStart(st);
		if(dto.getTimeEnd() != "") {
			st = OffsetDateTime.parse(dto.getTimeEnd(),dateTimeFormatter);
			task.setTimeEnd(st);
			
		}
		Task newTask = taskRepository.save(task);
		if(newTask != null) {
			return ResponseEntity.ok().body(
					new ResponseModel("Tạo công việc thành công",200,newTask));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
				new ResponseModel("Tạo công việc thất bại",403));
	}

	@Override
	public ResponseEntity<?> updateTask(int id, TaskDTO dto) {
		Optional<Task> task = taskRepository.findById(id);
		if(task.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy công việc",404));
		}
		Task temp = task.get();
		temp.setDescription(dto.getDescription());
		temp.setName(dto.getName());
		temp.setPriority(Priority.valueOf(dto.getPriority()));
		temp.setStatus(Status.valueOf(dto.getStatus()));
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	    OffsetDateTime st = OffsetDateTime.now();
	    if(dto.getTimeStart() != "") {
			st = OffsetDateTime.parse(dto.getTimeStart(),dateTimeFormatter);
			System.out.println(st.toString());
			
		}
	    temp.setTimeStart(st);
		if(dto.getTimeEnd() != "") {
			st = OffsetDateTime.parse(dto.getTimeEnd(),dateTimeFormatter);
			temp.setTimeEnd(st);
			
		}
		Task newTask = taskRepository.save(temp);
		return ResponseEntity.ok().body(
				new ResponseModel("Cập nhật công việc thành công",200,newTask));
	}

	@Override
	public ResponseEntity<?> deleteTask(int id) {
		Optional<Task> task = taskRepository.findById(id);
		if(task.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Công việc không tồn tại",404));
		}
		taskRepository.deleteById(id);
		return ResponseEntity.ok().body(
				new ResponseModel("Xóa công việc thành công",200));
	}

	@Override
	public ResponseEntity<?> getTaskByProject(int id_project) {
		Optional<Project> project = projectRepository.findById(id_project);
		if(project.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Dự án không tồn tại",404));
		}
		List<Task> tasks = taskRepository.findByProject(id_project);
		if(tasks.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ResponseModel("Không tồn tại công việc",404));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Danh sách công việc",200,tasks));
	}

	@Override
	public ResponseEntity<?> getTaskById(int id) {
		Optional<Task> task = taskRepository.findById(id);
		if(task.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Công việc không tồn tại",404));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Lấy công việc thành công",200,task.get()));
	}

}
