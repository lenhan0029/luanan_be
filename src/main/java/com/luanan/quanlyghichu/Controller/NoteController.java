package com.luanan.quanlyghichu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanan.quanlyghichu.Model.DTO.Request.NoteDTO;
import com.luanan.quanlyghichu.Service.NoteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	NoteService noteService;
	
	@GetMapping
	public ResponseEntity<?> getListNote(
			@RequestParam(name = "accountid",defaultValue = "0", required = true) int id_account,
			@RequestParam(name = "title",defaultValue = "%", required = false) String title,
			@RequestParam(name = "location",defaultValue = "%", required = false) String location,
			@RequestParam(name = "date",defaultValue = "0", required = false) String date
			){
		return noteService.getNoteByAccount(id_account, title, location, date);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getNoteById(@PathVariable int id){
		return noteService.getNoteById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> createNote(@RequestBody NoteDTO dto){
		return noteService.createNote(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateNote(@PathVariable int id,@RequestBody NoteDTO dto){
		return noteService.updateNote(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable int id){
		return noteService.deleteNote(id);
	}
}
