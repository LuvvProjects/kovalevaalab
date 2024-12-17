package tech.reliab.course.kovalevLab.bank.service;

import tech.reliab.course.kovalevLab.bank.DTO.CreditAccountDto;
import tech.reliab.course.kovalevLab.bank.entity.CreditAccount;

import java.util.List;

public interface CreditAccountService {

    CreditAccount createCreditAccount(CreditAccountDto creditAccountDTO);

    CreditAccount getCreditAccountById(int id);

    CreditAccount getCreditAccountDtoById(int id);

    List<CreditAccount> getAllCreditAccounts();

    CreditAccount updateCreditAccount(int id, int bankId);

    void deleteCreditAccount(int id);
}
