package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.UpdateClassDTO;

public interface OwnClassService {

	ResponseEntity<?> getAllClass(int id_account,String search);
	
	ResponseEntity<?> UpdateClass(int id_class,UpdateClassDTO dto);
	
	ResponseEntity<?> createClass(int id_account);
}
