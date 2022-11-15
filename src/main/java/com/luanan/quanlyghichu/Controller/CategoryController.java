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

import com.luanan.quanlyghichu.Model.DTO.Request.CategoryDTO;
import com.luanan.quanlyghichu.Service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<?> getAll(
			@RequestParam(name = "accountid",defaultValue = "0", required = false) int id_account
			){
		return categoryService.getAllCategory(id_account);
	}
	
	@PostMapping
	public ResponseEntity<?> createCategory(@RequestBody CategoryDTO dto){
		return categoryService.createCategory(dto);
	}
}
