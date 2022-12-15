package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.UpdateClassDTO;
import com.luanan.quanlyghichu.Model.DTO.Response.ResponseModel;
import com.luanan.quanlyghichu.Model.Entities.Account;
import com.luanan.quanlyghichu.Model.Entities.OwnClass;
import com.luanan.quanlyghichu.Model.Entities.Subject;
import com.luanan.quanlyghichu.Repository.AccountRepository;
import com.luanan.quanlyghichu.Repository.ClassRepository;
import com.luanan.quanlyghichu.Repository.SubjectRepository;
import com.luanan.quanlyghichu.Repository.TimeTableRepsitory;
import com.luanan.quanlyghichu.Service.OwnClassService;

@Service
public class OwnClassServiceImp implements OwnClassService{
	
	final ClassRepository classRepository;
	final AccountRepository accountRepository;
	final TimeTableRepsitory timeTableRepsitory;
	final SubjectRepository subjectRepository;

	public OwnClassServiceImp(ClassRepository classRepository, AccountRepository accountRepository,
			TimeTableRepsitory timeTableRepsitory, SubjectRepository subjectRepository) {
		super();
		this.classRepository = classRepository;
		this.accountRepository = accountRepository;
		this.timeTableRepsitory = timeTableRepsitory;
		this.subjectRepository = subjectRepository;
	}

	@Override
	public ResponseEntity<?> getAllClass(int id_account, String search) {
		List<OwnClass> list = classRepository.findByFilter(id_account, search.toLowerCase());
		if(list.isEmpty()) {
			return ResponseEntity.ok().body(
					new ResponseModel("Không tìm thấy lớp học",404));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Thành công",200,list));
	}

	@Override
	public ResponseEntity<?> UpdateClass(int id_class,UpdateClassDTO dto) {
		Optional<OwnClass> ownClass = classRepository.findById(id_class);
		if(ownClass.isEmpty()) {
			return ResponseEntity.ok().body(
					new ResponseModel("Không tìm thấy lớp học",404));
		}
		OwnClass update = ownClass.get();
		update.setListsv(dto.getListsv());
		update.setProgress(dto.getProgress());
		update.setNote(dto.getNote());
		classRepository.save(update);
		return ResponseEntity.ok().body(
				new ResponseModel("Thành công",200));
	}

	@Override
	public ResponseEntity<?> createClass(int id_account) {
		Optional<Account> account = accountRepository.findById(id_account);
		if(account.isEmpty()) {
			return ResponseEntity.ok().body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		List<Integer> timetables = new ArrayList<>();
		timetables.add(timeTableRepsitory.findByAccountAndWeek(id_account, 1).get().getId());
		String[] types = new String[] {"teach","cancel","offset", "offseted"};
		List<Subject> subjects = subjectRepository.findByTimeTable(timetables, "%", "%",types);
		if(subjects.isEmpty()) {
			return ResponseEntity.ok().body(
					new ResponseModel("Không tìm thấy lớp học",404));
		}
		List<OwnClass> ownclasses = new ArrayList<>();
//		
		for (Subject subject : subjects) {
			int temp = 0;
			OwnClass ownClass = new OwnClass();
			ownClass.setClasscode(subject.getClassCode());
			ownClass.setCode(subject.getCode());
			ownClass.setName(subject.getName());
			ownClass.setAccount(account.get());
			if(ownclasses.size() == 0) {
				ownclasses.add(ownClass);
			}else {
				for(int i = 0; i < ownclasses.size(); i++) {
					if(ownclasses.get(i).getClasscode().equals(subject.getClassCode()) && ownclasses.get(i).getCode().equals(subject.getCode())) {
						temp = 1;
					}
				}
				if(temp == 0) {
					ownclasses.add(ownClass);
				}
			}
		}
		List<OwnClass> rs = new ArrayList<>();
		for (OwnClass ownClass : ownclasses) {
			rs.add(classRepository.save(ownClass));
		}
		
		return ResponseEntity.ok().body(
				new ResponseModel("Danh sách lớp học",200,rs));
	}

	
}
