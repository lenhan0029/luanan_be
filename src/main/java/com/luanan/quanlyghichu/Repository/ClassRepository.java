package com.luanan.quanlyghichu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanan.quanlyghichu.Model.Entities.OwnClass;

public interface ClassRepository extends JpaRepository<OwnClass,Integer>{

	@Query(value = "select * from OwnClass oc where oc.id_account = :id_account and lower(oc.name) like %:search% or lower(oc.classcode) like %:search%"
			+ " or lower(oc.code) like %:search%", nativeQuery = true)
		List<OwnClass> findByFilter(int id_account,String search);
}
