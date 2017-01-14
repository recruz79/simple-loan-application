package evaluation.homework.repository;

import evaluation.homework.domain.LoanApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by recruz.
 */
public interface LoanRepository extends CrudRepository<LoanApplication, Long> {

   List<LoanApplication> findBySurname(String surname);

}