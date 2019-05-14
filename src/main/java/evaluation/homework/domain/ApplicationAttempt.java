package evaluation.homework.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by recruz.
 */
@Data
@Entity
@Table(name = "APPLICATION_ATTEMPT")
public class ApplicationAttempt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REMOTE_ADDRESS")
    private String remoteAddress;

    @Column(name = "APPLICATION_DATE")
    private LocalDate applicationDate;

    public ApplicationAttempt(String clientRemoteAddress) {
        this.remoteAddress = clientRemoteAddress;
        this.applicationDate = LocalDate.now();
    }

    public ApplicationAttempt() {
    }
}
