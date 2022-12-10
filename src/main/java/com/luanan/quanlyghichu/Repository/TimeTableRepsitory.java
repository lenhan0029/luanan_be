package com.luanan.quanlyghichu.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luanan.quanlyghichu.Model.Entities.TimeTable;

public interface TimeTableRepsitory extends JpaRepository<TimeTable, Integer>{

	@Query(value = "select * from TimeTable t where t.id_account = :idaccount and t.week = :week", nativeQuery = true)
	Optional<TimeTable> findByAccountAndWeek(@Param("idaccount") int idaccount, @Param("week") int week);
}
