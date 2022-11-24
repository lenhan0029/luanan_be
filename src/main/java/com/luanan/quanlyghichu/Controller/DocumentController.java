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

import com.luanan.quanlyghichu.Model.DTO.Request.DocumentDTO;
import com.luanan.quanlyghichu.Service.DocumentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	DocumentService documentService;
	
	@PostMapping
	public ResponseEntity<?> createDocument(@RequestBody DocumentDTO dto){
		return documentService.createDocument(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDocument(@PathVariable("id") int id, @RequestBody DocumentDTO dto){
		return documentService.updateDocument(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDocument(@PathVariable("id") int id){
		return documentService.deleteDocument(id);
	}
	
	@GetMapping
	public ResponseEntity<?> getDocumentByFilter(
			@RequestParam(name = "accountid",defaultValue = "0", required = true) int id_account,
			@RequestParam(name = "name",defaultValue = "%", required = false) String name,
			@RequestParam(name = "categoryid",defaultValue = "0", required = false) int id_category,
			@RequestParam(name = "page",defaultValue = "0", required = false) int page,
			@RequestParam(name = "sorttype",defaultValue = "ASC", required = false) String sorttype
			){
		return documentService.getDocumentByAccount(id_account, name, id_category, page, sorttype);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id){
		return documentService.getDocumentById(id);
	}
}
