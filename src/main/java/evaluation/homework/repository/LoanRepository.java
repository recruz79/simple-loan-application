package evaluation.homework.repository;

import evaluation.homework.domain.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by recruz.
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {

   List<Loan> findBySurname(String surname);

}