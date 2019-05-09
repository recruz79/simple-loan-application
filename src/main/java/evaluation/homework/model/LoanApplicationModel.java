package evaluation.homework.model;

import java.math.BigDecimal;

/**
 * Created by recruz.
 */
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

    public LoanApplicationModel() {
    }

    public Long getLoanReferenceId() {
        return loanReferenceId;
    }

    public void setLoanReferenceId(Long loanReferenceId) {
        this.loanReferenceId = loanReferenceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
}
