package evaluation.homework.service;

import evaluation.homework.model.LoanApplicationModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanApplicationServiceTest {

    @Autowired
    LoanApplicationService loanApplicationService;

    @Test
    public void sendLoanApplicationThenRejected() throws Exception {
        LoanApplicationModel loanApplicationModel =
                new LoanApplicationModel("Pepe", "Martinez", new BigDecimal(12), 30L);

        String result = loanApplicationService.processLoan(loanApplicationModel, "127.0.0.1");
        assertThat(result, equalTo("rejected"));
    }

    @Test
    public void sendLoanApplicationThenApproved() throws Exception {
        LoanApplicationModel loanApplicationModel =
                new LoanApplicationModel("Pepe", "Martinez", new BigDecimal(12), 30L);

        String result = loanApplicationService.processLoan(loanApplicationModel, "");
        assertThat(result, not(equalTo("rejected")));
    }

    @Test
    public void sendLoanApplicationRejectedByRiskyHours() throws Exception {
        LoanApplicationModel loanApplicationModel =
                new LoanApplicationModel("Pepe", "Martinez", new BigDecimal(12), 30L);

        String result = loanApplicationService.processLoan(loanApplicationModel, "");
        assertThat(result, equalTo("rejected"));
    }
}
