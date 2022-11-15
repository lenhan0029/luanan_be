package com.luanan.quanlyghichu.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanan.quanlyghichu.Model.DTO.Response.DocumentDTO;
import com.luanan.quanlyghichu.Model.Entities.Category;
import com.luanan.quanlyghichu.Model.Entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

//	@Query(value = "select NEW com.luanan.quanlyghichu.Model.DTO.Response.DocumentDTO(" +
//			"d.id, d.name, d.description, d.link, d.category.name) " +
//			"from Document d " +
//			"where d.id = :idaccount and" +
//			"(lower(d.name)  like  %:name% or " +
//			"lower(d.category.name)  like %:category%)", nativeQuery = false)
//	Page<DocumentDTO> listDocument(int idaccount,String name,String category, Pageable page);
	
	Optional<Document> findByCategory(Category category);
}
