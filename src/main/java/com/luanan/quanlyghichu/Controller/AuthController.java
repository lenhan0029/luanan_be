package com.luanan.quanlyghichu.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanan.quanlyghichu.Model.DTO.Request.LoginDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.SignupDTO;
import com.luanan.quanlyghichu.Service.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@Valid @RequestBody LoginDTO loginRequest) {
		return accountService.login(loginRequest);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupDTO signupRequest){
		return accountService.signup(signupRequest);
	}
}
