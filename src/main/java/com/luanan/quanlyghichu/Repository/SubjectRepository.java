package com.luanan.quanlyghichu.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luanan.quanlyghichu.Model.Entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{

	@Query(value = "select * from Subject s where s.id_timetable = :idtimetable", nativeQuery = true)
	List<Subject> findByTimeTable(@Param("idtimetable") int idtimetable);
	
	@Modifying
	@Query(value = "DELETE FROM Subject WHERE id_timetable = :idtimetable", nativeQuery = true)
	@Transactional
	int deleteSubject(@Param("idtimetable") int idtimetable);
}
