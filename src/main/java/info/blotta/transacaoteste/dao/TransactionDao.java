package info.blotta.transacaoteste.dao;

import info.blotta.transacaoteste.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDao {

    int addTransaction(Transaction transaction);

    List<Transaction> selectAllTransactions();

    Optional<Transaction> getTransactionById(long id);

    int updateTransaction(Transaction transaction);
}
