package tech.reliab.course.kovalevLab.bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAtmDto {

    private String name;
    private int bankId;
    private int locationId;
    private int employeeId;
    private boolean cashWithdrawal;
    private boolean cashDeposit;
    private double maintenanceCost;
}
