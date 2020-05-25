package info.blotta.transacaoteste.dao;

import info.blotta.transacaoteste.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("listDB")
public class TransactionListAsDBDao implements TransactionDao {
    private static long addCounter = 0;
    private static List<Transaction> DB = new ArrayList<>();

    @Override
    public int addTransaction(Transaction transaction) {
        transaction.setId(TransactionListAsDBDao.addCounter);
        TransactionListAsDBDao.addCounter += 1;
        DB.add(transaction);
        return 1;
    }

    @Override
    public List<Transaction> selectAllTransactions() {
        return DB;
    }

    @Override
    public Optional<Transaction> getTransactionById(long id) {
        return DB.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst();
    }

    @Override
    public int updateTransaction(Transaction transaction) {
        return getTransactionById(transaction.getId())
                .map(t -> {
                    int idx = DB.indexOf(t);
                    if (idx >= 0) {
                        DB.set(idx, transaction);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
