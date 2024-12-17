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
import tech.reliab.course.kovalevLab.bank.DTO.BankAtmDto;
import tech.reliab.course.kovalevLab.bank.controller.BankAtmController;
import tech.reliab.course.kovalevLab.bank.entity.BankAtm;
import tech.reliab.course.kovalevLab.bank.service.BankAtmService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-atms")
@Tag(name = "Bank ATM API", description = "API для управления банкоматами")
public class BankAtmControllerImpl implements BankAtmController {

    private final BankAtmService bankAtmService;

    @Override
    @Operation(summary = "Создать новый банкомат", description = "Создает новый банкомат на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банкомат успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAtm.class)))
    })
    @PostMapping
    public ResponseEntity<BankAtm> createBankAtm(@RequestBody @Parameter(description = "Данные для создания банкомата") BankAtmDto bankAtmDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankAtmService.createBankAtm(bankAtmDTO));
    }

    @Override
    @Operation(summary = "Удалить банкомат", description = "Удаляет банкомат по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банкомат успешно удален")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAtm(@PathVariable @Parameter(description = "ID банкомата") int id) {
        bankAtmService.deleteBankAtm(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Обновить банкомат", description = "Обновляет имя банкомата по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банкомат успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAtm.class))),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BankAtm> updateBankAtm(@PathVariable @Parameter(description = "ID банкомата") int id,
                                                 @RequestParam(name = "name")
                                                 @Parameter(description = "Новое имя банкомата") String name) {
        return ResponseEntity.ok(bankAtmService.updateBankAtm(id, name));
    }

    @Override
    @Operation(summary = "Получить банкомат по ID", description = "Возвращает банкомат по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAtm.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BankAtm> getBankAtmById(@PathVariable @Parameter(description = "ID банкомата") int id) {
        return ResponseEntity.ok(bankAtmService.getBankAtmDtoById(id));
    }

    @Override
    @Operation(summary = "Получить все банкоматы по ID банка", description = "Возвращает список банкоматов, принадлежащих определенному банку")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAtm.class)))
    })
    @GetMapping("/all-by-bank/{bankId}")
    public ResponseEntity<List<BankAtm>> getAllBankAtmByBankId(@PathVariable @Parameter(description = "ID банка") int bankId) {
        return ResponseEntity.ok(bankAtmService.getAllBankAtmsByBankId(bankId));
    }

    @Override
    @Operation(summary = "Получить все банкоматы", description = "Возвращает список всех банкоматов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankAtm.class)))
    })
    @GetMapping
    public ResponseEntity<List<BankAtm>> getAllBankAtms() {
        return ResponseEntity.ok(bankAtmService.getAllBankAtms());
    }
}