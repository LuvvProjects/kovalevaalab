package tech.reliab.course.kovalevLab.bank.service;

import tech.reliab.course.kovalevLab.bank.DTO.BankAtmDto;
import tech.reliab.course.kovalevLab.bank.entity.BankAtm;

import java.util.List;

public interface BankAtmService {
    BankAtm createBankAtm(BankAtmDto bankAtmDTO);

    BankAtm getBankAtmById(int id);

    BankAtm getBankAtmDtoById(int id);

    List<BankAtm> getAllBankAtms();

    List<BankAtm> getAllBankAtmsByBankId(int bankId);

    BankAtm updateBankAtm(int id, String name);

    void deleteBankAtm(int id);
}
