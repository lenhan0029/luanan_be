package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.CategoryDTO;

public interface CategoryService {
	
	ResponseEntity<?> createCategory(CategoryDTO dto);
	
	ResponseEntity<?> updateCategory(int id, CategoryDTO dto);
	
	ResponseEntity<?> deleteCategory(int id_account,int id);
	
	ResponseEntity<?> getAllCategory(int id_account);
}
