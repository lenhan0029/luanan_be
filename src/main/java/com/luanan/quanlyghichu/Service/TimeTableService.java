package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.TimeTableDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.UpdateTimeTable;

public interface TimeTableService {

	ResponseEntity<?> createTimeTable(TimeTableDTO dto);
	
	ResponseEntity<?> getTimeTable(int id_account);
	
	ResponseEntity<?> addNoteToSubject(int id_subject, String note);
	
	ResponseEntity<?> updateTimeTable(UpdateTimeTable dto);
}
