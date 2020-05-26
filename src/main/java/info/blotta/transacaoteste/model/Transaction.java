package info.blotta.transacaoteste.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private CardApplication cardApplication;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    /**
     * @deprecated Hibernate only
     */
    public Transaction() {}

    public Transaction(@JsonProperty("id") long id,
                       @JsonProperty("date") LocalDate date,
                       @JsonProperty("time") LocalTime time,
                       @JsonProperty("value") BigDecimal value,
                       @JsonProperty("cardApplication") CardApplication cardApplication,
                       @JsonProperty("status") PaymentStatus status) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.value = value;
        this.cardApplication = cardApplication;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public CardApplication getCardApplication() {
        return cardApplication;
    }

    public void setCardApplication(CardApplication cardApplication) {
        this.cardApplication = cardApplication;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
