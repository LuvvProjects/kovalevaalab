package tech.reliab.course.kovalevLab.bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankOfficeDto {

    private String name;
    private String address;
    private boolean canPlaceAtm;
    private boolean canIssueLoan;
    private boolean cashWithdrawal;
    private boolean cashDeposit;
    private double rentCost;
    private int bankId;
}
