package tech.reliab.course.kovalevLab.bank.service;

import tech.reliab.course.kovalevLab.bank.DTO.BankDto;
import tech.reliab.course.kovalevLab.bank.entity.Bank;

import java.util.List;

public interface BankService {
    Bank createBank(BankDto bankDto);

    Bank getBankById(int id);

    Bank getBankDtoById(int id);

    List<Bank> getAllBanks();

    Bank updateBank(int id, BankDto bankDto);

    void deleteBank(int id);
}
