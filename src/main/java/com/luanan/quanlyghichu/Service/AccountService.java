package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.LoginDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.SignupDTO;

public interface AccountService {

	ResponseEntity<?> signup(SignupDTO dto);
	
	ResponseEntity<?> login(LoginDTO dto);
}
