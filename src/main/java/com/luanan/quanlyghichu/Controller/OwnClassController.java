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

import com.luanan.quanlyghichu.Model.DTO.Request.NoteDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.UpdateClassDTO;
import com.luanan.quanlyghichu.Service.OwnClassService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/class")
public class OwnClassController {
	
	@Autowired
	OwnClassService ownClassService;

	@PostMapping
	public ResponseEntity<?> createClass(
			@RequestParam(name = "accountid",defaultValue = "0", required = true) int id_account){
		return ownClassService.createClass(id_account);
	}
	
	@GetMapping
	public ResponseEntity<?> getClass(
			@RequestParam(name = "accountid",defaultValue = "0", required = true) int id_account,
			@RequestParam(name = "search",defaultValue = "%", required = false) String search){
		return ownClassService.getAllClass(id_account, search);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateClass(
			@PathVariable("id") int id, @RequestBody UpdateClassDTO dto){
		return ownClassService.UpdateClass(id, dto);
	}
}
