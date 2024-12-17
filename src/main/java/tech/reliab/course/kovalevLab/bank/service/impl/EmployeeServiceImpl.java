package tech.reliab.course.kovalevLab.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.kovalevLab.bank.DTO.EmployeeDto;
import tech.reliab.course.kovalevLab.bank.entity.Employee;
import tech.reliab.course.kovalevLab.bank.repository.EmployeeRepository;
import tech.reliab.course.kovalevLab.bank.service.BankOfficeService;
import tech.reliab.course.kovalevLab.bank.service.BankService;
import tech.reliab.course.kovalevLab.bank.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BankService bankService;
    private final BankOfficeService bankOfficeService;

    /**
     * Создание нового сотрудника банка.
     *
     * @param employeeDto информация о сотруднике
     * @return Созданный сотрудник банка.
     */
    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getFullName(), employeeDto.getBirthDate(),
                                         employeeDto.getPosition(), bankService.getBankById(employeeDto.getBankId()),
                                         employeeDto.isRemoteWork(), bankOfficeService.getBankOfficeById(employeeDto.getBankOfficeId()),
                                         employeeDto.isCanIssueLoans(), employeeDto.getSalary());
        return employeeRepository.save(employee);
    }

    /**
     * Чтение сотрудника по его идентификатору.
     *
     * @param id Идентификатор сотрудника.
     * @return Сотрудник, если он найден
     * @throws NoSuchElementException Если сотрудник не найден.
     */
    public Employee getEmployeeDtoById(int id) {
        return getEmployeeById(id);
    }

    /**
     * Чтение сотрудника по его идентификатору.
     *
     * @param id Идентификатор сотрудника.
     * @return Сотрудник, если он найден
     * @throws NoSuchElementException Если сотрудник не найден.
     */
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee was not found"));
    }

    /**
     * Чтение всех сотрудников.
     *
     * @return Список всех сотрудников.
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Обновление информации о сотруднике по его идентификатору.
     *
     * @param id   Идентификатор сотрудника.
     * @param name Новое имя сотрудника.
     */
    public Employee updateEmployee(int id, String name) {
        Employee employee = getEmployeeById(id);
        employee.setFullName(name);
        return employeeRepository.save(employee);
    }

    /**
     * Удаление сотрудника по его идентификатору.
     *
     * @param id Идентификатор сотрудника.
     */
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
