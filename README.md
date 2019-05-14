# simple-loan-application

This is a simple Loan application which process Loans based on an JSON Request model as shown below:

    {
        "firstName" : "Pedro",
        "surName" : "Estebes",
        "amount" : "1200000",
        "term" : "90"
    }


Based on the Model data the loan will be processed according to risk levels from LOW LEVEL to HIGH LEVEL.


#### Validations:

- Check loan application is not being applied from 00:00hrs to 06:00hrs
- Verify the same ip address is not applying a loan more than three times


#### Return values:

- If the Loan Application is approved there will be a return string of:
    "The loan is approved with the id: X"
- Otherwise the return will be "Rejected"


#### Technologies Stack:
Maven 3, Java 8, Junit, JPA, H2, Lombok.
