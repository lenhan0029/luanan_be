package com.luanan.quanlyghichu.Service;

import org.springframework.http.ResponseEntity;

import com.luanan.quanlyghichu.Model.DTO.Request.NoteDTO;

public interface NoteService {

	ResponseEntity<?> createNote(NoteDTO dto);
	
	ResponseEntity<?> updateNote(int id, NoteDTO dto);
	
	ResponseEntity<?> deleteNote(int id);
	
	ResponseEntity<?> getNoteByAccount(int id_account);
	
	ResponseEntity<?> getNoteById(int id);
}
