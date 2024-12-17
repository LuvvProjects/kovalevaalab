package tech.reliab.course.kovalevLab.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.kovalevLab.bank.DTO.BankDto;
import tech.reliab.course.kovalevLab.bank.entity.Bank;
import tech.reliab.course.kovalevLab.bank.repository.BankRepository;
import tech.reliab.course.kovalevLab.bank.service.BankService;
import tech.reliab.course.kovalevLab.bank.service.generator.GeneratorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final GeneratorService dataGeneratorService;

    /**
     * Создание нового банка.
     *
     * @param bankDto Данные для создания банка.
     * @return Созданный банк.
     */
    @Override
    public Bank createBank(BankDto bankDto) {
        Bank bank = new Bank(bankDto.getName());
        bank.setRating(dataGeneratorService.generateRating());
        bank.setTotalMoney(dataGeneratorService.generateTotalMoney());
        bank.setInterestRate(dataGeneratorService.calculateInterestRate(bank.getRating()));
        return bankRepository.save(bank);
    }

    /**
     * Чтение банка по его идентификатору.
     *
     * @param id Идентификатор банка.
     * @return Банк dto, если он найден
     * @throws NoSuchElementException Если банк не найден.
     */
    @Override
    public Bank getBankDtoById(int id) {
        return getBankById(id);
    }

    /**
     * Чтение банка по его идентификатору.
     *
     * @param id Идентификатор банка.
     * @return Банк, если он найден
     * @throws NoSuchElementException Если банк не найден.
     */
    @Override
    public Bank getBankById(int id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bank was not found"));
    }

    /**
     * Чтение всех банков.
     *
     * @return Список всех банков.
     */
    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    /**
     * Обновление информации о банке по его идентификатору.
     *
     * @param id   Идентификатор банка.
     * @param bankDto Данные для обновления банка.
     */
    @Override
    public Bank updateBank(int id, BankDto bankDto) {
        Bank bank = getBankById(id);
        bank.setName(bankDto.getName());
        return bankRepository.save(bank);
    }

    /**
     * Удаление банка по его идентификатору.
     *
     * @param id Идентификатор банка.
     */
    @Override
    public void deleteBank(int id) {
        bankRepository.deleteById(id);
    }
}