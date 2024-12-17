package tech.reliab.course.kovalevLab.bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAccountDto {

    private int bankId;
    private int userId;
}
