package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.ProjectDTO;
import com.luanan.quanlyghichu.Model.DTO.Response.ResponseModel;
import com.luanan.quanlyghichu.Model.Entities.Account;
import com.luanan.quanlyghichu.Model.Entities.Category;
import com.luanan.quanlyghichu.Model.Entities.Priority;
import com.luanan.quanlyghichu.Model.Entities.Project;
import com.luanan.quanlyghichu.Model.Entities.Status;
import com.luanan.quanlyghichu.Repository.AccountRepository;
import com.luanan.quanlyghichu.Repository.ProjectRepository;
import com.luanan.quanlyghichu.Service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	final ProjectRepository projectRepository;
	final AccountRepository accountRepository;
	
	
	public ProjectServiceImpl(ProjectRepository projectRepository, AccountRepository accountRepository) {
		super();
		this.projectRepository = projectRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public ResponseEntity<?> createProject(ProjectDTO dto) {
		Optional<Account> account = accountRepository.findById(dto.getId_account());
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		Project project = new Project();
		project.setAccount(account.get());
		project.setCompany(dto.getCompany());
		project.setDescription(dto.getDescription());
		project.setName(dto.getName());
		project.setPriority(Priority.valueOf(dto.getPriority()));
		project.setStatus(Status.valueOf(dto.getStatus()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
			java.util.Date parsed1 = format.parse(dto.getTimeStart());
			Date start = new Date(parsed1.getTime());
			project.setTimeStart(start);
			java.util.Date parsed2 = format.parse(dto.getTimeEnd());
			Date end = new Date(parsed2.getTime());
			project.setTimeEnd(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Project newproject = projectRepository.save(project);
		if(newproject != null) {
			return ResponseEntity.ok().body(
					new ResponseModel("Tạo ghi chú thành công",200,newproject));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
				new ResponseModel("Tạo dự án thất bại",403));
	}

	@Override
	public ResponseEntity<?> updateProject(int id, ProjectDTO dto) {
		Optional<Project> oldProject = projectRepository.findById(id);
		if(oldProject.isEmpty() || oldProject.get().getAccount().getId() != dto.getId_account()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy dự án",404));
		}
		Project temp = oldProject.get();
		temp.setCompany(dto.getCompany());
		temp.setDescription(dto.getDescription());
		temp.setName(dto.getName());
		temp.setPriority(Priority.valueOf(dto.getPriority()));
		temp.setStatus(Status.valueOf(dto.getStatus()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
			java.util.Date parsed1 = format.parse(dto.getTimeStart());
			Date start = new Date(parsed1.getTime());
			temp.setTimeStart(start);
			java.util.Date parsed2 = format.parse(dto.getTimeEnd());
			Date end = new Date(parsed2.getTime());
			temp.setTimeEnd(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Project newProject = projectRepository.save(temp);
        return ResponseEntity.ok().body(
				new ResponseModel("Cập nhật ghi chú thành công",200,newProject));
	}

	@Override
	public ResponseEntity<?> deleteProject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getProjectByAccount(int id_account) {
		Optional<Account> account = accountRepository.findById(id_account);
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		List<Project> project = projectRepository.findByAccount(id_account);
		if(project.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ResponseModel("Không tồn tại dự án",404));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Danh sách dự án",200,project));
	}

	@Override
	public ResponseEntity<?> getProjectById(int id) {
		Optional<Project> project = projectRepository.findById(id);
		if(project.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Dự án không tồn tại",404));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Lấy dự án thành công",200,project.get()));
	}

}
