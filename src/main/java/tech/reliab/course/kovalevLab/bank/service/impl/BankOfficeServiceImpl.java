package tech.reliab.course.kovalevLab.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.kovalevLab.bank.DTO.BankOfficeDto;
import tech.reliab.course.kovalevLab.bank.entity.Bank;
import tech.reliab.course.kovalevLab.bank.entity.BankOffice;
import tech.reliab.course.kovalevLab.bank.repository.BankOfficeRepository;
import tech.reliab.course.kovalevLab.bank.service.BankOfficeService;
import tech.reliab.course.kovalevLab.bank.service.BankService;
import tech.reliab.course.kovalevLab.bank.service.generator.GeneratorService;
import tech.reliab.course.kovalevLab.bank.service.status.BankOfficeStatus;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BankOfficeServiceImpl implements BankOfficeService {

    private final BankOfficeRepository bankOfficeRepository;
    private final BankService bankService;
    private final GeneratorService generatorService;

    /**
     * Создание нового офиса банка.
     *
     * @param bankOfficeDTO содержит данные про офис
     * @return Созданный офис банка.
     */
    @Override
    public BankOffice createBankOffice(BankOfficeDto bankOfficeDTO) {
        Bank bank = bankService.getBankById(bankOfficeDTO.getBankId());
        BankOffice bankOffice = new BankOffice(bankOfficeDTO.getName(), bankOfficeDTO.getAddress(),
                bankOfficeDTO.isCanPlaceAtm(), bankOfficeDTO.isCanIssueLoan(),
                bankOfficeDTO.isCashWithdrawal(), bankOfficeDTO.isCashDeposit(),
                bankOfficeDTO.getRentCost(), bank);
        bankOffice.setStatus(BankOfficeStatus.randomStatus());
        bankOffice.setOfficeMoney(generatorService.generateOfficeMoney(bank));
        return bankOfficeRepository.save(bankOffice);
    }

    /**
     * Поиск офиса банка по его идентификатору.
     *
     * @param id Идентификатор офиса банка.
     * @return Офис банка, если он найден
     * @throws NoSuchElementException Если офис не найден.
     */
    @Override
    public BankOffice getBankOfficeById(int id) {
        return bankOfficeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("BankOffice was not found"));
    }

    @Override
    public BankOffice getBankDtoOfficeById(int id) {
        return getBankOfficeById(id);
    }

    /**
     * Чтение всех офисов банка.
     *
     * @return Список всех офисов банка.
     */
    @Override
    public List<BankOffice> getAllBankOffices() {
        return bankOfficeRepository.findAll();
    }

    /**
     * Обновление информации об офисе банка по его идентификатору.
     *
     * @param id   Идентификатор офиса банка.
     * @param name Новое название офиса банка.
     */
    @Override
    public BankOffice updateBankOffice(int id, String name) {
        BankOffice bankOffice = getBankOfficeById(id);
        bankOffice.setName(name);
        return bankOfficeRepository.save(bankOffice);
    }

    /**
     * Удаление офиса банка по его идентификатору
     *
     * @param id Идентификатор офиса банка.
     */
    @Override
    public void deleteBankAtm(int id) {
        bankOfficeRepository.deleteById(id);
    }
}