package com.an.savingsb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.an.savingsb.entity.AccountDetails;

@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetails, Long>{
	
	Optional<AccountDetails> findByAccountname(String accountname);

	@Transactional
	@Modifying
	@Query("update AccountDetails a set a.balance = :balance where a.accountname = :accountname")
	void updateBalance(@Param(value = "accountname") String accountname, @Param(value = "balance") String balance);
}
