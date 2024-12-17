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
import tech.reliab.course.kovalevLab.bank.DTO.CreditAccountDto;
import tech.reliab.course.kovalevLab.bank.controller.CreditAccountController;
import tech.reliab.course.kovalevLab.bank.entity.CreditAccount;
import tech.reliab.course.kovalevLab.bank.service.CreditAccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-accounts")
@Tag(name = "Credit account API", description = "API для управления кредитным счетом")
public class CreditAccountControllerImpl implements CreditAccountController {

    private final CreditAccountService creditAccountService;

    @Override
    @Operation(summary = "Создать новый кредитный счет", description = "Создает новый кредитный счет на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кредитный счет успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreditAccount.class)))
    })
    @PostMapping
    public ResponseEntity<CreditAccount> createCreditAccount(@RequestBody @Parameter(description = "Данные для создания кредитного счета") CreditAccountDto creditAccountDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(creditAccountService.createCreditAccount(creditAccountDTO));
    }

    @Override
    @Operation(summary = "Удалить кредитный счет", description = "Удаляет кредитный счет по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кредитный счет успешно удален")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteCreditAccount(@RequestParam @Parameter(description = "ID кредитного счета") int id) {
        creditAccountService.deleteCreditAccount(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Обновить кредитный счет", description = "Обновляет кредитный счет по его ID и ID банка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кредитный счет успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreditAccount.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CreditAccount> updateCreditAccount(@PathVariable @Parameter(description = "ID кредитного счета") int id,
                                                             @RequestParam @Parameter(description = "ID банка") int bankId) {
        return ResponseEntity.ok(creditAccountService.updateCreditAccount(id, bankId));
    }

    @Override
    @Operation(summary = "Получить кредитный счет по ID", description = "Возвращает кредитный счет по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreditAccount.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CreditAccount> getBankByCreditAccount(@PathVariable @Parameter(description = "ID кредитного счета") int id) {
        return ResponseEntity.ok(creditAccountService.getCreditAccountDtoById(id));
    }

    @Override
    @Operation(summary = "Получить все кредитные счета", description = "Возвращает список всех кредитных счетов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreditAccount.class)))
    })
    @GetMapping
    public ResponseEntity<List<CreditAccount>> getAllCreditAccounts() {
        return ResponseEntity.ok(creditAccountService.getAllCreditAccounts());
    }
}
