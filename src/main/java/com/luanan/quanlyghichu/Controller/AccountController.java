package com.luanan.quanlyghichu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanan.quanlyghichu.Model.DTO.Request.ChangePasswordDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.ChangePasswordFirstDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.EditAccountDTO;
import com.luanan.quanlyghichu.Service.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PutMapping("/changepassword1st")
	public ResponseEntity<?> changePasswordFirstLogin(@RequestBody ChangePasswordFirstDTO dto){
		return accountService.changePasswordFirstLogin(dto);
	}
	
	@PutMapping("/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto){
		return accountService.changePassword(dto);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> editAccount(@RequestBody EditAccountDTO dto){
		return accountService.editAccount(dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getListAccount(){
		return accountService.getListAccount();
	}
}
