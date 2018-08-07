package com.processingcenter.processingcenter.services;

import com.processingcenter.processingcenter.entity.Account;
import com.processingcenter.processingcenter.entity.Transaction;
import com.processingcenter.processingcenter.repositories.AccountRepository;
import com.processingcenter.processingcenter.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

/**
 * Created by davlet on 1/31/18.
 * Service with methods for making payments
 */
@Service
public class PaymentService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public PaymentService() {
    }

    /**
     * Payment method from one balance to another
     * @param fromId
     * @param toId
     * @param amount
     * @return transaction id, else return null
     */
    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public boolean makePayment(Long fromId, Long toId, Integer amount){
        if (balanceIsNotNegativeAfterPaymentAmountOf(fromId, amount)){
            Account fromAccount = accountRepository.findByAccId(fromId);
            Account toAccount = accountRepository.findByAccId(toId);
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            Transaction transaction = new Transaction(fromId, toId, amount);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
            transactionRepository.save(transaction);
            return true;
        }
        return false;
    }

    /**
     * Method for checking if funds are suffucient for making transaction
     * @param fromId
     * @param amount
     * @return true - if sufficient funds, otherwise false
     */
    public boolean balanceIsNotNegativeAfterPaymentAmountOf(Long fromId, Integer amount){
        Account account = accountRepository.findByAccId(fromId);
        if (account.getBalance() - amount >= 0){
            return true;
        }
        return false;
    }

    public void setAccountRepository(AccountRepository accountRep) {
        this.accountRepository = accountRep;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
}
