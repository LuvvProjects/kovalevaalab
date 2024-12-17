package tech.reliab.course.kovalevLab.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.kovalevLab.bank.DTO.UserDto;
import tech.reliab.course.kovalevLab.bank.entity.User;
import tech.reliab.course.kovalevLab.bank.repository.UserRepository;
import tech.reliab.course.kovalevLab.bank.service.UserService;
import tech.reliab.course.kovalevLab.bank.service.generator.GeneratorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GeneratorService dataGeneratorService;

    /**
     * Создание нового пользователя.
     *
     * @param userDto содержит данные пользователя
     * @return Созданный пользователь.
     */
    @Override
    public User createUser(UserDto userDto) {
        User user = new User(userDto.getFullName(), userDto.getBirthDate(), userDto.getJob());
        user.setMonthlyIncome(dataGeneratorService.generateMonthlyIncome());
        user.setCreditRating(dataGeneratorService.generateCreditRating(user.getMonthlyIncome()));
        return userRepository.save(user);
    }

    /**
     * Чтение пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь, если он найден
     * @throws NoSuchElementException Если пользователь не найден.
     */
    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User was not found"));
    }

    @Override
    public User getUserDtoById(int id) {
        return getUserById(id);
    }

    /**
     * Чтение всех пользователей.
     *
     * @return Список всех пользователей.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Обновление информации о пользователе по его идентификатору.
     *
     * @param id   Идентификатор пользователя.
     * @param name Новое имя пользователя.
     */
    @Override
    public User updateUser(int id, String name) {
        User user = getUserById(id);
        user.setFullName(name);
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     */
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}