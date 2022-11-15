package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.CategoryDTO;
import com.luanan.quanlyghichu.Model.DTO.Response.ResponseModel;
import com.luanan.quanlyghichu.Model.Entities.Account;
import com.luanan.quanlyghichu.Model.Entities.Category;
import com.luanan.quanlyghichu.Model.Entities.Document;
import com.luanan.quanlyghichu.Repository.AccountRepository;
import com.luanan.quanlyghichu.Repository.CategoryRepositoty;
import com.luanan.quanlyghichu.Repository.DocumentRepository;
import com.luanan.quanlyghichu.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	final CategoryRepositoty categoryRepositoty;
	final DocumentRepository documentRepository;
	final AccountRepository accountRepository;
	final ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepositoty categoryRepositoty, DocumentRepository documentRepository,
			AccountRepository accountRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepositoty = categoryRepositoty;
		this.documentRepository = documentRepository;
		this.accountRepository = accountRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<?> createCategory(CategoryDTO dto) {
		Optional<Account> account = accountRepository.findById(dto.getId_account());
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		Optional<Category> category = categoryRepositoty.findByName(dto.getName());
		if(category.isPresent()  &&  category.get().getId() == dto.getId_account()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ResponseModel("Danh mục tài liệu đã tồn tại",409));
		}
		
		Category categoryDTO = new Category();
		categoryDTO.setName(dto.getName());
		Category newCategory = categoryRepositoty.save(categoryDTO);
		return ResponseEntity.ok().body(
				new ResponseModel("Thêm danh mục thành công",200,newCategory));
	}

	@Override
	public ResponseEntity<?> updateCategory(int id, CategoryDTO dto) {
		Optional<Category> category = categoryRepositoty.findById(id);
		if(category.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Danh mục tài liệu không tồn tại",404));
		}
		Category categoryDTO  = category.get();
		categoryDTO.setName(dto.getName());
		Category newCategory = categoryRepositoty.save(categoryDTO);
		return ResponseEntity.ok().body(
				new ResponseModel("Cập nhật danh mục thành công",200,newCategory));
	}

	@Override
	public ResponseEntity<?> deleteCategory(int id_account,int id) {
		Optional<Category> category = categoryRepositoty.findById(id);
		if(category.get().getAccount().getId() == id_account && category.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Danh mục tài liệu không tồn tại",404));
		}
		Optional<Document> document = documentRepository.findByCategory(category.get());
		if(document.get().getCategory().getAccount().getId() == id_account && document.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ResponseModel("Danh mục có tồn tại tài liệu",409));
		}
		categoryRepositoty.deleteById(id);;
		return ResponseEntity.ok().body(
				new ResponseModel("Xóa danh mục thành công",200));
	}

	@Override
	public ResponseEntity<?> getAllCategory(int id_account) {
		Optional<Account> account = accountRepository.findById(id_account);
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		List<Category> categories = categoryRepositoty.findByAccount(id_account);
		if(categories.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ResponseModel("Không tồn tại danh mục tài liệu",404));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Danh sách danh mục",200,categories));
	}

}
