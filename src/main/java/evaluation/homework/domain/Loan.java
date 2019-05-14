package evaluation.homework.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by recruz.
 */
@Entity
@Table(name = "LOAN_APPLICATION")
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "TERM")
    private Long term;

    public Loan(String firstname, String surname, Long amount, Long term) {
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

    public Loan(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Loan [firstname=%s, surname=%s, amount=%d, term=%d] ", firstname, surname, amount, term);
    }
}
