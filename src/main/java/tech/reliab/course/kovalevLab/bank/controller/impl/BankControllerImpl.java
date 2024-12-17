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
import tech.reliab.course.kovalevLab.bank.DTO.BankDto;
import tech.reliab.course.kovalevLab.bank.controller.BankController;
import tech.reliab.course.kovalevLab.bank.entity.Bank;
import tech.reliab.course.kovalevLab.bank.service.BankService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banks")
@Tag(name = "Bank API", description = "API для управления банками")
public class BankControllerImpl implements BankController {

    private final BankService bankService;

    @Operation(summary = "Создать новый банк", description = "Создает новый банк с указанным именем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банк успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Bank.class)))
    })
    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestBody @Parameter(description = "Данные для создания банка") BankDto bankDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankService.createBank(bankDto));
    }

    @Override
    @Operation(summary = "Удалить банк", description = "Удаляет банк по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банк успешно удален")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable @Parameter(description = "ID банка") int id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Обновить банк", description = "Обновляет имя банка по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банк успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Bank.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable @Parameter(description = "ID банка") int id,
                                           @RequestBody @Parameter(description = "Данные для обновления банка") BankDto bankDto) {
        return ResponseEntity.ok(bankService.updateBank(id, bankDto));
    }

    @Override
    @Operation(summary = "Получить банк по ID", description = "Возвращает банк по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Bank.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable @Parameter(description = "ID банка") int id) {
        return ResponseEntity.ok(bankService.getBankDtoById(id));
    }

    @Override
    @Operation(summary = "Получить все банки", description = "Возвращает список всех банков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Bank.class)))
    })
    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        return ResponseEntity.ok(bankService.getAllBanks());
    }
}