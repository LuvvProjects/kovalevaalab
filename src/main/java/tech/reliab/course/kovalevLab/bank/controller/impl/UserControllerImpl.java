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
import tech.reliab.course.kovalevLab.bank.DTO.UserDto;
import tech.reliab.course.kovalevLab.bank.controller.UserController;
import tech.reliab.course.kovalevLab.bank.entity.User;
import tech.reliab.course.kovalevLab.bank.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User API", description = "API для управления клиентом")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @Operation(summary = "Создать нового пользователя", description = "Создает нового пользователя на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Parameter(description = "Данные для создания пользователя") UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userDto));
    }

    @Override
    @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Parameter(description = "ID пользователя") int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Обновить пользователя", description = "Обновляет имя пользователя по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable @Parameter(description = "ID пользователя") int id,
                                           @RequestParam @Parameter(description = "Новое имя пользователя") String name) {
        return ResponseEntity.ok(userService.updateUser(id, name));
    }

    @Override
    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @Parameter(description = "ID пользователя") int id) {
        return ResponseEntity.ok(userService.getUserDtoById(id));
    }

    @Override
    @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
