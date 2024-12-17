package tech.reliab.course.kovalevLab.bank.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.kovalevLab.bank.DTO.EmployeeDto;
import tech.reliab.course.kovalevLab.bank.controller.EmployeeController;
import tech.reliab.course.kovalevLab.bank.entity.Employee;
import tech.reliab.course.kovalevLab.bank.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Tag(name = "Employee API", description = "API для управления сотрудниками")
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;

    @Override
    @Operation(summary = "Создать нового сотрудника", description = "Создает нового сотрудника на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сотрудник успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)))
    })
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Parameter(description = "Данные для создания сотрудника") EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employeeDto));
    }

    @Override
    @Operation(summary = "Удалить сотрудника", description = "Удаляет сотрудника по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сотрудник успешно удален")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable @Parameter(description = "ID сотрудника") int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Обновить сотрудника", description = "Обновляет имя сотрудника по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сотрудник успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable @Parameter(description = "ID сотрудника") int id,
                                                   @RequestParam @Parameter(description = "Новое имя сотрудника") String name) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, name));
    }

    @Override
    @Operation(summary = "Получить сотрудника по ID", description = "Возвращает сотрудника по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable @Parameter(description = "ID сотрудника") int id) {
        return ResponseEntity.ok(employeeService.getEmployeeDtoById(id));
    }

    @Override
    @Operation(summary = "Получить всех сотрудников", description = "Возвращает список всех сотрудников")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)))
    })
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
