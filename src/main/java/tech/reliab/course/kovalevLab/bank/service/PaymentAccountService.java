package tech.reliab.course.kovalevLab.bank.service;

import tech.reliab.course.kovalevLab.bank.DTO.PaymentAccountDto;
import tech.reliab.course.kovalevLab.bank.entity.PaymentAccount;

import java.util.List;

public interface PaymentAccountService {

    PaymentAccount createPaymentAccount(PaymentAccountDto paymentAccountDto);

    PaymentAccount getPaymentAccountById(int id);

    PaymentAccount getPaymentAccountDtoById(int id);

    List<PaymentAccount> getAllPaymentAccounts();

    PaymentAccount updatePaymentAccount(int id, int bankId);

    void deletePaymentAccount(int id);
}
