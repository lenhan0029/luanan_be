package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.TimeTableDTO;

public interface TimeTableService {

	ResponseEntity<?> createTimeTable(TimeTableDTO dto);
	
	ResponseEntity<?> getTimeTable(int id_account);
}
