package evaluation.homework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by recruz.
 */
@Entity
@Table(name = "APPLICATION_ATTEMPT")
public class ApplicationAttempt implements Serializable {

    @Column(name = "REMOTE_ADDRESS")
    private String remoteAddress;

    @Column(name = "DATE")
    private LocalDate createdDate;

    public ApplicationAttempt() {
    }

    public ApplicationAttempt(String clientRemoteAddress) {
        this.remoteAddress = clientRemoteAddress;
        this.createdDate = LocalDate.now();
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate date) {
        this.createdDate = date;
    }

}
