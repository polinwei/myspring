package com.polinwei.myspring.db.maria.service;

import com.polinwei.myspring.db.maria.dao.BankAccountDao;
import com.polinwei.myspring.db.maria.model.BankAccount;
import com.polinwei.myspring.exception.CommonTransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountService {
    private static final Logger logger = LoggerFactory.getLogger(BankAccountService.class);

    @Autowired
    private BankAccountDao bankAccountDao;

    // MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY )
    public void addAmount(Long id, double amount) throws CommonTransactionException {

        BankAccount account = null;
        try {
            account = bankAccountDao.findById(id).get();
        } catch (Exception e) {
            throw new CommonTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new CommonTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }

    // Do not catch BankTransactionException in this method.
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = CommonTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws CommonTransactionException {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
}
