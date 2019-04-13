package com.kingshuk.hibernateandjpa.hibernatesoftdelete.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingshuk.hibernateandjpa.hibernatesoftdelete.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
