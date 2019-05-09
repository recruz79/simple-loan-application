package evaluation.homework.repository;

import evaluation.homework.domain.ApplicationAttempt;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ApplicationAttemptRepository extends CrudRepository<ApplicationAttempt, Long> {

    // find By RemoteAddress and Equals to Current LocalDate findByRemoteAddressAndCreatedDateEqualsLocalDate
    List<ApplicationAttempt> findByRemoteAddressAndCreatedDateEqualsLocalDate(String remoteAddress, LocalDate localDate);

}
