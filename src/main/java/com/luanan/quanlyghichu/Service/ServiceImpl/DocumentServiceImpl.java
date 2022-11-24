package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Exception.ResourceNotFoundException;
import com.luanan.quanlyghichu.Model.DTO.Request.DocumentDTO;
import com.luanan.quanlyghichu.Model.DTO.Response.ResponseModel;
import com.luanan.quanlyghichu.Model.Entities.Account;
import com.luanan.quanlyghichu.Model.Entities.Category;
import com.luanan.quanlyghichu.Model.Entities.Document;
import com.luanan.quanlyghichu.Repository.AccountRepository;
import com.luanan.quanlyghichu.Repository.CategoryRepositoty;
import com.luanan.quanlyghichu.Repository.DocumentRepository;
import com.luanan.quanlyghichu.Service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{
	final DocumentRepository documentRepository;
	final CategoryRepositoty categoryRepositoty;
	final AccountRepository accountRepository;

	public DocumentServiceImpl(DocumentRepository documentRepository, CategoryRepositoty categoryRepositoty,
			AccountRepository accountRepository) {
		super();
		this.documentRepository = documentRepository;
		this.categoryRepositoty = categoryRepositoty;
		this.accountRepository = accountRepository;
	}

	@Override
	public ResponseEntity<?> createDocument(DocumentDTO dto) {
		Optional<Category> category = categoryRepositoty.findById(dto.getId_category());
		if(category.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Danh mục không tồn tại",404));
		}
		Document temp = new Document();
		temp.setCategory(category.get());
		temp.setDescription(dto.getDescription());
		temp.setLink(dto.getLink());
		temp.setName(dto.getName());
		Document newDocument = documentRepository.save(temp);
		if(newDocument != null) {
			return ResponseEntity.ok().body(
					new ResponseModel("Tạo tài liệu thành công",200,newDocument));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
				new ResponseModel("Tạo tài liệu thất bại",403));
	}

	@Override
	public ResponseEntity<?> updateDocument(int id, DocumentDTO dto) {
		Optional<Document> document = documentRepository.findById(id);
		if(document.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài liệu không tồn tại",404));
		}
		Optional<Category> category = categoryRepositoty.findById(dto.getId_category());
		if(category.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Danh mục không tồn tại",404));
		}
		Document temp = document.get();
		temp.setCategory(category.get());
		temp.setDescription(dto.getDescription());
		temp.setLink(dto.getLink());
		temp.setName(dto.getName());
		Document newDocument = documentRepository.save(temp);
		return ResponseEntity.ok().body(
				new ResponseModel("Cập nhật tài liệu thành công",200,newDocument));
	}

	@Override
	public ResponseEntity<?> deleteDocument(int id) {
		Optional<Document> document = documentRepository.findById(id);
		if(document.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài liệu không tồn tại",404));
		}
		documentRepository.deleteById(id);
		return ResponseEntity.ok().body(
				new ResponseModel("Xóa tài liệu thành công",200));
	}

	@Override
	public ResponseEntity<?> getDocumentByAccount(int id_account, String name, int idcategory,int page,String sorttype) {
		Optional<Account> account = accountRepository.findById(id_account);
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		Pageable newPage = createPage(page, sorttype);
		Page<com.luanan.quanlyghichu.Model.DTO.Response.DocumentDTO> pageDocument = 
				documentRepository.listDocument(id_account, name, idcategory, newPage);
		if(pageDocument.hasContent()) {
			return ResponseEntity.ok().body(
					new ResponseModel("Danh sách tài liệu",200,pageDocument));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseModel("Không tìm thấy tài liệu",404));
	}

	@Override
	public ResponseEntity<?> getDocumentById(int id) {
		Optional<Document> document = documentRepository.findById(id);
		if(document.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài liệu",404));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Thông tin tài liệu",200,document.get()));
	}
	
	public Pageable createPage(int page, String sortType){

		String sType = sortType.toUpperCase();
		Sort sort;

		switch (sType){
			case "ASC":
				sort = Sort.by(Sort.Direction.ASC, "name");
				break;
			case "DESC":
				sort = Sort.by(Sort.Direction.DESC, "name");
				break;
			default:
				throw new ResourceNotFoundException("NO MODE SORT FOUND !");
		}

		return PageRequest.of(page,12,sort);

	}

}
