package info.blotta.transacaoteste.dao;


import info.blotta.transacaoteste.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository("h2DB")
public class H2DBDao implements TransactionDao {

    // @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public H2DBDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public int addTransaction(Transaction transaction) {
        entityManager.persist(transaction);
        return (int)transaction.getId();
    }

    @Override
    public List<Transaction> selectAllTransactions() {
        Query query = entityManager.createQuery("SELECT e FROM Transaction e");

        return (List<Transaction>)query.getResultList();
    }

    @Override
    public Optional<Transaction> getTransactionById(long id) {
        return Optional.ofNullable(entityManager.find(Transaction.class, id));
    }

    @Override
    @Transactional
    public int updateTransaction(Transaction transaction) {
        Transaction tdb;
        try {
            tdb = getTransactionById(transaction.getId()).get();
        } catch (NoSuchElementException e) {
            return 0;
        }
        tdb = entityManager.merge(transaction);
        return 1;
    }
}
