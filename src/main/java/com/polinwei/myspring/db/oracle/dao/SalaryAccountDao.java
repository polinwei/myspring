package com.polinwei.myspring.db.oracle.dao;

import com.polinwei.myspring.db.oracle.model.SalaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryAccountDao extends JpaRepository<SalaryAccount,Long>{
}
