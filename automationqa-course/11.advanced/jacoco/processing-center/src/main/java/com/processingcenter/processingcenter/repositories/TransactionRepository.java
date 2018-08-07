package com.processingcenter.processingcenter.repositories;

import com.processingcenter.processingcenter.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by davlet on 1/31/18.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAll();
}
