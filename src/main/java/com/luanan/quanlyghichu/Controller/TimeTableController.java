package com.luanan.quanlyghichu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanan.quanlyghichu.Model.DTO.Request.TimeTableDTO;
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
			@RequestParam(name = "accountid",defaultValue = "0", required = false) int id_account){
		return timeTableService.getTimeTable(id_account);
	}
}