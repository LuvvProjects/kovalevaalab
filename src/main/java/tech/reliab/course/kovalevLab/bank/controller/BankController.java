package tech.reliab.course.kovalevLab.bank.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import tech.reliab.course.kovalevLab.bank.DTO.BankDto;
import tech.reliab.course.kovalevLab.bank.entity.Bank;

import java.util.List;

public interface BankController {

    ResponseEntity<Bank> createBank(@RequestBody BankDto bankDto);

    ResponseEntity<Void> deleteBank(@PathVariable int id);

    ResponseEntity<Bank> updateBank(@PathVariable int id, @RequestBody BankDto bankDto);

    ResponseEntity<Bank> getBankById(@PathVariable int id);

    ResponseEntity<List<Bank>> getAllBanks();
}
