package tech.reliab.course.kovalevLab.bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String fullName;
    private LocalDate birthDate;
    private String position;
    private int bankId;
    private boolean remoteWork;
    private int bankOfficeId;
    private boolean canIssueLoans;
    private double salary;
}
