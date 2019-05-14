package evaluation.homework.repository;

import evaluation.homework.domain.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by recruz.
 */
@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

}