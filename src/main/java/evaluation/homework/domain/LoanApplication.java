package evaluation.homework.domain;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by recruz.
 */
@Entity
@Table(name = "LOAN_APPLICATION")
public class LoanApplication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOAN_REFERENCE_ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "TERM")
    private Long term;

    public LoanApplication(String firstname, String surname, Long amount, Long term) {
        this.firstname = firstname;
        this.surname = surname;
        this.amount = amount;
        this.term = term;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public LoanApplication(){}

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("LoanApplication [firstname=%s, surname=%s, amount=%d, term=%d] ", firstname, surname, amount, term);
    }
}
