package tech.reliab.course.kovalevLab.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.kovalevLab.bank.DTO.CreditAccountDto;
import tech.reliab.course.kovalevLab.bank.entity.Bank;
import tech.reliab.course.kovalevLab.bank.entity.CreditAccount;
import tech.reliab.course.kovalevLab.bank.repository.CreditAccountRepository;
import tech.reliab.course.kovalevLab.bank.service.*;
import tech.reliab.course.kovalevLab.bank.service.generator.GeneratorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {

    private final CreditAccountRepository creditAccountRepository;
    private final BankService bankService;
    private final UserService userService;
    private final EmployeeService employeeService;
    private final PaymentAccountService paymentAccountService;
    private final GeneratorService generatorService;

    /**
     * Создание нового кредитного аккаунта.
     *
     * @param creditAccountDTO данные аккаунта
     * @return Созданный кредитный аккаунт.
     */
    @Override
    public CreditAccount createCreditAccount(CreditAccountDto creditAccountDTO) {
        Bank bank = bankService.getBankById(creditAccountDTO.getBankId());
        CreditAccount creditAccount = new CreditAccount(userService.getUserById(creditAccountDTO.getUserId()),
                bank, creditAccountDTO.getStartDate(),
                creditAccountDTO.getLoanTermMonths(), creditAccountDTO.getInterestRate(),
                employeeService.getEmployeeById(creditAccountDTO.getEmployeeId()),
                paymentAccountService.getPaymentAccountById(creditAccountDTO.getPaymentAccountId()));
        creditAccount.setEndDate(generatorService.calculateEndDate(creditAccountDTO.getStartDate(), creditAccountDTO.getLoanTermMonths()));
        creditAccount.setLoanAmount(generatorService.calculateLoanAmount(creditAccountDTO.getLoanAmount(), bank));
        creditAccount.setMonthlyPayment(generatorService.calculateMonthlyPayment(creditAccountDTO.getInterestRate(),
                creditAccountDTO.getLoanAmount(), creditAccountDTO.getLoanTermMonths()));
        creditAccount.setInterestRate(generatorService.calculateInterestRate(creditAccountDTO.getInterestRate(), bank));
        return creditAccountRepository.save(creditAccount);
    }

    /**
     * Чтение кредитного аккаунта по его идентификатору.
     *
     * @param id Идентификатор кредитного аккаунта.
     * @return Кредитный аккаунт, если он найден
     * @throws NoSuchElementException Если кредитный аккаунт не найден.
     */
    @Override
    public CreditAccount getCreditAccountById(int id) {
        return creditAccountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("CreditAccount was not found"));
    }

    @Override
    public CreditAccount getCreditAccountDtoById(int id) {
        return getCreditAccountById(id);
    }

    /**
     * Чтение всех кредитных аккаунтов.
     *
     * @return Список всех кредитных аккаунтов.
     */
    @Override
    public List<CreditAccount> getAllCreditAccounts() {
        return creditAccountRepository.findAll();
    }

    /**
     * Обновление информации о кредитном аккаунте по его идентификатору.
     *
     * @param id     Идентификатор кредитного аккаунта.
     * @param bankId Банк, который предоставляет кредит.
     */
    @Override
    public CreditAccount updateCreditAccount(int id, int bankId) {
        CreditAccount creditAccount = getCreditAccountById(id);
        creditAccount.setBank(bankService.getBankById(bankId));
        return creditAccountRepository.save(creditAccount);
    }

    /**
     * Удаление кредитного аккаунта по его идентификатору
     *
     * @param id Идентификатор кредитного аккаунта.
     */
    @Override
    public void deleteCreditAccount(int id) {
        creditAccountRepository.deleteById(id);
    }
}