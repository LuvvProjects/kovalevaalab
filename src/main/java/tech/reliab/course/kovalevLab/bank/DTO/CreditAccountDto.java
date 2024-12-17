package tech.reliab.course.kovalevLab.bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditAccountDto {

    private int userId;
    private int bankId;
    private LocalDate startDate;
    private int loanTermMonths;
    private double loanAmount;
    private double interestRate;
    private int employeeId;
    private int paymentAccountId;
}