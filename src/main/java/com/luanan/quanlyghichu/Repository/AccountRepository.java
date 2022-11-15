package com.luanan.quanlyghichu.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luanan.quanlyghichu.Model.Entities.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByUsername(String username);
}
