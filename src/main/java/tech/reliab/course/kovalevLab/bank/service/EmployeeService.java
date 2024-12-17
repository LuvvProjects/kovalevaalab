package tech.reliab.course.kovalevLab.bank.service;

import tech.reliab.course.kovalevLab.bank.DTO.EmployeeDto;
import tech.reliab.course.kovalevLab.bank.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(EmployeeDto employeeDto);

    Employee getEmployeeDtoById(int id);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(int id, String name);

    void deleteEmployee(int id);
}
