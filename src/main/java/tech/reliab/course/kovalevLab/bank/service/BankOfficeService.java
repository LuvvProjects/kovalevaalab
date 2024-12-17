package tech.reliab.course.kovalevLab.bank.service;

import tech.reliab.course.kovalevLab.bank.DTO.BankOfficeDto;
import tech.reliab.course.kovalevLab.bank.entity.BankOffice;

import java.util.List;

public interface BankOfficeService {

    BankOffice createBankOffice(BankOfficeDto bankOfficeDTO);

    BankOffice getBankOfficeById(int id);

    BankOffice getBankDtoOfficeById(int id);

    List<BankOffice> getAllBankOffices();

    BankOffice updateBankOffice(int id, String name);

    void deleteBankAtm(int id);
}
