package evaluation.homework.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by recruz.
 */
@Entity
@Table(name = "BLOCKED_REMOTE_ADDRESS")
public class BlockedRemoteAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REMOTE_ADDRESS")
    private String remoteAddress;

    public BlockedRemoteAddress() {
    }

    public Long getId() {
        return id;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
}
