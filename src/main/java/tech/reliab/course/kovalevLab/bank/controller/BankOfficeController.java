package tech.reliab.course.kovalevLab.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.kovalevLab.bank.DTO.BankOfficeDto;
import tech.reliab.course.kovalevLab.bank.entity.BankOffice;

import java.util.List;

public interface BankOfficeController {

    ResponseEntity<BankOffice> createBankOffice(@RequestBody BankOfficeDto bankOfficeDTO);

    ResponseEntity<Void> deleteBankOffice(@PathVariable int id);

    ResponseEntity<BankOffice> updateBankOffice(@PathVariable int id, @RequestParam(name = "name") String name);

    ResponseEntity<BankOffice> getBankOfficeById(@PathVariable int id);

    ResponseEntity<List<BankOffice>> getAllBankOffices();
}
