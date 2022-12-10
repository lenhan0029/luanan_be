package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.CreateSubjectDTO;
import com.luanan.quanlyghichu.Model.DTO.Response.ResponseModel;
import com.luanan.quanlyghichu.Model.Entities.Subject;
import com.luanan.quanlyghichu.Model.Entities.TimeTable;
import com.luanan.quanlyghichu.Model.Entities.Type;
import com.luanan.quanlyghichu.Repository.AccountRepository;
import com.luanan.quanlyghichu.Repository.SubjectRepository;
import com.luanan.quanlyghichu.Repository.TimeTableRepsitory;
import com.luanan.quanlyghichu.Service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

	final SubjectRepository subjectRepository;
	final TimeTableRepsitory timeTableRepsitory;
	final AccountRepository accountRepository;
	
	public SubjectServiceImpl(SubjectRepository subjectRepository, TimeTableRepsitory timeTableRepsitory,
			AccountRepository accountRepository) {
		super();
		this.subjectRepository = subjectRepository;
		this.timeTableRepsitory = timeTableRepsitory;
		this.accountRepository = accountRepository;
	}


	@Override
	public ResponseEntity<?> createSubject(CreateSubjectDTO dto) {
		TimeTable timetable = timeTableRepsitory.findByAccountAndWeek(dto.getId_account(), dto.getWeek()).get();
		List<Integer> id_timetable = new ArrayList<>();
		id_timetable.add(timetable.getId());

		Subject subject = subjectRepository.findById(dto.getReference()).get();
		List<Subject> subjects = subjectRepository.findByTimeTable(id_timetable, "%", "%", "%");
		for (Subject item : subjects) {
			if(item.getDay().equals(dto.getDay())) {
				for (int i = 0; i < item.getNumber(); i++) {
					if(item.getStart() + i == dto.getStart() ||  item.getStart() + i == dto.getStart() + subject.getNumber()) {
						return ResponseEntity.ok().body(
								new ResponseModel("Môn học đã bị trùng lịch",409));
					}
				}
			}
		}
		Subject newSubject = new Subject();
		newSubject.setClassCode(subject.getClassCode());
		newSubject.setCode(subject.getCode());
		newSubject.setDay(dto.getDay());
		newSubject.setListStudent(subject.getListStudent());
		newSubject.setName(subject.getName());
		newSubject.setPractise(false);
		newSubject.setRoom(dto.getRoom());
		newSubject.setStart(dto.getStart());
		newSubject.setStc(subject.getStc());
		newSubject.setSubjectGroup(subject.getSubjectGroup());
		newSubject.setType(Type.valueOf(dto.getType()));
		newSubject.setNote(dto.getNote());
		newSubject.setNumber(subject.getNumber());
		newSubject.setSubjectReferenceFrom(subject);
		newSubject.setTimetable(timetable);
		Subject rs = subjectRepository.save(newSubject);
		subject.setType(Type.valueOf("offseted"));
		subjectRepository.save(subject);
		return ResponseEntity.ok().body(
				new ResponseModel("thành công",200,rs));
	}

}
