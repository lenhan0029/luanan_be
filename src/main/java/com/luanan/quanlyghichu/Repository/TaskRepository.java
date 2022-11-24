package com.luanan.quanlyghichu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luanan.quanlyghichu.Model.Entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

	@Query(value = "select * from Task t where t.id_project = :idproject", nativeQuery = true)
	List<Task> findByProject(@Param("idproject") int idproject);
}
