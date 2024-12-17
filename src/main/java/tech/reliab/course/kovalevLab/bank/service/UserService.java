package tech.reliab.course.kovalevLab.bank.service;

import tech.reliab.course.kovalevLab.bank.DTO.UserDto;
import tech.reliab.course.kovalevLab.bank.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    User getUserById(int id);

    User getUserDtoById(int id);

    List<User> getAllUsers();

    User updateUser(int id, String name);

    void deleteUser(int id);
}
