package com.luanan.quanlyghichu.Service.ServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.DocumentDTO;
import com.luanan.quanlyghichu.Service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{

	@Override
	public ResponseEntity<?> createDocument(DocumentDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> updateDocument(int id, DocumentDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteDocument(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getDocumentByAccount(int id_account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getDocumentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
