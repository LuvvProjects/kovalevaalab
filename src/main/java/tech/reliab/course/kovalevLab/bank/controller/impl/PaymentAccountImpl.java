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
import tech.reliab.course.kovalevLab.bank.DTO.PaymentAccountDto;
import tech.reliab.course.kovalevLab.bank.controller.PaymentAccountController;
import tech.reliab.course.kovalevLab.bank.entity.PaymentAccount;
import tech.reliab.course.kovalevLab.bank.service.PaymentAccountService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-accounts")
@Tag(name = "Payment account API", description = "API для управления платежным счетом")
public class PaymentAccountImpl implements PaymentAccountController {

    private final PaymentAccountService paymentAccountService;

    @Override
    @Operation(summary = "Создать новый платежный счет", description = "Создает новый платежный счет на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Платежный счет успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentAccount.class)))
    })
    @PostMapping
    public ResponseEntity<PaymentAccount> createPaymentAccount(@RequestBody @Parameter(description = "Данные для создания платежного счета") PaymentAccountDto paymentAccountDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentAccountService.createPaymentAccount(paymentAccountDto));
    }

    @Override
    @Operation(summary = "Удалить платежный счет", description = "Удаляет платежный счет по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Платежный счет успешно удален")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentAccount(@PathVariable @Parameter(description = "ID платежного счета") int id) {
        paymentAccountService.deletePaymentAccount(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Обновить платежный счет", description = "Обновляет платежный счет по его ID и ID банка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Платежный счет успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentAccount.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<PaymentAccount> updatePaymentAccount(@PathVariable @Parameter(description = "ID платежного счета") int id,
                                                               @RequestParam @Parameter(description = "ID банка") int bankId) {
        return ResponseEntity.ok(paymentAccountService.updatePaymentAccount(id, bankId));
    }

    @Override
    @Operation(summary = "Получить платежный счет по ID", description = "Возвращает платежный счет по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentAccount.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentAccount> getBankByPaymentAccount(@PathVariable @Parameter(description = "ID платежного счета") int id) {
        return ResponseEntity.ok(paymentAccountService.getPaymentAccountDtoById(id));
    }

    @Override
    @Operation(summary = "Получить все платежные счета", description = "Возвращает список всех платежных счетов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentAccount.class)))
    })
    @GetMapping
    public ResponseEntity<List<PaymentAccount>> getAllPaymentAccounts() {
        return ResponseEntity.ok(paymentAccountService.getAllPaymentAccounts());
    }
}
