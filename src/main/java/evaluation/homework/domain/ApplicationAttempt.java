package evaluation.homework.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by recruz.
 */
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

    public ApplicationAttempt() {
    }

    public ApplicationAttempt(Long id, String remoteAddress, LocalDate applicationDate) {
        this.id = id;
        this.remoteAddress = remoteAddress;
        this.applicationDate = applicationDate;
    }

    public ApplicationAttempt(String clientRemoteAddress) {
        this.remoteAddress = clientRemoteAddress;
        this.applicationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
}
