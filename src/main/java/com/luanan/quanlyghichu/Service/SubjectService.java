package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.CreateSubjectDTO;

public interface SubjectService {

	ResponseEntity<?> createSubject(CreateSubjectDTO dto);
}
