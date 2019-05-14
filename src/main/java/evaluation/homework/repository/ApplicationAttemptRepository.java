package evaluation.homework.repository;

import evaluation.homework.domain.ApplicationAttempt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by recruz.
 */
@Repository
public interface ApplicationAttemptRepository extends CrudRepository<ApplicationAttempt, Long> {

    List<ApplicationAttempt> findByRemoteAddressAndApplicationDate(String remoteAddress, LocalDate applicationDate);

}
