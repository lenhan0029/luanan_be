package com.luanan.quanlyghichu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luanan.quanlyghichu.Model.Entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

	@Query(value = "select * from Project p where p.id_account = :idaccount", nativeQuery = true)
	List<Project> findByAccount(@Param("idaccount") int idaccount);
	
}
