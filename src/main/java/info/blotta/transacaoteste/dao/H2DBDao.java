package info.blotta.transacaoteste.dao;


import info.blotta.transacaoteste.model.CardApplication;
import info.blotta.transacaoteste.model.PaymentStatus;
import info.blotta.transacaoteste.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository("h2DB")
public class H2DBDao implements TransactionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public H2DBDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addTransaction(Transaction transaction) {
        String sql = "INSERT INTO TBL_TRANSACTIONS(date, time, value, card_application, payment_status)" +
                " VALUES(?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getValue(),
                    CardApplication.toInt(transaction.getCardApplication()),
                    PaymentStatus.toInt(transaction.getStatus())
            );
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<Transaction> selectAllTransactions() {
        final String sql = "SELECT * FROM TBL_TRANSACTIONS";
        // return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class));
        List<Transaction> transactions = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Transaction(
                    resultSet.getLong("id"),
                    resultSet.getDate("date").toLocalDate(),
                    resultSet.getTime("time").toLocalTime(),
                    resultSet.getBigDecimal("value"),
                    CardApplication.values()[resultSet.getInt("card_application")],
                    PaymentStatus.values()[resultSet.getInt("payment_status")]
            );
        });
        return transactions;
    }

    @Override
    public Optional<Transaction> getTransactionById(long id) {
        final String sql = "SELECT * FROM TBL_TRANSACTIONS WHERE id = ?";
        Transaction transaction = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            return new Transaction(
                    resultSet.getLong("id"),
                    resultSet.getDate("date").toLocalDate(),
                    resultSet.getTime("time").toLocalTime(),
                    resultSet.getBigDecimal("value"),
                    CardApplication.values()[resultSet.getInt("card_application")],
                    PaymentStatus.values()[resultSet.getInt("payment_status")]
            );
        });
        return Optional.ofNullable(transaction);
    }

    @Override
    public int updateTransaction(Transaction transaction) {

        String sql = "UPDATE TBL_TRANSACTIONS SET date = ?, time = ?, value = ?, card_application = ?, payment_status = ? WHERE id = ?";
        Object[] params = {
                transaction.getDate(),
                transaction.getTime(),
                transaction.getValue(),
                CardApplication.toInt(transaction.getCardApplication()),
                PaymentStatus.toInt(transaction.getStatus()),
                transaction.getId()
        };
        int[] types = {
                Types.DATE,
                Types.TIME,
                Types.DECIMAL,
                Types.INTEGER,
                Types.INTEGER,
                Types.BIGINT
        };

        try {
            int modified = jdbcTemplate.update(
                    sql,
                    params,
                    types
            );
            return modified;
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
