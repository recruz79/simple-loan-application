package evaluation.homework.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by recruz.
 */
@Entity
@Table(name = "LOAN_APPLICATION")
@Data
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

}
