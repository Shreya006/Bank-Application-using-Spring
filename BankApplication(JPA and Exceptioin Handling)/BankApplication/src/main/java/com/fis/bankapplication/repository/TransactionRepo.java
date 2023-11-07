package com.fis.bankapplication.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fis.bankapplication.model.Transaction;

@Repository
@Transactional
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE (t.accountFrom.id = :accountId OR t.accountTo.id = :accountId) " + "AND t.dateOfTransaction BETWEEN :startDate AND :endDate")
    public List<Transaction> getAccountStatementsByDateRange(@Param("accountId") long accountId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

//    @Query("SELECT t FROM Transaction t ORDER BY WHERE t.accountFrom.id = :accountId OR t.accountTo.id = :accountId LIMIT 2")
//    public List<Transaction> getLastTwoTransactions(@Param("accountId") long accountId);

    @Query("SELECT t FROM Transaction t WHERE t.accountFrom.id = :accountId OR t.accountTo.id = :accountId ")
    public List<Transaction> getTransactionsForAccount(@Param("accountId") long accountId);
	    
}