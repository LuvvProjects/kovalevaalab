package tech.reliab.course.kovalevLab.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.kovalevLab.bank.DTO.PaymentAccountDto;
import tech.reliab.course.kovalevLab.bank.entity.PaymentAccount;

import java.util.List;

public interface PaymentAccountController {

    ResponseEntity<PaymentAccount> createPaymentAccount(@RequestBody PaymentAccountDto paymentAccountDto);

    ResponseEntity<Void> deletePaymentAccount(@PathVariable int id);

    ResponseEntity<PaymentAccount> updatePaymentAccount(@PathVariable int id, @RequestParam(name = "bankId") int bankId);

    ResponseEntity<PaymentAccount> getBankByPaymentAccount(@PathVariable int id);

    ResponseEntity<List<PaymentAccount>> getAllPaymentAccounts();
}
