package evaluation.homework.controller;

import evaluation.homework.domain.LoanApplication;
import evaluation.homework.model.LoanApplicationModel;
import evaluation.homework.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by recruz.
 * Verifies a loan process by checking high risk hours and allowed amounts.
 * Store and blocks remote ips with more than three retries.
 */
@RestController
public class LoanApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(LoanApplicationController.class);

    public static final int START_RISK_HOUR = 0;
    public static final int END_RISK_HOUR = 6;
    private final Integer MAX_NUMBER_OF_ATTEMPTS = 3;
    private final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(10000);

    private Map<String, Integer> listOfRemoteAddress = new ConcurrentHashMap<>();

    @Autowired
    private LoanRepository loanRepository;

    public Map<String, Integer> getListOfRemoteAddress() {
        return listOfRemoteAddress;
    }

    @RequestMapping(value = "/loanApplication",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkLoan(@RequestBody LoanApplicationModel loanApplicationModel, HttpServletRequest request) {

        updateToRemoteAddressList(request.getRemoteAddr());

        if(checkMaxAttemptsReached(request.getRemoteAddr())) {
            if (verifyLoanRisk(loanApplicationModel)) {
                return new ResponseEntity<String>("Accepted with loan reference ID : " +
                        loanApplicationModel.getLoanReferenceId(), HttpStatus.CREATED);
            }
        } else {
            logger.warn("Max attempts for the IP " + request.getRemoteAddr() + " have been reached.");
        }

        return new ResponseEntity<String>("Rejected", HttpStatus.BAD_REQUEST);
    }

    private void updateToRemoteAddressList(String clientRemoteAddress) {
        Integer count = getListOfRemoteAddress().getOrDefault(clientRemoteAddress, 0);
        if(count <= MAX_NUMBER_OF_ATTEMPTS) {
            getListOfRemoteAddress().put(clientRemoteAddress, count + 1);
        }
    }

    private boolean checkMaxAttemptsReached(String remoteAddressIp) {
        return (getListOfRemoteAddress().get(remoteAddressIp) <= MAX_NUMBER_OF_ATTEMPTS);
    }

    private boolean verifyLoanRisk(LoanApplicationModel loanApplicationModel) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour >= START_RISK_HOUR && hour <= END_RISK_HOUR)
        {
            logger.warn("Range is between " + START_RISK_HOUR + " and " + END_RISK_HOUR + ", Risk is medium");
            if(loanApplicationModel.getAmount().compareTo(MAX_AMOUNT) > 0) {
                logger.warn("Total amount is bigger or equals than MAX_AMOUNT in this time range is not permitted, High Risk");
                return false;
            }
        }

        saveLoanApplication(loanApplicationModel);
        return true;
    }

    private void saveLoanApplication(LoanApplicationModel loanApplicationModel) {
        LoanApplication loan = new LoanApplication();
        loan.setFirstname(loanApplicationModel.getFirstName());
        loan.setSurname(loanApplicationModel.getSurName());
        loan.setAmount(loanApplicationModel.getAmount().longValue());
        loan.setTerm(loanApplicationModel.getTerm().longValue());
        loanRepository.save(loan);
        loanApplicationModel.setLoanReferenceId(loan.getId());
    }

}