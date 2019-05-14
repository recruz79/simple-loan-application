package evaluation.homework.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by recruz.
 */
@Data
public class LoanApplicationModel {

    private Long loanReferenceId;
    private String firstName;
    private String surName;
    private BigDecimal amount;
    private Long term;
    private String remoteAddress;

    public LoanApplicationModel(String firstName, String surName, BigDecimal amount, Long term) {
        this.firstName = firstName;
        this.surName = surName;
        this.amount = amount;
        this.term = term;
    }

}
