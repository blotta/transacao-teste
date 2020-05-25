package info.blotta.transacaoteste.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Transaction {

    //@GeneratedValue(strategy = GenerationType.AUTO)
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

    private CardApplication cardApplication;

    private PaymentStatus status;

    // /**
    //  * @deprecated Hibernate only
    //  */
    // public Transaction() {}

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
