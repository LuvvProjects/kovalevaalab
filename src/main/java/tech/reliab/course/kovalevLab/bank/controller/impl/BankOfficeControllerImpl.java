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
import tech.reliab.course.kovalevLab.bank.DTO.BankOfficeDto;
import tech.reliab.course.kovalevLab.bank.controller.BankOfficeController;
import tech.reliab.course.kovalevLab.bank.entity.BankOffice;
import tech.reliab.course.kovalevLab.bank.service.BankOfficeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-offices")
@Tag(name = "Office API", description = "API для управления офисами")
public class BankOfficeControllerImpl implements BankOfficeController {

    private final BankOfficeService bankOfficeService;

    @Override
    @Operation(summary = "Создать новый офис банка", description = "Создает новый офис банка на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Офис банка успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankOffice.class)))
    })
    @PostMapping
    public ResponseEntity<BankOffice> createBankOffice(@RequestBody @Parameter(description = "Данные для создания офиса банка") BankOfficeDto bankOfficeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankOfficeService.createBankOffice(bankOfficeDTO));
    }

    @Override
    @Operation(summary = "Удалить офис банка", description = "Удаляет офис банка по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Офис банка успешно удален")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankOffice(@PathVariable @Parameter(description = "ID офиса банка") int id) {
        bankOfficeService.deleteBankAtm(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Обновить офис банка", description = "Обновляет имя офиса банка по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Офис банка успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankOffice.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BankOffice> updateBankOffice(@PathVariable @Parameter(description = "ID офиса банка") int id,
                                                       @RequestParam @Parameter(description = "Новое имя офиса банка") String name) {
        return ResponseEntity.ok(bankOfficeService.updateBankOffice(id, name));
    }

    @Override
    @Operation(summary = "Получить офис банка по ID", description = "Возвращает офис банка по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankOffice.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BankOffice> getBankOfficeById(@PathVariable @Parameter(description = "ID офиса банка") int id) {
        return ResponseEntity.ok(bankOfficeService.getBankDtoOfficeById(id));
    }

    @Override
    @Operation(summary = "Получить все офисы банков", description = "Возвращает список всех офисов банков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankOffice.class)))
    })
    @GetMapping
    public ResponseEntity<List<BankOffice>> getAllBankOffices() {
        return ResponseEntity.ok(bankOfficeService.getAllBankOffices());
    }
}
