package info.blotta.transacaoteste.service;

import info.blotta.transacaoteste.dao.TransactionDao;
import info.blotta.transacaoteste.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionService(@Qualifier("h2DB") TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public int addTransaction(Transaction transaction) {
        return transactionDao.addTransaction(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDao.selectAllTransactions();
    }

    public Transaction updateTransaction(Transaction t) {
        if (transactionDao.updateTransaction(t) > 0) {
            return t;
        }
        return null;
    }
}
