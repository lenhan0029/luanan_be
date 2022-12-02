package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.ChangePasswordDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.ChangePasswordFirstDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.EditAccountDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.LoginDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.SignupDTO;

public interface AccountService {

	ResponseEntity<?> signup(SignupDTO dto);
	
	ResponseEntity<?> login(LoginDTO dto);
	
	ResponseEntity<?> changePasswordFirstLogin(ChangePasswordFirstDTO dto);
	
	ResponseEntity<?> changePassword(ChangePasswordDTO dto);
	
	ResponseEntity<?> editAccount(EditAccountDTO dto);
}
