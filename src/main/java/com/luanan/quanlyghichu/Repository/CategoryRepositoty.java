package com.luanan.quanlyghichu.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luanan.quanlyghichu.Model.Entities.Category;


public interface CategoryRepositoty extends JpaRepository<Category, Integer>{

	Optional<Category> findByName(String name);
	
	@Query(value = "select * from Category c where c.id_account = :idaccount", nativeQuery = true)
	List<Category> findByAccount(@Param("idaccount") int idaccount);
	
}
