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

import com.luanan.quanlyghichu.Model.DTO.Request.SubjectDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.TimeTableDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.UpdateTimeTable;
import com.luanan.quanlyghichu.Service.TimeTableService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/timetable")
public class TimeTableController {

	@Autowired
	TimeTableService timeTableService;
	
	@PostMapping
	public ResponseEntity<?> createTimeTable(@RequestBody TimeTableDTO dto){
		return timeTableService.createTimeTable(dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getTimeTable(
			@RequestParam(name = "accountid",defaultValue = "0", required = false) int id_account,
			@RequestParam(name = "week",defaultValue = "1-2-3-4-5-6-7-8-9-10-11-12-13-14-15", required = false) String week,
			@RequestParam(name = "name",defaultValue = "%", required = false) String name,
			@RequestParam(name = "classcode",defaultValue = "%", required = false) String classcode,
			@RequestParam(name = "type",defaultValue = "%", required = false) String type){
		return timeTableService.getTimeTable(id_account,week,name,classcode,type);
	}
	@GetMapping("/subject")
	public ResponseEntity<?> getTimeTableBySubject(
			@RequestParam(name = "accountid",defaultValue = "0", required = false) int id_account,
			@RequestParam(name = "subjectid",defaultValue = "1", required = false) int id_subject){
		return timeTableService.getTimeTableBySubject(id_account,id_subject);
	}
	@PutMapping
	public ResponseEntity<?> addNoteforSubject(@RequestParam(name = "subjectid",defaultValue = "0", required = true) int id_subject,
			@RequestBody SubjectDTO dto){
		return timeTableService.addNoteToSubject(id_subject, dto.getNote(),dto.getType());
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateTimetable(@RequestBody UpdateTimeTable dto){
		return timeTableService.updateTimeTable(dto);
	}
}
