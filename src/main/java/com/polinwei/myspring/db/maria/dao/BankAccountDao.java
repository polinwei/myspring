package com.polinwei.myspring.db.maria.dao;

import com.polinwei.myspring.db.maria.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountDao extends JpaRepository<BankAccount,Long> {
}
