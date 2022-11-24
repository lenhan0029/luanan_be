package com.luanan.quanlyghichu.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luanan.quanlyghichu.Model.DTO.Response.DocumentDTO;
import com.luanan.quanlyghichu.Model.Entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

	@Query(value = "select NEW com.luanan.quanlyghichu.Model.DTO.Response.DocumentDTO(" +
			"d.id, d.name, d.description,d.link,d.category.id,d.category.name) " +
			"from Document d " +
			"where (d.category.account.id = :idaccount or d.category.id = :idcategory) and " +
			"lower(d.name)  like  %:name% ", nativeQuery = false)
	Page<DocumentDTO> listDocument(int idaccount,String name,int idcategory, Pageable page);
	
	@Query(value = "select * from Document d where d.id_category = :idcategory", nativeQuery = true)
	List<Document> findByCategory(@Param("idcategory") int idcategory);
}
