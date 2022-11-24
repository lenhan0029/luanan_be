package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.DocumentDTO;

public interface DocumentService {
	ResponseEntity<?> createDocument(DocumentDTO dto);
	
	ResponseEntity<?> updateDocument(int id, DocumentDTO dto);
	
	ResponseEntity<?> deleteDocument(int id);
	
	ResponseEntity<?> getDocumentByAccount(int id_account, String name, int idcategory, int page, String sorttype);
	
	ResponseEntity<?> getDocumentById(int id);
}
