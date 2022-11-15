package com.luanan.quanlyghichu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luanan.quanlyghichu.Model.Entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
