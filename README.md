# simple-loan-application

This is a simple Loan application which process loans based on LoanApplicationModel, the loan will be processed according to risk levels from LOW LEVEL to HIGH LEVEL by using a risk accumulator, if the Loan succeds to have a low risk it will be saved in the repository and will get a Loan Id by wich the user can follow up the status of the loan.

#### LoanApplicationModel JSON:


    {
        "firstName" : "Pedro",
        "surName" : "Estebes",
        "amount" : "1200000",
        "term" : "90"
    }




#### Endpoints :

- POST http:\\localhost:8080\loan : Creates a loan by getting a LoanApplicationModel and pass validations.

- GET http:\\localhost:8080\loans : Returns all the existing loans in the database.

#### Validations:

- Check loan application is not being applied from 00:00hrs to 06:00hrs using the maximum amount.
- Verify the same ip address is not applying a loan more than three times.


#### Return values:

- "The loan is approved with the id: X" : If the Loan Application by passing all the validations.

- "Rejected" : In case of validations failures and the loan is rejected.



#### Technologies Stack:
Maven 3, Java 8, Junit, JPA, H2, Lombok.
