package evaluation.homework.service;

import evaluation.homework.domain.ApplicationAttempt;
import evaluation.homework.domain.Loan;
import evaluation.homework.model.LoanApplicationModel;
import evaluation.homework.repository.ApplicationAttemptRepository;
import evaluation.homework.repository.LoanRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by recruz.
 */
@Log
@Service
public class LoanApplicationService {

    private Clock clock = Clock.systemDefaultZone();
    public static final LocalTime RISK_TIME_START = LocalTime.of(0, 0);
    public static final LocalTime RISK_TIME_END = LocalTime.of(6, 0);
    public static final int LOW_RISK = 0;
    public static final BigDecimal MAXIMUM_AMOUNT = new BigDecimal(120000);
    private final Integer MAX_NUMBER_OF_ATTEMPTS = 3;


    protected LoanApplicationService(Clock clock) {
        this.clock = clock;
    }

    LoanApplicationService() {
    }

    @Autowired
    private ApplicationAttemptRepository applicationAttemptRepository;

    @Autowired
    private LoanRepository loanRepository;

    public String processLoan(LoanApplicationModel loanApplicationModel, String remoteAddress) {
        Integer loanRiskLevel = 0;
        String message;

        loanRiskLevel += verifyMaxAttemptsReached(remoteAddress) ? 1 : 0;
        loanRiskLevel += verifyLoanRiskyHoursWithMaxAmount(loanApplicationModel.getAmount()) ? 1 : 0;


        if (loanRiskLevel == LOW_RISK) {
            Loan loan = saveLoanApplication(loanApplicationModel);
            message = "Loan accepted with reference ID : " + loan.getId();
        } else {
            message = "rejected";
            log.severe("Something's wrong here");
        }

        addApplicationAttempt(remoteAddress);
        return message;
    }

    private boolean verifyMaxAttemptsReached(String remoteAddressIp) {
        List<ApplicationAttempt> result = applicationAttemptRepository
                .findByRemoteAddressAndApplicationDate(remoteAddressIp, LocalDate.now(clock));

        return (result.size() >= MAX_NUMBER_OF_ATTEMPTS) ? true : false;
    }

    private boolean verifyLoanRiskyHoursWithMaxAmount(BigDecimal amount) {
        LocalTime currentTime = LocalTime.now(clock);

        if (currentTime.isAfter(RISK_TIME_START) &&
                currentTime.isBefore(RISK_TIME_END) &&
                amount.compareTo(MAXIMUM_AMOUNT) >= 0) {
            System.out.println("Im inside the interval with maximum amount!!");
            return true;
        }

        return false;
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
        for (Loan loan : loanRepository.findAll()) {
            result.add(loan);
        }
        return result;
    }
}
