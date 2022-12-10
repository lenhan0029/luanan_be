package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.TimeTableDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.UpdateTimeTable;

public interface TimeTableService {

	ResponseEntity<?> createTimeTable(TimeTableDTO dto);
	
	ResponseEntity<?> getTimeTable(int id_account, String week, String name, String classcode, String type);
	
	ResponseEntity<?> addNoteToSubject(int id_subject, String note,String type);
	
	ResponseEntity<?> updateTimeTable(UpdateTimeTable dto);
	
	ResponseEntity<?> getTimeTableBySubject(int id_account, int id_subject);
}
