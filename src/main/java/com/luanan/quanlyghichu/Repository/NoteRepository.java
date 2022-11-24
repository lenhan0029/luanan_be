package com.luanan.quanlyghichu.Repository;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.luanan.quanlyghichu.Model.Entities.Note;


public interface NoteRepository extends JpaRepository<Note, Integer>{

	@Query(value = "select * " +
	"from Note n " +
	"where n.id_account = :idaccount and" +
	"(lower(n.title)  like  %:title% or " +
	"lower(n.location)  like %:location%) and (n.time_start between :timestart and :timeend) order by n.time_start asc", nativeQuery = true)
	List<Note> listNote(@Param("idaccount") int idaccount,@Param("title") String title,@Param("location") String location,
			@Param("timestart") OffsetDateTime timestart, @Param("timeend") OffsetDateTime timeend);
}
