package com.fis.bankapplication.repository;

import org.springframework.stereotype.Repository;
import com.fis.bankapplication.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

@Repository
public class TransactionRepoImpl implements TransactionRepo {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public String addTransaction(Transaction transaction) {
        entityManager.persist(transaction);
        return "Transaction added successfully";
    }

    @Override
    public String updateTransaction(Transaction transaction) {
        entityManager.merge(transaction);
        return "Transaction updated successfully";
    }

    @Override
    public String deleteTransaction(long transactionId) {
        Transaction transaction = entityManager.find(Transaction.class, transactionId);
        if (transaction != null) {
            entityManager.remove(transaction);
            return "Transaction deleted successfully";
        }
        return "Transaction not found";
    }

    @Override
    public Transaction getTransactionById(long transactionId) {
        return entityManager.find(Transaction.class, transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        String jpql = "SELECT t FROM Transaction t";
        
        TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
		return query.getResultList();

    }
    
    
    @Override
	public List<Transaction> getAccountStatementsByDateRange(long accountId, Date startDate, Date endDate) {
    	String jpql = "SELECT t FROM Transaction t WHERE (t.accNoFrom = ?1 OR t.accNoTo = ?2) AND (t.dateOfTrans BETWEEN ?3 AND ?4)";
    	TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
        query.setParameter(1, accountId);
 		query.setParameter(2, accountId);
 		query.setParameter(3, startDate);
 		query.setParameter(4, endDate);
        return query.getResultList();
    }

    
	@Override
	public List<Transaction> getLastTwoTransactions(long accountId) {
		String jpql = "SELECT t FROM Transaction t WHERE t.accNoFrom = ?1 OR t.accNoTo = ?2 LIMIT 2";
		TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
        query.setParameter(1, accountId);
		query.setParameter(2, accountId);
        return query.getResultList();
	}
    
    @Override
    public List<Transaction> getTransactionsForAccount(long accountId) {
        String jpql = "SELECT t FROM Transaction t WHERE t.accNoFrom = ?1 OR t.accNoTo = ?2";
        TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
        query.setParameter(1, accountId);
		query.setParameter(2, accountId);
        return query.getResultList();
    }

	
}