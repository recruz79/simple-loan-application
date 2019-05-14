package evaluation.homework.service;

import evaluation.homework.domain.ApplicationAttempt;
import evaluation.homework.domain.Loan;
import evaluation.homework.model.LoanApplicationModel;
import evaluation.homework.repository.ApplicationAttemptRepository;
import evaluation.homework.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanApplicationService {

    private Clock clock = Clock.systemDefaultZone();
    private static final Logger logger = LoggerFactory.getLogger(LoanApplicationService.class);
    public static final LocalTime RISK_TIME_START = LocalTime.of(0, 0);
    public static final LocalTime RISK_TIME_END = LocalTime.of(6, 0);
    public static final int LOW_RISK = 0;
    private final Integer MAX_NUMBER_OF_ATTEMPTS = 3;


    public LoanApplicationService(Clock clock) {
        this.clock = clock;
    }

    LoanApplicationService() {
    }

    @Autowired
    private ApplicationAttemptRepository applicationAttemptRepository;

    @Autowired
    private LoanRepository loanRepository;



    public String processLoan(LoanApplicationModel loanApplicationModel, HttpServletRequest request) {
        int risk = 0;
        String message;

        risk += verifyMaxAttemptsReached(request.getRemoteAddr()) ? 1 : 0;
        risk += verifyLoanRiskyHours() ? 1 : 0;


        if (risk == LOW_RISK) {
            Loan loan = saveLoanApplication(loanApplicationModel);
            message = "Loan accepted with reference ID : " + loan.getId();
        } else {
            message = "rejected";
        }

        addApplicationAttempt(request.getRemoteAddr());
        return message;
    }

    private boolean verifyMaxAttemptsReached(String remoteAddressIp) {
        List<ApplicationAttempt> result = applicationAttemptRepository
                .findByRemoteAddressAndApplicationDate(remoteAddressIp, LocalDate.now(clock));

        if (result.size() >= MAX_NUMBER_OF_ATTEMPTS) {
            return true;
        }
        return false;
    }

    private boolean verifyLoanRiskyHours() {
        LocalTime currentTime = LocalTime.now(clock);

        if (currentTime.isAfter(RISK_TIME_START) && currentTime.isBefore(RISK_TIME_END)) {
            System.out.println("Im inside the interval!!");
            return false;
        }

        return true;
    }

    private Loan saveLoanApplication(LoanApplicationModel loanApplicationModel) {
        Loan loan = new Loan();
        loan.setFirstname(loanApplicationModel.getFirstName());
        loan.setSurname(loanApplicationModel.getSurName());
        loan.setAmount(loanApplicationModel.getAmount().longValue());
        loan.setTerm(loanApplicationModel.getTerm().longValue());
        loanRepository.save(loan);
        return loan;
    }


    private void addApplicationAttempt(String clientRemoteAddress) {
        ApplicationAttempt applicationAttempt = new ApplicationAttempt(clientRemoteAddress);
        applicationAttemptRepository.save(applicationAttempt);
    }

    public List<Loan> getAllLoans() {
        List<Loan> result = new ArrayList<>();
        for (Loan loan: loanRepository.findAll()) {
            result.add(loan);
        }
        return result;
    }
}
