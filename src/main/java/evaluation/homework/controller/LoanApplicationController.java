package evaluation.homework.controller;

import evaluation.homework.domain.Loan;
import evaluation.homework.model.LoanApplicationModel;
import evaluation.homework.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by recruz.
 * Creates a loan application if validations pass correctly.
 */
@RestController
public class LoanApplicationController {

    @Autowired
    LoanApplicationService loanApplicationService;

    @RequestMapping(value = "/loan", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> loan(@RequestBody LoanApplicationModel loanApplicationModel, HttpServletRequest request) {
        String result = loanApplicationService.processLoan(loanApplicationModel, request.getRemoteAddr());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/loans", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Loan> getAllLoans() {
        List<Loan> result = loanApplicationService.getAllLoans();
        return result ;
    }
}