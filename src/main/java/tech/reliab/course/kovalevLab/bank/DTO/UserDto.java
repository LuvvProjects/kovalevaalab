package tech.reliab.course.kovalevLab.bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String fullName;
    private LocalDate birthDate;
    private String job;
}
