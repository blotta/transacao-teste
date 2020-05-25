package info.blotta.transacaoteste.api;

import info.blotta.transacaoteste.model.CardApplication;
import info.blotta.transacaoteste.model.PaymentStatus;
import info.blotta.transacaoteste.model.Transaction;
import info.blotta.transacaoteste.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addTransaction(@Valid @NonNull @RequestBody Transaction transaction) {
        // TODO: Spring returns json when bad params. Customize to txt for consistency
        if (transactionService.addTransaction(transaction) > 0) {
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } else {
            // TODO: Find out why
            return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PutMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> updateTransaction(@RequestBody Map<String, String> ti) {
        // Usando Map<String, String> pois id precisa estar presente. Spring o passa como zero.
        // Melhor se id fosse passado pela query
        // Deve haver um jeito melhor de implementar isso

        long new_id;
        LocalDate new_date;
        LocalTime new_time;
        BigDecimal new_value;
        CardApplication new_cardApplication;
        PaymentStatus new_paymentStatus;

        // Get id or fail
        try {
            new_id = Long.parseLong(ti.get("id"));
            new_date = LocalDate.parse(ti.get("date"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            new_time = LocalTime.parse(ti.get("time"), DateTimeFormatter.ISO_LOCAL_TIME);
            new_value = BigDecimal.valueOf(Double.parseDouble(ti.get("value")));
            new_cardApplication = CardApplication.valueOf(ti.get("cardApplication"));
            new_paymentStatus = PaymentStatus.valueOf(ti.get("status"));
        } catch (RuntimeException e) {
            return new ResponseEntity<String>("Bad parameters", HttpStatus.BAD_REQUEST);
        }

        Transaction t = new Transaction(
                new_id,
                new_date,
                new_time,
                new_value,
                new_cardApplication,
                new_paymentStatus
        );
        if (transactionService.updateTransaction(t) != null) {
            return new ResponseEntity<String>("Updated", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
}
